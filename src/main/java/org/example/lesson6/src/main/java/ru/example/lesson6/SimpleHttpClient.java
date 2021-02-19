package ru.example.lesson6;

import java.io.*;
import java.net.Socket;


public class SimpleHttpClient {
    public static final String UTF_8 = "charset=UTF-8";
    public static final String POST = "POST";
    public static final String GET = "GET";
    public enum ContentType {
        TEXT("text/plain;"), JSON_ACCEPT("application/json, */*; q=0.01"),JSON_TYPE("application/json;");
        private final String txt;
        ContentType(String txt) {
            this.txt = txt;
        }
        public String getTxt() {
            return txt;
        }
    }
    public static class Response {
        private int contentLength;
        private String httpVersion;
        private int statusCode;
        public Response(InputStream in, boolean debug) throws IOException{
            String line;
            StringBuffer sb = new StringBuffer();
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(in))){
                line = reader.readLine();
                if(debug)
                    System.out.println(line);
                parseStatusLine(line);
                parseHeaders(debug, line, reader);
                parseBody(reader);
                System.out.println();
            }
        }

        private void parseBody(BufferedReader reader) throws IOException {
            String line;
            line = reader.readLine();
            while (line!= null){
                System.out.println(line);
                line = reader.readLine();
            }
        }

        private void parseHeaders(boolean debug, String line, BufferedReader reader) throws IOException {
            do{
                if(line.isEmpty())
                    break;
                line = reader.readLine();
                parseHeader(line);
                if (debug)
                    System.out.println(line);
            } while (line != null);
        }

        public void parseHeader(String line){
            if (line.startsWith("Content-Length")){
                contentLength = Integer.parseInt(line.split("\\s+")[1]);
            }
        };
        public void parseStatusLine(String line){
            String[] tokens = line.split("\\s+");
            httpVersion = tokens[0];
            statusCode = Integer.parseInt(tokens[1]);
        };

    }
    public static void sendRequest(String host, String subUrl, int port, String method, boolean json, String... param) throws IOException{
        String[] hostLine = host.split("/");
        String caret = "\r\n";
        String hostName = hostLine[2];
        String acceptType = ContentType.TEXT.getTxt()+ UTF_8;
        String contentType = acceptType;
        try(Socket socket = new Socket(hostName,8090)){
            if (json) {
                contentType = ContentType.JSON_TYPE.getTxt();
                acceptType = ContentType.JSON_ACCEPT.getTxt();
            }
            StringBuilder out = new StringBuilder();
            out.append(caret);
            //METHOD
            out.append(method).append(" /").append(subUrl);
            //PARAM
            if (method.equals(GET))
                out.append(parametrization(out, param));
            out.append(" HTTP/1.0").append(caret);
            appendHeaders(port, method, caret, hostName, acceptType, contentType, out, param);
            out.append(caret);
            //BODY
            if (param.length>0 && method.equals(POST))
                out.append(param[0]);
            out.append(caret);
            //System.out.println(out);
            socket.getOutputStream().write(out.toString().getBytes("UTF-8"));
            socket.getOutputStream().flush();
            new Response(socket.getInputStream(),true);
        }
    }

    private static void appendHeaders(int port, String method, String caret, String hostName, String acceptType, String contentType, StringBuilder out, String[] param) {
        //HEADERS
        out.append("Host: ").append(hostName).append(":").append(port).append(caret);
        out.append("Accept: ").append(acceptType).append(caret);
        out.append("Connection: ").append("close").append(caret);
        out.append("Content-Type: ").append(contentType).append(caret);
        //LENGTH BODY
        if (param.length>0 && method.equals(POST))
            out.append("Content-Length: ").append(param[0].length()).append(caret);
    }

    private static StringBuilder parametrization(StringBuilder out, String[] param) {
        StringBuilder sb = new StringBuilder();
        if (param.length>0) {
            sb.append("?");
            int count=0;
            while(param.length!=count++){
                sb.append("param").append(count).append("=").append(param[count-1]);
                if(param.length!=count)
                    sb.append("&");
            }
        }
        return sb;
    }

    public static void main(String[] args) throws Exception {
        sendRequest("http://localhost/", "hello",8090,GET, false);
        sendRequest("http://localhost/", "hello",8090,POST,false);
        sendRequest("http://localhost/", "value",8090,GET,false,"Hello","World");
        sendRequest("http://localhost/", "value",8090,POST,false,"Hello World!");
        String json=new Company(1L, "Geekbrains", "123456", "my@email.ru", 111L).toString();
        sendRequest("http://localhost/", "",8090,POST,true,json);
        sendRequest("http://localhost/", "company",8090,GET,true,"1","Geekbrains","my@mail.ru");
    }

}

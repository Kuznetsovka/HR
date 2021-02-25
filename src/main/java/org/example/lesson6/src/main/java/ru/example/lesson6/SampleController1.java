package ru.example.lesson6;

import com.sun.xml.messaging.saaj.packaging.mime.Header;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SampleController1 {

    @GetMapping ("/hello")
    public String getHello(){
        return "GET HELLO";
    }

    @PostMapping("/hello")
    public String postHello(){
        return "POST HELLO";
    }

    @PostMapping(produces ="application/json")
    @ResponseBody
    public Company postCompany(@RequestBody Company company){
        return company;
    }

    @GetMapping(value = "/company", params = {"param1", "param2","param3"})
    @ResponseBody
    public Company postCompanyFromString(@RequestParam String param1,
                                         @RequestParam String param2,
                                         @RequestParam String param3){
        return new Company(Long.parseLong(param1),param2,"123456",param3,111L);
    }


    // http://localhost:8090/value?param1=param1&param2=param2
    @GetMapping(value = "/value", params = {"param1", "param2"})
    public String getParam(@RequestParam String param1,
                           @RequestParam String param2){
        return "GET param1=" + param1 + " param2=" + param2;
    }

    // http://localhost:8090/value
    @PostMapping(value = "/value")
    public String postParam(@RequestBody String param1){
        return "POST param1=" + param1;
    }

}

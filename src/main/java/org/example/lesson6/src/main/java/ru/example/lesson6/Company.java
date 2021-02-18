package ru.example.lesson6;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {
    private Long id;
    private String name;
    private String password;
    private String email;
    private Long INN;

    @Override
    public String toString() {
        return
                "{" +
                    "\"id\":\"" + id + "\"," +
                    "\"name\":\"" + name + "\"," +
                    "\"password\":\"" + password + "\"," +
                    "\"email\":\"" + email + "\"," +
                    "\"INN\":\"" + INN +
                "\"}";
    }
}

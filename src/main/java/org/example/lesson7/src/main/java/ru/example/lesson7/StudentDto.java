package ru.example.lesson7;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String name;
    private int age;
}

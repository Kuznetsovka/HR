package ru.example.lesson7;

import java.util.List;

public interface StudentService {
    boolean save(StudentDto studentDto);
    StudentDto findById(Long id);
    StudentDto getByName(String name);
    Student findByName(String name);
    List<StudentDto> findAll();
    void delete(Long id);
    Student save(Student student);
    void updateProfile(StudentDto dto);
    Student getById(Long id);
    Long getId(StudentDto updateStudent);
    List<Student> getAll();
    Student entityByDto(StudentDto dto);
    StudentDto dtoByEntity(Student student);
}

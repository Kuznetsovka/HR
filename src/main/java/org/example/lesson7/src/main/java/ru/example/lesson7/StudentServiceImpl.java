package ru.example.lesson7;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        InitBDStudent();
    }

    private void InitBDStudent() {
        if (!studentRepository.existsById ((long) 1)) {
            studentRepository.saveAll (Arrays.asList (
                    new Student (1L,"Student1",  24),
                    new Student (2L, "Student2", 25),
                    new Student (3L, "Student3", 34)
            ));
        }
    }

    @Override
    @Transactional
    public boolean save(StudentDto studentDto) {
        Student student = entityByDto(studentDto);
        studentRepository.save(student);
        return true;
    }

    @Override
    @Transactional
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student findByName(String name) {
        return studentRepository.findFirstByName(name);
    }

    @Override
    @Transactional
    public StudentDto findById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        return dtoByEntity(student);
    }

    public StudentDto dtoByEntity(Student student) {
        return StudentDto.builder().id(student.getId()).name(student.getName()).age(student.getAge()).build();
    }

    public Student entityByDto(StudentDto dto) {
        return Student.builder().id(dto.getId()).name(dto.getName()).age(dto.getAge()).build();
    }

    @Override
    @Transactional
    public StudentDto getByName(String name) {
        Student student = studentRepository.findFirstByName (name);
        return dtoByEntity(student);
    }

    @Override
    @Transactional
    public List<StudentDto> findAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentsDto = new ArrayList<>();
        for (Student student : students) {
            studentsDto.add(dtoByEntity(student));
        }
        return studentsDto;
    }
    @Override
    @Transactional
    public void updateProfile(StudentDto dto) {
        Student savedStudent = studentRepository.findFirstByName(dto.getName());
        if(savedStudent == null){
            throw new RuntimeException("Student not found by name " + dto.getName());
        }
        boolean changed = false;
        if(dto.getName() != null && !dto.getName().isEmpty()){
            savedStudent.setName(dto.getName());
            changed = true;
        }
        if(!Objects.equals(dto.getAge(), savedStudent.getAge())){
            savedStudent.setAge(dto.getAge());
            changed = true;
        }
        if(changed){
            studentRepository.save(savedStudent);
        }
    }

    @Override
    @Transactional
    public Student getById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Long getId(StudentDto studentDto) {
        Student student = studentRepository.getOne (studentDto.getId());
        return student.getId ();
    }

    @Override
    @Transactional
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id){
        studentRepository.deleteById (id);
    }
}

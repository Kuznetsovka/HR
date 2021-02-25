package ru.example.lesson7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private List<Student> students;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String getById(Model model, @PathVariable Long id) {
        Student byId = studentService.getById (id);
        model.addAttribute("student", byId == null ? new Student() : byId);
        return "student_form";
    }

    @GetMapping
    public String studentList(Model model){
        students =  studentService.getAll();
        model.addAttribute("students",students);
        return "student";
    }


    @PostMapping("/create")
    public String newStudent(Model model){
        model.addAttribute("student", new StudentDto());
        return "student_form";
    }

    @PostMapping(value = "/update")
    public String saveStudent(Model model,StudentDto dto){
        Student savedStudent = studentService.save(studentService.entityByDto(dto));
        if(savedStudent!=null){
            if (dto.getId()!=null) {
                if (students.contains(savedStudent))
                    students.remove(savedStudent);
            }
            students.add(savedStudent);
            return "redirect:/students";
        }
        else {
            model.addAttribute("student", dto);
            return "student_form";
        }
    }

    // http://localhost:8090/students/edit/3 - POST
    @RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
    public String postFormUpdateStudent(Model model, @PathVariable Long id){
        StudentDto byId = studentService.findById(id);
        model.addAttribute("student",
                byId == null ? new StudentDto(): byId);
        return "student_form";
    }

    // http://localhost:8090/students/{id}/delete
    @RequestMapping(value="/delete/{id}", method = RequestMethod.POST)
    public String getRemoveById(Model model, @PathVariable Long id){
        students.remove(studentService.getById(id));
        studentService.delete(id);
        System.out.println("Удален продукт с id" + id);
        model.addAttribute("students", students);
        return "redirect:/students";
    }

}

package org.example.lesson5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.lesson5.SessionClass.sessionFactory;

@Component
public class App {
    @Autowired
    public void init() {
        EntityManager em = sessionFactory().createEntityManager();

        CrudRepo<Student,Long> dao = new CrudRepo<>(Student.class);
        Stream.Builder<Student> builderStudent= Stream.builder();
        for (int i = 1; i < 1000; i++) {
            builderStudent.add(Student.builder()
                    .name("Ivan" + i)
                    .mark((int) (Math.random()*5))
                    .build());
        }
        ArrayList<Student> students = builderStudent.build().collect(Collectors.toCollection(ArrayList::new));
        dao.create(em, students);
        dao.read(em,100);
    }
}

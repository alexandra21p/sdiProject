package core.service;


import core.model.Student;
import core.repository.ProblemRepository;
import core.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by Alexandra on 18/04/2017.
 */

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Override
    @Transactional
    public List<Student> findAll() {
        log.trace("findAll");

        List<Student> students = studentRepository.findAll();

        log.trace("findAll: students = {}", students);

        return students;
    }

    @Override
    public Student findStudent(Long studentId) {
        log.trace("findStudent: studentId = {}", studentId);

        Student student = studentRepository.findOne(studentId);

        log.trace("findStudent: student = {}", student);

        return student;
    }

    @Override
    @Transactional
    public Student updateStudent(Long studentId, String serialNumber, String name, Integer group,
                                 Set<Long> problems) {
        log.trace("updateStudent: studentId = {}, serialNumber = {}, name = {}, group = {}, problems = {}",
                studentId, serialNumber, name, group, problems);

        Student student = studentRepository.findOne(studentId);
        student.setSerialNumber(serialNumber);
        student.setName(name);
        student.setGroup(group);

        List<Long> toRemove = new ArrayList<>();
        student.getProblems().stream()
                .map(d -> d.getId())
                .forEach(id -> {
                    if (problems.contains(id)) {
                        problems.remove(id);
                    } else {
                        toRemove.add(id);
                    }
                });
        problemRepository.findAll(toRemove).forEach(p -> p.getStudentProblems().removeIf(sp -> sp.getProblem().getId().equals(p.getId())));
        problemRepository.findAll(problems).stream().forEach(p -> student.addProblem(p));

        log.trace("updateStudent: student = {}", student);

        return student;
    }

    @Override
    public Student addStudent(String serialNumber, String name, Integer group) {
        log.trace("addStudent: serialNumber = {}, name = {}, group = {}",
                serialNumber, name, group);

//        Student student = new Student(serialNumber, name, group);
//        student = studentRepository.save(student);
        Student student = Student.builder()
                .serialNumber(serialNumber)
                .name(name)
                .group(group)
                .build();
        student = studentRepository.save(student);

        log.trace("addStudent: student = {}", student);

        return student;
    }

    @Override
    public void deleteStudent(Long studentId) {
        log.trace("deleteStudent: studentId = {}", studentId);

        studentRepository.delete(studentId);

        log.trace("deleteStudent - method end");
    }

    @Override
    @Transactional
    public Student updateStudentGrades(Long studentId, Map<Long, Integer> grades) {
        log.trace("updateStudentGrades: studentId = {}, grades = {}", studentId, grades);

        Student student = studentRepository.findOne(studentId);
        student.getStudentProblems().stream()
                .forEach(sp -> sp.setGrade(grades.get(sp.getProblem().getId())));

        log.trace("updateStudentGrades: student = {}", student);
        return student;
    }
}

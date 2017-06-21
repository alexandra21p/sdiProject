package web.controller;

import core.model.Student;
import core.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.StudentConverter;
import web.dto.EmptyJsonResponse;
import web.dto.StudentDto;
import web.dto.StudentsDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandra on 18/04/2017.
 */

@RestController
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentConverter studentConverter;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public StudentsDto getStudents() {
        log.trace("getStudents");

        List<Student> students = studentService.findAll();

        log.trace("getStudents: students={}", students);

        return new StudentsDto(studentConverter.convertModelsToDtos(students));
    }

    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.PUT)
    public Map<String, StudentDto> updateStudent(
            @PathVariable final Long studentId,
            @RequestBody final Map<String, StudentDto> studentDtoMap) {
        log.trace("updateStudent: studentId={}, studentDtoMap={}", studentId, studentDtoMap);

        StudentDto studentDto = studentDtoMap.get("student");
        Student student = studentService.updateStudent(studentId, studentDto.getSerialNumber(),
                studentDto.getName(), studentDto.getGroup(), studentDto.getProblems());

        Map<String, StudentDto> result = new HashMap<>();
        result.put("student", studentConverter.convertModelToDto(student));

        log.trace("updateStudent: result={}", result);

        return result;
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public Map<String, StudentDto> createStudent(
            @RequestBody final Map<String, StudentDto> studentDtoMap) {
        log.trace("addStudent: studentDtoMap={}", studentDtoMap);

        StudentDto studentDto = studentDtoMap.get("student");
        Student student = studentService.addStudent(
                studentDto.getSerialNumber(), studentDto.getName(), studentDto.getGroup());

        Map<String, StudentDto> result = new HashMap<>();
        result.put("student", studentConverter.convertModelToDto(student));

        log.trace("updateStudent: result={}", result);

        return result;
    }

    @RequestMapping(value = "students/{studentId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteStudent(@PathVariable final Long studentId) {
        log.trace("deleteStudent: studentId={}", studentId);

        studentService.deleteStudent(studentId);

        log.trace("deleteStudent - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}

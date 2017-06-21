package web.controller;

import core.model.Student;
import core.model.StudentProblem;
import core.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.converter.StudentProblemConverter;
import web.dto.StudentProblemDto;
import web.dto.StudentProblemsDto;

import java.util.Map;
import java.util.Set;


@RestController
public class GradesController {
    private static final Logger log = LoggerFactory.getLogger(GradesController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentProblemConverter studentProblemConverter;

    @RequestMapping(value = "/grades/{studentId}", method = RequestMethod.GET)
    public StudentProblemsDto getStudentProblems(
            @PathVariable final Long studentId) {
        log.trace("getStudentProblems: studentId={}", studentId);

        Student student = studentService.findStudent(studentId);

        Set<StudentProblem> studentProblems = student.getStudentProblems();
        Set<StudentProblemDto> studentProblemDtos = studentProblemConverter
                .convertModelsToDtos(studentProblems);


        StudentProblemsDto result = new StudentProblemsDto(studentProblemDtos);

        log.trace("getStudentProblems: result = {}", result);

        return result;
    }

    @RequestMapping(value = "/grades/{studentId}", method = RequestMethod.PUT)
    public StudentProblemsDto updateStudentGrades(
            @PathVariable final Long studentId,
            @RequestBody final StudentProblemsDto studentProblemsDto) {
        log.trace("updateStudentGrades: studentId = {}, studentProblemsDto = {}",
                studentId, studentProblemsDto);

        Map<Long, Integer> grades = studentProblemConverter.convertDtoToMap(studentProblemsDto);
        Student student = studentService.updateStudentGrades(studentId, grades);

        Set<StudentProblemDto> studentProblemDtos = studentProblemConverter.
                convertModelsToDtos(student.getStudentProblems());
        StudentProblemsDto result = new StudentProblemsDto(studentProblemDtos);

        log.trace("getStudentProblems: result = {}", result);

        return result;
    }

//    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.PUT)
//    public Map<String, StudentDto> updateStudent(
//            @PathVariable final Long studentId,
//            @RequestBody final Map<String, StudentDto> studentDtoMap) {
//        log.trace("updateStudent: studentId={}, studentDtoMap={}", studentId, studentDtoMap);
//
//        StudentDto studentDto = studentDtoMap.get("student");
//        Student student = studentService.updateStudent(studentId, studentDto.getSerialNumber(),
//                studentDto.getName(), studentDto.getGroupNumber(), studentDto.getProblems());
//
//        Map<String, StudentDto> result = new HashMap<>();
//        result.put("student", studentConverter.convertModelToDto(student));
//
//        log.trace("updateStudent: result={}", result);
//
//        return result;
//    }
}

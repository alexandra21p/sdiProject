package web.converter;

import core.model.StudentProblem;
import org.springframework.stereotype.Component;
import web.dto.StudentProblemDto;
import web.dto.StudentProblemsDto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexandra on 10/05/2017.
 */
@Component
public class StudentProblemConverter extends BaseConverterGeneric<StudentProblem, StudentProblemDto> {
    @Override
    public StudentProblem convertDtoToModel(StudentProblemDto studentProblemDto) {
        throw new RuntimeException("not yet implemented.");
    }

    @Override
    public StudentProblemDto convertModelToDto(StudentProblem studentProblem) {
        StudentProblemDto studentProblemDto = StudentProblemDto.builder()
                .studentId(studentProblem.getStudent().getId())
                .problemId(studentProblem.getProblem().getId())
                .problemNumber(studentProblem.getProblem().getNumber())
                .grade(studentProblem.getGrade())
                .build();
        return studentProblemDto;
    }

    public Map<Long, Integer> convertDtoToMap(StudentProblemsDto studentProblemsDto) {
        Map<Long, Integer> grades = new HashMap<>();
        studentProblemsDto.getStudentProblems().stream()
                .forEach(sp -> grades.put(sp.getProblemId(), sp.getGrade()));
        return grades;
    }
}

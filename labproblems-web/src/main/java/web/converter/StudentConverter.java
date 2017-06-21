package web.converter;

import core.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import web.dto.StudentDto;

import java.util.stream.Collectors;


@Component
public class StudentConverter extends BaseConverter<Student, StudentDto> {

    private static final Logger log = LoggerFactory.getLogger(StudentConverter.class);

    @Override
    public StudentDto convertModelToDto(Student student) {
        StudentDto studentDto = StudentDto.builder()
                .serialNumber(student.getSerialNumber())
                .name(student.getName())
                .group(student.getGroup())
                .build();
        studentDto.setId(student.getId());
        if (student.getProblems() != null) {
            studentDto.setProblems(student.getProblems().stream()
                    .map(d -> d.getId())
                    .collect(Collectors.toSet()));
        }
        return studentDto;
    }
//    public StudentDto convertModelToDto(Student student) {
//        StudentDto studentDto = new StudentDto(student.getSerialNumber(), student.getName(), student.getGroup());
//        studentDto.setId(student.getId());
//        return studentDto;
//    }
}

package web.dto;

import lombok.*;
import java.util.Set;
/**
 * Created by Alexandra on 18/04/2017.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentsDto {
    private Set<StudentDto> students;

//    public StudentsDto() {
//    }
//
//    public StudentsDto(Set<StudentDto> students) {
//        this.students = students;
//    }
//
//    public Set<StudentDto> getStudents() {
//        return students;
//    }
//
//    public void setStudents(Set<StudentDto> students) {
//        this.students = students;
//    }

}

package web.dto;

import lombok.*;
import java.util.Set;

/**
 * Created by Alexandra on 10/05/2017.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentProblemsDto {
    Set<StudentProblemDto> studentProblems;
}

package web.dto;

import lombok.*;
import java.util.Set;

/**
 * Created by Alexandra on 19/04/2017.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProblemsDto {
    private Set<ProblemDto> problems;

//    public ProblemsDto() {
//    }
//
//    public ProblemsDto(Set<ProblemDto> problems) {
//        this.problems = problems;
//    }
//
//    public Set<ProblemDto> getProblems() {
//        return problems;
//    }
//
//    public void setProblems(Set<ProblemDto> problems) {
//        this.problems = problems;
//    }

}

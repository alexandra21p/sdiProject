package web.dto;

import lombok.*;

/**
 * Created by Alexandra on 10/05/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StudentProblemDto {
    private Long studentId;
    private Long problemId;
    private String problemNumber;
    private Integer grade;
}

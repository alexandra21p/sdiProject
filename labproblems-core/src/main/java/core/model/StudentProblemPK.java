package core.model;

import lombok.*;

import java.io.Serializable;

/**
 * Created by Alexandra on 04/05/2017.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class StudentProblemPK implements Serializable {
    private Student student;
    private Problem problem;
}

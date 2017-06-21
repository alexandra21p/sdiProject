package web.dto;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProblemDto extends BaseDto {
    private String number;
    private String statement;


    @Override
    public String toString() {
        return "ProblemDto{" +
                "number = '" + number + '\'' +
                ", statement = '" + statement + '\'' +
                "} " + super.toString();
    }


}

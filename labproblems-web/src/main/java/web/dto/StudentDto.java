package web.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentDto extends BaseDto {
    private String serialNumber;
    private String name;
    private Integer group;
    private Set<Long> problems;


    @Override
    public String toString() {
        return "StudentDto{" +
                "serialNumber = '" + serialNumber + '\'' +
                ", name = '" + name + '\'' +
                ", group = " + group +
                ", problems = " + problems +
                "} " + super.toString();
    }
 }

package web.converter;

import core.model.Problem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import web.dto.ProblemDto;


@Component
public class ProblemConverter extends BaseConverter<Problem, ProblemDto> {

    private static final Logger log = LoggerFactory.getLogger(ProblemConverter.class);

    @Override
    public ProblemDto convertModelToDto(Problem problem) {
//        ProblemDto problemDto = new ProblemDto(problem.getNumber(), problem.getStatement());
//        problemDto.setId(problem.getId());
//        return problemDto;
        ProblemDto problemDto = ProblemDto.builder()
                .number(problem.getNumber())
                .statement(problem.getStatement())
                .build();
        problemDto.setId(problem.getId());
        return problemDto;
    }
}

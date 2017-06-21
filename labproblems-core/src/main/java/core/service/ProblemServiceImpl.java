package core.service;

import core.model.Problem;
import core.repository.ProblemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Alexandra on 19/04/2017.
 */

@Service
public class ProblemServiceImpl implements ProblemService {
    private static final Logger log = LoggerFactory.getLogger(ProblemServiceImpl.class);

    @Autowired
    private ProblemRepository problemRepository;

    @Override
    @Transactional
    public List<Problem> findAll() {
        log.trace("findAll");

        List<Problem> problems = problemRepository.findAll();

        log.trace("findAll: Problems = {}", problems);

        return problems;
    }

    @Override
    @Transactional
    public Problem updateProblem(Long problemId, String problemNumber, String statement) {
        log.trace("updateProblem: problemId = {}, problemNumber = {}, statement = {}",
                problemId, problemNumber, statement);

        Problem problem = problemRepository.findOne(problemId);
        problem.setNumber(problemNumber);
        problem.setStatement(statement);

        log.trace("updateProblem: problem = {}", problem);

        return problem;
    }

    @Override
    public Problem addProblem(String problemNumber, String statement) {
        log.trace("addProblem: problemNumber = {}, statement = {}",
                problemNumber, statement);

//        Problem pb = new Problem(problemNumber, statement);
//        pb = problemRepository.save(pb);
        Problem p = Problem.builder()
                .number(problemNumber)
                .statement(statement)
                .build();
        Problem problem = problemRepository.save(p);

        log.trace("addProblem: problem = {}", problem);

        return problem;
    }

    @Override
    public void deleteProblem(Long problemId) {
        log.trace("deleteProblem: problemId={}", problemId);

        problemRepository.delete(problemId);

        log.trace("deleteProblem - method end");
    }
}
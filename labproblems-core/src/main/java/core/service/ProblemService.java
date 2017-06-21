package core.service;

import core.model.Problem;

import java.util.List;

/**
 * Created by Alexandra on 19/04/2017.
 */
public interface ProblemService {
    List<Problem> findAll();

    Problem updateProblem(Long problemId, String problemNumber, String statement);

    Problem addProblem(String problemNumber, String statement);

    void deleteProblem(Long problemId);
}

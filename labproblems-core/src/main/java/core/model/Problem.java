package core.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Alexandra on 19/04/2017.
 */
@Entity
@Table(name = "problem")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Problem extends BaseEntity<Long> {

    private static final int NUMBER_LENGTH = 10;
    @Column(name = "number", nullable = false, length = NUMBER_LENGTH)
    private String number;

    @Column(name = "statement", nullable = false)
    private String statement;


    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<StudentProblem> studentProblems = new HashSet<>();

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(
                studentProblems.stream()
                        .map(sp -> sp.getStudent())
                        .collect(Collectors.toSet())
        );
    }

    public void addStudent(Student student) {
        StudentProblem studentProblem = new StudentProblem();
        studentProblem.setStudent(student);
        studentProblem.setProblem(this);
        studentProblems.add(studentProblem);
    }

    public void addGrade(Student student, Integer grade) {
        StudentProblem studentProblem = new StudentProblem();
        studentProblem.setStudent(student);
        studentProblem.setGrade(grade);
        studentProblem.setProblem(this);
        studentProblems.add(studentProblem);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Problem Problem = (Problem) o;

        return number.equals(Problem.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public String toString() {
        return "Problem{" +
                "number = " + number + '\'' +
                ", statement = '" + statement + '\'' +
                "} " + super.toString();
    }
}
package core.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Alexandra on 18/04/2017.
 */

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Student extends BaseEntity<Long> {

    private static final int SERIAL_NUMBER_LENGTH = 16;

    @Column(name = "serial_number", nullable = false, length = SERIAL_NUMBER_LENGTH)
    private String serialNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "group_number", nullable = false)
    private Integer group;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<StudentProblem> studentProblems = new HashSet<>();


    public Set<Problem> getProblems() {
        return Collections.unmodifiableSet(
                this.studentProblems.stream().
                        map(sp -> sp.getProblem()).
                        collect(Collectors.toSet()));
    }

    public void addProblem(Problem problem) {
        StudentProblem studentProblem = new StudentProblem();
        studentProblem.setProblem(problem);
        studentProblem.setStudent(this);
        studentProblems.add(studentProblem);
    }

    public void addProblems(Set<Problem> problems) {
        problems.stream()
                .forEach(problem -> addProblem(problem));
    }

    public void addGrade(Problem problem, Integer grade) {
        StudentProblem studentProblem = new StudentProblem();
        studentProblem.setProblem(problem);
        studentProblem.setGrade(grade);
        studentProblem.setStudent(this);
        studentProblems.add(studentProblem);
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return serialNumber.equals(student.serialNumber);

    }

    @Override
    public int hashCode() {
        return serialNumber.hashCode();
    }

    @Override
    public String toString() {
        return "Student{" +
                "serialNumber = '" + serialNumber + '\'' +
                ", name = '" + name + '\'' +
                ", group = " + group +
                "} " + super.toString();
    }
}

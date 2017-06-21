import {Component} from "@angular/core";
import {Location} from "@angular/common";
import {Problem} from "../problems/shared/problem.model";
import {StudentService} from "../students/shared/student.service";
import {ProblemService} from "../problems/shared/problem.service";
import {Student} from "../students/shared/student.model";

@Component({
    moduleId: module.id,
    selector: 'web-problem-assign',
    templateUrl: './problem-assign.component.html',
    styleUrls: ['./problem-assign.component.css'],
})
export class ProblemAssignComponent {
    errorMessage: string;
    selectedStudent: Student;
    showProblems: boolean;
    selectedProblems: Problem[];
    unassignedProblems: Problem[];

    constructor(private studentService: StudentService,
                private problemService: ProblemService,
                private location: Location) {
    }

    goBack(): void {
        this.location.back();
    }

    loadProblems(serialNumber: string) {
        this.showProblems = false;
        if (!serialNumber) {
            console.log("serial number must not be empty");
            alert("serial number must not be empty");
            return;
        }
        this.loadSelectedProblems(serialNumber);
        this.loadUnassignedProblems();
    }
    private loadSelectedProblems(serialNumber: string) {
        this.studentService.getStudents()
            .subscribe(
                students => {
                    const currentStudent = students.filter(s => s.serialNumber === serialNumber);
                    //TODO there should be exactly one student in currentStudent or err; handle errs
                    if (currentStudent.length === 1) {
                        this.selectedStudent = currentStudent[0];
                        this.showProblems = true;
                        this.loadSelectedProblemsForStudent(this.selectedStudent);
                    }
                },
                error => this.errorMessage = <any>error);
    }

    private loadSelectedProblemsForStudent(student: Student) {
        this.selectedProblems = [];
        const problemIds = student.problems;
        if (problemIds) {
            problemIds.forEach(id => {
                this.problemService.getProblem(id)
                    .subscribe(
                        problem => {
                            this.selectedProblems = this.selectedProblems
                                .concat([problem]);
                        },
                        error => this.errorMessage = error);
            });
        }
    }

    private loadUnassignedProblems() {
      console.log("Should contain stuff: ", this.selectedProblems);
        //noinspection TypeScriptUnresolvedFunction
      // problems => this.unassignedProblems = problems.filter(x => this.selectedProblems.indexOf(x) < 0)
        this.problemService.getProblems()
            .subscribe(
                problems => this.unassignedProblems = problems,
                error => this.errorMessage = <any>error);
      console.log("unassigned: ", this.unassignedProblems);
    }

    selectProblem(problem: Problem) {
        const disc = this.selectedProblems.filter(p => p.number == problem.number);
        if (disc.length > 0) {
            console.log("Problem already selected! Choose another one.");
            alert("Problem already selected! Choose another one.");
            return;
        } else {
          this.selectedProblems = this.selectedProblems.concat([problem]);
        }
    }

    removeSelectedProblem(problem: Problem) {
        this.selectedProblems = this.selectedProblems.filter(p => p !== problem);
    }

    save() {

        this.selectedStudent.problems = this.selectedProblems.map(p => p.id);
        console.log("aaaaaaaaaaaa ", this.selectedProblems, this.selectedProblems.map(d => d.id), this.selectedStudent.problems);
        this.studentService.update(this.selectedStudent)
            .subscribe(_ => this.goBack())
    }
}

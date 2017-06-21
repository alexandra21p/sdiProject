import {Component, OnInit} from '@angular/core';
import {Problem} from "../shared/problem.model";
import {ProblemService} from "../shared/problem.service";
import {Router} from "@angular/router";


@Component({
    moduleId: module.id,
    selector: 'app-problem-list',
    templateUrl: './problem-list.component.html',
    styleUrls: ['./problem-list.component.css'],
})
export class ProblemListComponent implements OnInit {
    errorMessage: string;
    problems: Problem[];
    selectedProblem: Problem;

    constructor(private problemService: ProblemService,
                private router: Router) {
    }

    ngOnInit(): void {
        this.getProblems();
    }

    getProblems() {
        this.problemService.getProblems()
            .subscribe(
                problems => this.problems = problems,
                error => this.errorMessage = <any>error
            );
    }

    onSelect(problem: Problem): void {
        this.selectedProblem = problem;
    }

    gotoDetail(): void {
        this.router.navigate(['/problem/detail', this.selectedProblem.id]);
    }

    delete(problem: Problem): void {
        this.problemService.delete(problem.id)
            .subscribe(() => {
                this.problems = this.problems.filter(s => s !== problem);
                if (this.selectedProblem === problem) {
                    this.selectedProblem = null;
                }
            });
    }

}

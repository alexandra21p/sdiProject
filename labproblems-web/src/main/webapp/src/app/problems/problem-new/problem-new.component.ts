import {Component, Input} from "@angular/core";
import {Location} from '@angular/common';

import {Problem} from "../shared/problem.model";
import {ProblemService} from "../shared/problem.service";


@Component({
    moduleId: module.id,
    selector: 'app-problem-new',
    templateUrl: './problem-new.component.html',
    styleUrls: ['./problem-new.component.css'],
})
export class ProblemNewComponent {
    @Input() problem: Problem;

    constructor(private problemService: ProblemService,
                private location: Location) {
    }

    goBack(): void {
        this.location.back();
    }


    save(number, statement): void {
        console.log("data: ", number, statement);
        if (!this.isValid(number, statement)) {
            console.log("all fields are required ");
            alert("all fields are required");
            return;
        }
        this.problemService.create(number, statement)
            .subscribe(_ => this.goBack());
    }

    private isValid(number, statement) {
        if (!number || !statement) {
            console.log("all fields are required");
            return false;
        }
        //TODO other validations
        return true;
    }
}

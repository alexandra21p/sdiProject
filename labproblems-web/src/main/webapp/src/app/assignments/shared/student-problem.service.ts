import {Injectable} from "@angular/core";
import {Headers, Http, Response} from "@angular/http";
import {StudentProblem} from "./student-problem.model";

import {Observable} from "rxjs";

@Injectable()
export class StudentProblemService {
    private studentProblemUrl = 'http://localhost:8080/api/grades';
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) {
    }

    getStudentProblems(studentID: number): Observable<StudentProblem[]> {
        //noinspection TypeScriptUnresolvedFunction
      return this.http.get(`${this.studentProblemUrl}/${studentID}`, this.headers)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response) {
        let body = res.json();
        return body.studentProblems || {};
    }

    private handleError(error: Response | any) {
        let errMsg: string;
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable.throw(errMsg);
    }

    saveGrades(studentId: number, problemIdsGrades: Object): Observable<StudentProblem[]> {
        const url = `${this.studentProblemUrl}/${studentId}`;
        let studentProblems = this.createStudentProblems(studentId, problemIdsGrades);
        console.log("grades: ",studentProblems);
        console.log("request: ",JSON.stringify({"studentProblems": studentProblems}));
        //noinspection TypeScriptUnresolvedFunction
      return this.http
            .put(url, JSON.stringify({"studentProblems": studentProblems}), {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }

    private createStudentProblems(studentId: number, problemIdsGrades: Object): StudentProblem[] {
        const arr: StudentProblem[] = [];
        Object.keys(problemIdsGrades).forEach(problemId => {
            const sd = new StudentProblem(
                studentId,
                parseInt(problemId),
                null,
                problemIdsGrades[problemId]);
            arr.push(sd);
        });
        return arr;
    }
}

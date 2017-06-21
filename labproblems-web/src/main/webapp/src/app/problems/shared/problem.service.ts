import {Injectable} from '@angular/core';
import {Http, Response, Headers} from "@angular/http";

import {Problem} from "./problem.model";

import {Observable} from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


@Injectable()
export class ProblemService {
    private problemsUrl = 'http://localhost:8080/api/problems';
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) {
    }

    getProblems(): Observable<Problem[]> {
        //noinspection TypeScriptUnresolvedFunction
      return this.http.get(this.problemsUrl)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response) {
        let body = res.json();
        return body.problems || {};
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

    getProblem(id: number): Observable<Problem> {
      //noinspection TypeScriptUnresolvedVariable,TypeScriptUnresolvedFunction
      return this.getProblems()
            .map(problems => problems.find(problem => problem.id === id));
    }
    update(problem): Observable<Problem> {
        const url = `${this.problemsUrl}/${problem.id}`;
        //noinspection TypeScriptUnresolvedVariable,TypeScriptUnresolvedFunction
        return this.http
            .put(url, JSON.stringify({"problem": problem}), {headers: this.headers})
            .map(this.extractProblemData)
            .catch(this.handleError);
    }

    private extractProblemData(res: Response) {
        let body = res.json();
        return body.problem || {};
    }

    create(number: string, statement: string): Observable<Problem> {
        let problem = {number, statement};
        //noinspection TypeScriptUnresolvedVariable,TypeScriptUnresolvedFunction
        return this.http
            .post(this.problemsUrl, JSON.stringify({"problem": problem}), {headers: this.headers})
            .map(this.extractProblemData)
            .catch(this.handleError);
    }

    delete(id: number): Observable<void> {
        const url = `${this.problemsUrl}/${id}`;
        //noinspection TypeScriptUnresolvedVariable,TypeScriptUnresolvedFunction
        return this.http
            .delete(url, {headers: this.headers})
            .map(() => null)
            .catch(this.handleError);
    }

}

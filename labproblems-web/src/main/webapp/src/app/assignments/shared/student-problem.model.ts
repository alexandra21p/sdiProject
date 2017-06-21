export class StudentProblem {

    studentId: number;
    problemId: number;
    problemNumber: string;
    grade: number;

    constructor(studentId: number, disciplineId: number, problemNumber: string, grade: number) {
        this.studentId = studentId;
        this.problemId = disciplineId;
        this.problemNumber = problemNumber;
        this.grade = grade;
    }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private _http : HttpClient) { }
  
    //load all the quizes
    public quizzes()
    {
      return this._http.get(`${baseUrl}/quiz/`);
    }

    //add quiz
    public addQuiz(quiz:any)
    {
      return this._http.post(`${baseUrl}/quiz/`, quiz);
    }

    //delete quiz
    public deleteQuiz(quizid:any)
    {
      return this._http.delete(`${baseUrl}/quiz/${quizid}`, quizid);
    }

    //get a single quiz
    public getQuiz(quizid : any)
    {
      return this._http.get(`${baseUrl}/quiz/${quizid}`)
    }

    //update quiz
    public updateQuiz(quiz:any)
    {
      return this._http.put(`${baseUrl}/quiz/`, quiz);
    }

    //getQuizzesOfCategory
    public getQuizzesOfCategory(cid:any)
    {
      return this._http.get(`${baseUrl}/quiz/category/${cid}`);
    }

    //getAllActice
    public getAllActiveQuiz()
    {
      return this._http.get(`${baseUrl}/quiz/active`);
    }

    //getActiveQuizOfCategory
    public getActiveQuizOfCategory(cid:any)
    {
      return this._http.get(`${baseUrl}/quiz/category/active/${cid}`)
    }
}

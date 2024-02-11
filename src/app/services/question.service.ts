import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private _http:HttpClient) { }

  //get 
  public getQuestionsOfQuiz(quizId:any)
  {
    return this._http.get(`${baseUrl}/question/quiz/all/${quizId}`);
  }

  //post
  public addQuestion(question:any)
  {
    return this._http.post(`${baseUrl}/question/`,question); 
    //this will return observeable and now we subscribe to see the data
  }

  //delete the question
  public deleteQuestion(questionId:any)
  {
    return this._http.delete(`${baseUrl}/question/${questionId}`);
  }

  //update the Question
  public updateQuestion(question:any)
  {
    return this._http.put(`${baseUrl}/question/`,question);
  }

    //get 
    public getQuestionsOfQuizForTest(quizId:any)
    {
      return this._http.get(`${baseUrl}/question/quiz/${quizId}`);
    }

    //evalQuiz
    public evalQuiz(questions:any)
    {
      return this._http.post(`${baseUrl}/question/eval-quiz`,questions);
    }

}

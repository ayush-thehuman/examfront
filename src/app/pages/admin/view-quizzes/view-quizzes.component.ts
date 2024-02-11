import { Component, OnInit } from '@angular/core';
import { QuizService } from '../../../services/quiz.service';
import { Console, error } from 'console';
import Swal, { SweetAlertResult } from 'sweetalert2';

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrl: './view-quizzes.component.css'
})
export class ViewQuizzesComponent implements OnInit {

  
quizId: any;

  
  
  //delete quiz
  deleteQuiz(quizid: any) {

    Swal.fire({
      icon: 'info',
      title: 'Are you sure ?',
      confirmButtonText: 'Delete',
      showCancelButton: true,
    }).then((result)=>{

      if(result.isConfirmed)
      {
        //delete ..
        this._quiz.deleteQuiz(quizid).subscribe
      (
        (data: any) => {
          this.quizzess = this.quizzess.filter((quiz) => quiz.quizId != quizid);
          //console.log(this.deleteQuiz);
          Swal.fire('Success !', "Quiz Deleted Successfully !", 'success');
        },
        (error) => {
          console.log(error);
          Swal.fire('Error !', 'Error in deleting quiz !', 'error');
        }
      );
      }
    })
  }

  constructor(private _quiz: QuizService) { }

  quizzess = [
    {
      quizId: '',
      quizTitle: '',
      quizDescription: '',
      maxMarks: '',
      noOfQuestions: '',
      quizActive: '',
      category: {
        title: ''
      }
    }
  ]
  ngOnInit(): void {

    this._quiz.quizzes().subscribe(
      (data: any) => {
        this.quizzess = data;
        console.log(this.quizzess);
      },
      (error) => {
        console.log(error);
        Swal.fire('Error !', 'Error in loading data !', 'error');

      }
    );
  }
}

function result(value: SweetAlertResult<any>): SweetAlertResult<any> | PromiseLike<SweetAlertResult<any>> {
  throw new Error('Function not implemented.');
}


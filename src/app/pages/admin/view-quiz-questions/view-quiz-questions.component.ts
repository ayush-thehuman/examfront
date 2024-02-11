import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuizService } from '../../../services/quiz.service';
import { QuestionService } from '../../../services/question.service';
import Swal from 'sweetalert2';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-view-quiz-questions',
  templateUrl: './view-quiz-questions.component.html',
  styleUrl: './view-quiz-questions.component.css'
})
export class ViewQuizQuestionsComponent implements OnInit {

  quizId: any;
  quizTitle: any;

  questions!: any[];

  constructor(private _route: ActivatedRoute, private _ques: QuestionService, private _snack: MatSnackBar) { }

  ngOnInit(): void {
    this.quizId = this._route.snapshot.params['quizId'];
    this.quizTitle = this._route.snapshot.params['quizTitle'];
    //alert(this.quizId);
    //alert(this.quizTitle);

    this._ques.getQuestionsOfQuiz(this.quizId).subscribe(
      (data: any) => {
        this.questions = data;
        console.log(this._ques);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  //delete Question
  deleteQuestion(quesId: any) {
    //alert(quesId);
    Swal.fire({
      icon: 'info',
      showCancelButton: true,
      confirmButtonText: 'Delete',
      title: 'Are you sure, want to delete this Question?',
    }).then((result) => {

      if (result.isConfirmed) {
        //confirm
        this._ques.deleteQuestion(quesId).subscribe(
          (data: any) => {
            this._snack.open('Question Deleted', '', {
              duration: 3000,
            });
            this.questions = this.questions.filter((q) => q.quesId != quesId)
          },
          (error) => {
            this._snack.open("Error in deleting Question !", '', {
              duration: 3000,
            });
            console.log(error);
          });
      }
    });
  }
}

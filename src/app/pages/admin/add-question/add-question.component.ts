import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from '../../../services/question.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrl: './add-question.component.css'
})
export class AddQuestionComponent implements OnInit {

  
  quizId: any;
  quizTitle: any;

  question = {
    quiz: {
      quizId: '',
    },
    content: '',
    option1: '',
    option2: '',
    option3: '',
    option4: '',
    quesAnswer: '',
  };

  constructor(private _route: ActivatedRoute, private _ques: QuestionService, private _snack: MatSnackBar) { }


  ngOnInit(): void {

    this.quizId = this._route.snapshot.params['quizId'];
    this.question.quiz['quizId'] = this.quizId;

    this.quizTitle = this._route.snapshot.params['quizTitle'];
    //this.question.quiz['quizTitle'] = this.quizTitle;
  }

  postQuestionSubmit() {
    if (this.question.content.trim() == '' || this.question.content == null) {
      this._snack.open("Content Required !!", '', {
        duration: 3000
      })
      return;
    }
    if (this.question.content.trim() == '' || this.question.option1 == null) {
      this._snack.open("option1 Required !!", '', {
        duration: 3000
      })
      return;
    }
    if (this.question.content.trim() == '' || this.question.option2 == null) {
      this._snack.open("option2 Required !!", '', {
        duration: 3000
      })
      return;
    }
    if (this.question.content.trim() == '' || this.question.quesAnswer == null) {
      this._snack.open("Answer Required !!", '', {
        duration: 3000
      })
      return;
    }
    

    //all done
    this._ques.addQuestion(this.question).subscribe(
      (data: any) => {
        this.question.content = '',
          this.question.option1 = '',
          this.question.option2 = '',
          this.question.option3 = '',
          this.question.option4 = '',
          this.question.quesAnswer = '',
          this.question.quiz.quizId = '',
          Swal.fire("Success !!", 'Question is added, Add Another One', 'success');
      },
      (error) => {
        Swal.fire("Error !",'Question Not Added !', error);
      }
    )
  }
}

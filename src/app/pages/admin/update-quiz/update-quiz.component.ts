import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { QuizService } from '../../../services/quiz.service';
import { CategoryService } from '../../../services/category.service';
import Swal from 'sweetalert2';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from 'express';

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrl: './update-quiz.component.css'
})
export class UpdateQuizComponent implements OnInit {

  constructor(
    private _route: ActivatedRoute,
    private _quiz: QuizService,
    private _category: CategoryService,
    private _snack: MatSnackBar,
    //private _router: Router,
  ) { }

  color = 'accent';
  checked = false;
  disabled = false;

  categories: any;

  quizId = 0;
  
  quiz: any;

  ngOnInit(): void {

    this.quizId = this._route.snapshot.params['quizId'];
    //alert(this.quizId)
    this._quiz.getQuiz(this.quizId).subscribe(
      (data: any) => {
        this.quiz = data;
        console.log(this.quiz);
      },
      (error) => {
        console.log(error);
      }
    );

    //This method is used to show all the categories 

    this._category.categories().subscribe(
      (data: any) => {
        this.categories = data;
        //console.log(this.categories);
        //Swal.fire("Success !!", 'Quiz is added successfully', 'success');
      },
      (error) => {
        console.log(error);
        Swal.fire("Error !!", 'Server Error !', 'error');
      }
    )
  }

  //update form submit
  updateFormSubmit() {
    if (this.quiz.quizTitle.trim() == '' || this.quiz.quizTitle == null) {
      this._snack.open("Title Required !!", '', {
        duration: 3000
      })
      return;
    }

    //all done
    this._quiz.updateQuiz(this.quiz).subscribe(
      (data) => {
        Swal.fire("Success !!", 'Quiz is Updated successfully', 'success');
        // .then((e)=>{
        //   this._router.navigate(['/admin/quizzes']);
        // });

      },
      (error) => {
        console.log(error);
        Swal.fire("Error !!", 'Server Error !', 'error');
      }
    );

  }

}

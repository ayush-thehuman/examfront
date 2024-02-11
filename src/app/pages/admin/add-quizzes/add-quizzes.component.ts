import { Component, OnInit } from '@angular/core';
import { QuizService } from '../../../services/quiz.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
import { CategoryService } from '../../../services/category.service';

@Component({
  selector: 'app-add-quizzes',
  templateUrl: './add-quizzes.component.html',
  styleUrl: './add-quizzes.component.css'
})
export class AddQuizzesComponent implements OnInit {

  categories:any;

  quizData = 
  {
    quizTitle: '',
      quizDescription: '',
      maxMarks: '',
      noOfQuestions: '',
      quizActive: 'true',
      category: {
        cid:''
      },
  };

  color = 'accent';
  checked = false;
  disabled = false;

  constructor(private _category: CategoryService, private _quiz: QuizService, private _snack: MatSnackBar) { }

  ngOnInit(): void { 
    
    this._category.categories().subscribe(
      (data : any)=>{
        this.categories=data;
        //console.log(this.categories);
        //Swal.fire("Success !!", 'Quiz is added successfully', 'success');
      },
      (error) => {
        console.log(error);
        Swal.fire("Error !!", 'Server Error !', 'error');
      }
    )
  }

  formSubmit() {
    if (this.quizData.quizTitle.trim() == '' || this.quizData.quizTitle == null) {
      this._snack.open("Title Required !!", '', {
        duration: 3000
      })
      return;
    }

    //all done
    this._quiz.addQuiz(this.quizData).subscribe(
      (data: any) => {
        this.quizData.quizTitle = '',
          this.quizData.quizDescription = '',
          this.quizData.maxMarks = '',
          this.quizData.noOfQuestions = '',
          this.quizData.quizActive = '',
          this.quizData.category.cid='',
        Swal.fire("Success !!", 'Quiz is added successfully', 'success');
      },
      (error) => {
        console.log(error);
        Swal.fire("Error !!", 'Server Error !', 'error');
      }
    )
  }
}

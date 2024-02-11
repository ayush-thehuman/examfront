import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuizService } from '../../services/quiz.service';
import { error } from 'console';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-load-quiz',
  templateUrl: './load-quiz.component.html',
  styleUrl: './load-quiz.component.css'
})
export class LoadQuizComponent {

  catId: any;
  quizzess: any;
  
  constructor(private _route: ActivatedRoute, private _quiz : QuizService) { }

  ngOnInit(): void {

    this._route.params.subscribe((params)=>{
      this.catId = params['catId'];

      if (this.catId == 0) {
        console.log("Load all the quiz");
  
        this._quiz.getAllActiveQuiz().subscribe(
          (data:any)=>{
            this.quizzess = data;
            console.log(this.quizzess);
          },
          (error)=>{
            console.log(error);
            Swal.fire('Error');
          }
        )
      }
      else {
        console.log("Load Specific quiz");
        
        this._quiz.getActiveQuizOfCategory(this.catId).subscribe(
          (data:any)=>{
            this.quizzess = data;
            console.log(this.quizzess); 
          },
          (error)=>{
            console.log(error);
            Swal.fire('Error');
          }
        )
      }
  
    });
  }

}

import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from '../../services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-start-quiz',
  templateUrl: './start-quiz.component.html',
  styleUrl: './start-quiz.component.css',
})
export class StartQuizComponent implements OnInit {
  qid: any;
  ques: any;
  marksGot = 0;
  correctAnswers = 0;
  attempted = 0;
  isSubmit = false;
  timer: any;

  constructor(
    private locationSt: LocationStrategy,
    private _route: ActivatedRoute,
    private _question: QuestionService,
  ) {}

  ngOnInit(): void {
    this.preventBackButton();
    this.qid = this._route.snapshot.params['qId'];
    console.log(this.qid);
    this.loadQuestions();
  }
  loadQuestions() {
    this._question.getQuestionsOfQuizForTest(this.qid).subscribe(
      (data: any) => {
        this.ques = data;

        this.timer = this.ques.length * 2 * 60;

        this.ques.forEach((q: any) => {
          q['givenAnswer'] = '';
        });

        console.log(this.ques);
        this.startTimer();
      },
      (error) => {
        Swal.fire('Error', 'Error in loading..', 'error');
      },
    );
  }

  preventBackButton() {
    history.pushState(null, location.href);
    this.locationSt.onPopState(() => {
      history.pushState(null, location.href);
    });
  }

  submitQuiz() {
    Swal.fire({
      title: 'DO you want to submit the quiz?',
      showCancelButton: true,
      confirmButtonText: 'Submit',
      icon: 'info',
    }).then((e) => {
      if (e.isConfirmed) {
        //calculation
        this.evalQuizOfQuestions();
      }
    });
  }

  startTimer() {
    let t = window.setInterval(() => {
      //code
      if (this.timer <= 0) {
        this.evalQuizOfQuestions();
        clearInterval(t);
      } else {
        this.timer--;
      }
    }, 1000);
  }

  getFormattedTime() {
    let minutes = Math.floor(this.timer / 60);
    let sec = this.timer - minutes * 60;
    return `${minutes} min : ${sec} sec`;
  }

  evalQuizOfQuestions() {
    //call server to evaluate quiz
    //backend calculation
    this._question.evalQuiz(this.ques).subscribe(
      (data: any) => {
        console.log(data);
        this.marksGot = data.marksGot;
        this.correctAnswers = data.correctAnswers;
        this.attempted = data.attempted;
        this.isSubmit = true;
      },
      (error: any) => {
        console.log(error);
      },
    );
  }

  printPage() {
    window.print();
  }
}

import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})

export class SignupComponent {



  constructor(private userService: UserService, private _snack: MatSnackBar) { }

  public user =
    {
      username: '',
      password: '',
      firstName: '',
      lastName: '',
      email: '',
      mobileNo: '',
    }

  formSubmit() {
    //alert('submit');

    // For printing the values 
    console.log(this.user);
    if (this.user.username == '' || this.user.username == null) {
      //alert('User is required!!');
      this._snack.open('User Name is required !! ', 'OK', {
        duration: 3000
      });
      return;
    }

    //Validation
    if (this.user.password == null) {
      this._snack.open('Password should be at least 8 characters long and should contain one number,one character and one special character!!', 'OK', {
        duration: 3000
      });
      return;
    }

    //add normal user : userService
    this.userService.addNormalUser(this.user).subscribe(
      (data: any) => {
        //success
        console.log(data);
        //alert('success');
        Swal.fire('Successfully done !!', 'User id is ' + data.userId, 'success')
      },

      (error) => {
        //error
        console.log(error);
        //alert('something went wrong');
        this._snack.open('User Already Exit ! ', 'OK', {
          duration: 3000
        })
      }
    )
  }



}

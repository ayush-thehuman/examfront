import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubject = new Subject<boolean>();

  constructor(private Http: HttpClient) { }

  //current user: which is loggedin
  public getCurrentUser()
  {
    return this.Http.get(`${baseUrl}/current-user`)
  }
  //generate token
  public generateToken(loginData: any)
  {
    return this.Http.post(`${baseUrl}/generate-token`,loginData);
  }

  //login user: set token in localStorage
  public loginUser(token:any)
  {
    localStorage.setItem('token',token);
   // this.loginStatusSubject.next(true);
    return true;
  }

  //islogin: user is Logges in or not
  public isLoggedIn()
  {
    let tokenStr = localStorage.getItem('token');
    if(tokenStr == undefined || tokenStr == '' || tokenStr == null)
    {
      return false;
    }
    else
    {
      return true;
    }
  }

  //logout : remove token from local storage
  public logout()
  {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  } 

  //getToken : which returns token
  public getToken()
  {
    return localStorage.getItem('token');
  }

  //set userDetail 
  public setUser(user:any)
  {
    localStorage.setItem('user',JSON.stringify(user)); //1.convert data to string the sotre in localStorage. 
  }

  //getUser
  public getUser()
  {
    let userStr = localStorage.getItem('user');
    if(userStr != null)
    {
      return JSON.parse(userStr);
    }
    else
    {
      this.logout();
      return null;
    }
  }

  //get user role
  public getUserRole()
  {
    let user = this.getUser();
    return user.authorities[0].authority;
  }
}

import { TestBed } from '@angular/core/testing';
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';

import { normalGuard } from './normal.guard';
import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn : 'root'
})
export class NormalGuard implements CanActivate{

  constructor(private login:LoginService, private router:Router){ }

  canActivate(
    route: ActivatedRouteSnapshot, 
    state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
  
    if(this.login.isLoggedIn() && this.login.getUserRole() == 'NORMAL')
    {
      return true;
    }

    this.router.navigate(['login']);

    return false;
    
  }
};

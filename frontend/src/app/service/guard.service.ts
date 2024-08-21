import { CanActivateFn, Router } from "@angular/router";
import { ApiService } from "./api.service";
import { inject } from "@angular/core";



export const userGuard: CanActivateFn = (route, state) => {
  if (inject(ApiService).isAuthenticated()) {
    return true;
  }else{
    inject(Router).navigate(['/login'])
    return false;
  }
};



export const adminGuard: CanActivateFn = (route, state) => {
  if (inject(ApiService).isAdmin()) {
    return true;
  }else{
    inject(Router).navigate(['/login'])
    return false;
  }
};

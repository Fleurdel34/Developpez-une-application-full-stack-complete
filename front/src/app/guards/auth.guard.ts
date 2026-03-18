import { inject } from '@angular/core';
import { CanActivateFn, CanActivateChildFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = () => {
  const router = inject(Router);
  const token =localStorage.getItem('token');

  if(token){
    return true;
  }
  router.navigateByUrl('');
  return false;
};

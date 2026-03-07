import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

    if(req.url.includes('/api/login') || req.url.includes('/api/register')){
        return next(req);
    }

    const token = localStorage.getItem('token');

    if(token){
        req = req.clone({
        setHeaders: {
            Authorization: `Bearer ${token}`}
        });
    }
    return next(req);
};

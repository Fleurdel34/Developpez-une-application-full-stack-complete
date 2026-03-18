import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'register', component: RegisterComponent},
  {path:'login', component:LoginComponent},
  {path: 'dashboard', loadComponent: () => 
    import('./pages/dashboard/dashboard.component')
    .then(m => m.DashboardComponent),
    canActivate: [authGuard],
    canActivateChild: [authGuard],
    children: [ 
      {path:'theme', loadComponent: () => 
        import('./pages/topic-list/topic-list.component')
        .then(m => m.TopicListComponent)},
      {path:'me', loadComponent: () => 
        import('./components/me/me.component')
        .then(m => m.MeComponent)
      },
      {path:'article', loadComponent: () => 
        import('./pages/article/list/list.component')
        .then(m => m.ListComponent)
      },
      {path:'article/detail/:id', loadComponent: () => 
        import('./pages/article/detail/detail.component')
        .then(m => m.DetailComponent)
      },
      {path:'article/form-article', loadComponent: () => 
        import('./pages/article/form-article/form-article.component')
        .then(m => m.FormArticleComponent)
      },
      {
      path: '',
      redirectTo: 'article',
      pathMatch: 'full'
    }
    ]
  }
];


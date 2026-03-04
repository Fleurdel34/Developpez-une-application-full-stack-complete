import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { RegisterComponent } from './pages/register/register.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'register', component: RegisterComponent},
  {path: 'dashboard', loadChildren: () => import('./pages/dashboard/dashboard.component').then(m => m.DashboardComponent),
    children: [ 
      {path:'topic', loadChildren: () => import('./pages/topic-list/topic-list.component').then(m => m.TopicListComponent)},
      {path:'me', loadChildren: () => import('./components/me/me.component').then(m => m.MeComponent)},
    ]
  }
];


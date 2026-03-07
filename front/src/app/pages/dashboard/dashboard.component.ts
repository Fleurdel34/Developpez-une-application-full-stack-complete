import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {
    private router = inject(Router);


    logout() {
      this.router.navigateByUrl('');
      localStorage.removeItem('token');
    }
    onMeClick() {
      this.router.navigateByUrl('/dashboard/me');
    }
    onThemesClick() {
      this.router.navigateByUrl('/dashboard/theme');
    }
    onArticlesClick() {
      this.router.navigateByUrl('/dashboard/article');
    }
}

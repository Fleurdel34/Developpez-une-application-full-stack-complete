import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  imports: [RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {
  private router = inject(Router);
  menuOpen=false;

  logout() {
    this.router.navigateByUrl('');
    localStorage.removeItem('token');
  };

  onMeClick() {
    this.router.navigateByUrl('/dashboard/me');
  };

  onThemesClick() {
    this.router.navigateByUrl('/dashboard/theme');
  };

  onArticlesClick() {
    this.router.navigateByUrl('/dashboard/article');
  };

  openMenu(){
    this.menuOpen=!this.menuOpen;
  };
}

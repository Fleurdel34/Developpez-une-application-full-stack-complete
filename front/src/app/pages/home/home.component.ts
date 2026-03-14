import { Component, OnInit, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';


@Component({
    selector: 'app-home',
    standalone: true,
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss'],
    imports: [MatButtonModule]
})
export class HomeComponent implements OnInit {
  private router = inject(Router);

  ngOnInit(): void {}

  register() {
    this.router.navigateByUrl('/register');
  }

  login() {
    this.router.navigateByUrl('/login');
  }
}

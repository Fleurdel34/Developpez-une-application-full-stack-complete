import { Component, OnInit, inject, DestroyRef } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import {takeUntilDestroyed} from '@angular/core/rxjs-interop';
import { RegisterService } from 'src/app/core/services/register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule,],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent implements OnInit {

  private registerService = inject(RegisterService)
  private router = inject(Router);
  private destroyRef = inject(DestroyRef);
  private formBuilder = inject(FormBuilder);
  
  registerForm!: FormGroup;
  

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required, Validators.minLength(8), Validators.pattern(/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,}$/)]]
    });
  }

  onSubmit(): void {
    const formValue = this.registerForm.value;
    if (this.registerForm.valid) {
      this.registerService.register(formValue)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
          next: () => this.router.navigate(['/login']),
          error: (error) => console.error("Error during registration:", error)
        });

    }
  }
}

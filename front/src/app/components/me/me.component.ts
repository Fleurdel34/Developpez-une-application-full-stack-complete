import { Component, DestroyRef, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from 'src/app/core/services/user.service';
import { Router } from '@angular/router';
import { User } from 'src/app/core/models/user.interface';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { TopicService } from 'src/app/core/services/topic.service';
import { Topic } from 'src/app/core/models/topic.interface';
import { TopicCardComponent } from "../topic-card/topic-card.component";

@Component({
  selector: 'app-me',
  imports: [ReactiveFormsModule, TopicCardComponent],
  templateUrl: './me.component.html',
  styleUrl: './me.component.scss'
})
export class MeComponent {
  private userService = inject(UserService);
  private topicService = inject(TopicService);
  private router = inject(Router);
  private destroyRef = inject(DestroyRef);
  private formBuilder = inject(FormBuilder);
  
  updateForm!: FormGroup;
  user!: User;
  topics!:Topic[];

  /* user data reception with their subscription*/

  ngOnInit(): void {
    this.userService.getUserAuth()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe(data => {
        this.user = data;
        this.updateForm = this.formBuilder.group({
        username: [this.user.username, [Validators.required]],
        email: [this.user.email, [Validators.required, Validators.email]],
        password: [null, [Validators.required, Validators.minLength(8), Validators.pattern(/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,}$/)]]
      });
    });
    this.topicService.getAll().pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe(data => {
        this.topics = data;
        if(this.topics.length > 0 && this.user){
          const suscribedTopics = this.topics.filter(topic => 
            topic.subscriptions?.some(subscription => subscription.userId === this.user.id));
          this.topics = suscribedTopics;
        }
      });
  }

  /*update data user with a form*/

  onSubmit(): void {
    const formValue = this.updateForm.value;
    if (this.updateForm.valid) {
      this.userService.update(formValue)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe(
      {
        next: () => {
          this.router.navigate(['/dashboard/article']);
        },
        error: (error) => console.error(error)
      }
      )
    }
  }

}

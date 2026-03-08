import { Component,OnInit, inject, DestroyRef } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { ArticleService } from 'src/app/core/services/article.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {takeUntilDestroyed} from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-form-article',
  imports: [ReactiveFormsModule],
  templateUrl: './form-article.component.html',
  styleUrl: './form-article.component.scss'
})
export class FormArticleComponent implements OnInit {


    private articleService = inject(ArticleService)
    private router = inject(Router);
    private destroyRef = inject(DestroyRef);
    private formBuilder = inject(FormBuilder);
    
    articleForm!: FormGroup;
    
  
    ngOnInit(): void {
      this.articleForm = this.formBuilder.group({
        topic: [null, [Validators.required]],
        title: [null, [Validators.required]],
        content: [null, [Validators.required]]
      });
    }
  
    onSubmit(): void {
      const formValue = this.articleForm.value;
      if (this.articleForm.valid) {
        this.articleService.createArticle(formValue)
        .pipe(takeUntilDestroyed(this.destroyRef))
        .subscribe({
            next: () => this.router.navigate(['/articles']),
            error: (error: Error) => console.error("Error creating article:", error)
        });
      }
    }

}

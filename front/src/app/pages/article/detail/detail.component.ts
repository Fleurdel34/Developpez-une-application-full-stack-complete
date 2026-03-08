import { Component, DestroyRef, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import {Article} from 'src/app/core/models/article.interface';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { CommentService } from 'src/app/core/services/comment.service';
import { ArticleService } from 'src/app/core/services/article.service';

@Component({
  selector: 'app-detail',
  imports: [DatePipe, ReactiveFormsModule],
  templateUrl: './detail.component.html',
  styleUrl: './detail.component.scss'
})
export class DetailComponent implements OnInit{

  private router = inject(Router);
  private formBuilder = inject(FormBuilder);
  private destroyRef = inject(DestroyRef);
  private commentService = inject(CommentService);
  private route = inject(ActivatedRoute);
  private articleService = inject(ArticleService);
  public article!: Article;
  public commentForm!:FormGroup;
  public idArticle!: number;

  ngOnInit(): void {
    const id= this.route.snapshot.paramMap.get('id');
    this.idArticle = parseInt(id!);
    this.articleService.getById(this.idArticle)
    .pipe(takeUntilDestroyed(this.destroyRef))
    .subscribe({
      next: (article: Article) => this.article = article,
      error: (error: Error) => console.error("Error fetching article:", error)
    });



    this.commentForm = this.formBuilder.group({
      content: [null, [Validators.required]]
    });
  }

  onSubmit() {
    const id= this.idArticle;
    const formValue = this.commentForm.value;
      if (this.commentForm.valid) {
        this.commentService.createComment(formValue, id)
        .pipe(takeUntilDestroyed(this.destroyRef))
        .subscribe({
            next: () => this.router.navigate(['/articles']),
            error: (error: Error) => console.error("Error creating comment:", error)
        });
      }
  }

  back() {
    this.router.navigateByUrl('/articles');
  }

}

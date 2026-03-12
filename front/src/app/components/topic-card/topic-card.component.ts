import { TitleCasePipe } from '@angular/common';
import { Component, Input, OnInit, inject } from '@angular/core';
import { Topic } from 'src/app/core/models/topic.interface';
import { SubscriptionService } from 'src/app/core/services/subscription.service';


@Component({
  selector: 'app-topic-card',
  imports: [TitleCasePipe,],
  templateUrl: './topic-card.component.html',
  styleUrl: './topic-card.component.scss'
})
export class TopicCardComponent implements OnInit{

@Input() topic!: Topic;
@Input() userId!: number;
@Input() isPageProfil=false;
subscribed: String = "";

private subscriptionService = inject(SubscriptionService)

ngOnInit(): void {
  if(this.isPageProfil){
    this.subscribed="Se désabonner";
  }else{
    this.subscribed= "S'abonner"
  }


}

subscribe() {
    if(this.isPageProfil){
      this.subscriptionService.unsubscribe(this.userId, this.topic.id).subscribe();
      this.subscribed = "S'abonner";
    }else{
      if(this.subscribed === "S'abonner") {
        this.subscriptionService.subscribe(this.userId, this.topic.id).subscribe();
        this.subscribed = "Déjà abonné";
      }
    }
  }
} 
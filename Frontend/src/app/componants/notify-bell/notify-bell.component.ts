import {Component, OnDestroy, OnInit} from '@angular/core';
import {Notification} from '../../../model/notification';
import {NotifyService} from '../../../service/notify.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-notify-bell',
  templateUrl: './notify-bell.component.html',
  styleUrls: ['./notify-bell.component.css']
})
export class NotifyBellComponent implements OnInit, OnDestroy {
  notifications: Notification[];
  unreadCount=0;
  private subscription!: Subscription;

  constructor(private notifyService: NotifyService) { }

  ngOnInit(): void {
    this.getAllNotifies();
    this.subscription = this.notifyService.refreshNeeded$.subscribe(() => {
      this.getAllNotifies();
    });

  }

  getAllNotifies(): void {
    this.notifyService.getAllNotifications().subscribe(
      response => {this.notifications=response
        this.unreadCount=response.length
      },error => {
        console.error('Error loading notifications:', error)
      }
    )
  }
  ngOnDestroy() {
    this.subscription?.unsubscribe();
  }

  readAll(){
    this.unreadCount=this.unreadCount*-1;
  }
}

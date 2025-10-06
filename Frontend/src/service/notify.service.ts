import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Notification} from '../model/notification';
import {map} from 'rxjs/operators';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotifyService {
private baseUrl ="http://localhost:8080/api/v1/notification/get-All-Notification"

  private refreshSubject = new Subject<void>();
  refreshNeeded$ = this.refreshSubject.asObservable();
  constructor(private http: HttpClient) { }

  getAllNotifications(){
  return this.http.get<Notification[]>(this.baseUrl).pipe(
    map(
      response => response
    )
  )
  }
  triggerRefresh() {
    this.refreshSubject.next();
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Log } from '../model';

@Injectable({
  providedIn: 'root'
})
export class LogService {
  private baseURL = "http://localhost:8080/logs";

  constructor(private httpClient: HttpClient) { }
  
  getLogsList(): Observable<Log[]>{
    return this.httpClient.get<Log[]>(`${this.baseURL}`);
  }

  createLog(employee: Log): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, employee);
  }

  getLogById(id: number): Observable<Log> {
    return this.httpClient.get<Log>(`${this.baseURL}/${id}`);
  }

  updateLog(id:number, employee: Log): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/${id}`, employee);
  }

  deleteLog(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}

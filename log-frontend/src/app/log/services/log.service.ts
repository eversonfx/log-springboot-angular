import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Log } from '../model';

@Injectable({
  providedIn: 'root'
})
export class LogService {
  private baseURL = "http://localhost:8080/logs";

  constructor(private httpClient: HttpClient) { }

  // getLogsList(): Observable<Log[]>{
  //   return this.httpClient.get<Log[]>(`${this.baseURL}`);
  // }

  getLogsList(begin?: number, end?: number): Observable<Log[]> {
    let params = new HttpParams();
    params = params.append('begin', begin);
    params = params.append('end', end);
    return this.httpClient.get<Log[]>(`${this.baseURL}/page-interval`, { params: params });
  }

  searchLogs(searchText: string): Observable<Log[]> {
    let params = new HttpParams();
    params = params.append('search', searchText);
    return this.httpClient.get<Log[]>(`${this.baseURL}/search-logs`, { params: params });
  }

  createLog(employee: Log): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, employee);
  }

  getLogById(id: number): Observable<Log> {
    return this.httpClient.get<Log>(`${this.baseURL}/${id}`);
  }

  updateLog(id: number, employee: Log): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/${id}`, employee);
  }

  deleteLog(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }

  getTotalNumReg(): Observable<number> {
    return this.httpClient.get<number>(`${this.baseURL}/pages-info`);
  }

}

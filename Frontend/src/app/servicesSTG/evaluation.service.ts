import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Evaluation } from '../modelSTG/evaluation';
import { Observable, map } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class EvaluationService {
  private baseUrl=environment.baseurl+"/evaluation"
  constructor(private router : Router,private httpClient: HttpClient) { }

    addEvaluation(addEvaluation : Evaluation):Observable<Evaluation>{
      console.log("fffffffffffffff",addEvaluation)
      const url=this.baseUrl+"/add"
      return this.httpClient.post<Evaluation>(url,addEvaluation)
    }

    updateEvaluation(updateEvaluation : Evaluation):Observable<Evaluation>{
      console.log("fffffffffffffff",updateEvaluation)
      const url=this.baseUrl+"/update"
      return this.httpClient.post<Evaluation>(url,updateEvaluation)
    }


    finddpetById(id: number): Observable<Evaluation> {
      return this.httpClient.get<Evaluation>(`${this.baseUrl}/findByIdPostule/${id}`)

    }
    listeallEvaluationByRESP(id: number): Observable<Evaluation[]> {
      return this.httpClient.get<Evaluation[]>(`${this.baseUrl}/listeallbyresp/${id}`)
      .pipe(
        map((response:any) => response as Evaluation[])
      );

    }

    getList(): Observable<Evaluation[]> {
      return this.httpClient.get<Evaluation[]>(`${this.baseUrl}/listeall`)
      .pipe(
        map((response:any) => response as Evaluation[])
      );

    }

    deleteById(id: number): Observable<void> {
      return this.httpClient.delete<void>(`${this.baseUrl}/delete/${id}`)

    }


  }

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Competence } from '../modelSTG/competence';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompetenceService   {
  private baseUrl=environment.baseurl+"/competence"

  constructor ( private router : Router,private httpClient: HttpClient)
  { }

  addCompetence(addCompetence : Competence):Observable<Competence>{
    console.log("fffffffffffffff",addCompetence)
    const url=this.baseUrl+"/add"
    return this.httpClient.post<Competence>(url,addCompetence)
  }

  updateCompetence(addCompetence : Competence):Observable<Competence>{
    console.log("fffffffffffffff",addCompetence)
    const url=this.baseUrl+"/update"
    return this.httpClient.post<Competence>(url,addCompetence)
  }

  finddpetById(id: number): Observable<Competence> {
    return this.httpClient.get<Competence>(`${this.baseUrl}/findByIdCompetence/${id}`)

  }
  getList(): Observable<Competence[]> {
    return this.httpClient.get<Competence[]>(`${this.baseUrl}/listeall`)
    .pipe(
      map((response:any) => response as Competence[])
    );

  }

  deleteById(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/delete/${id}`)

  }
}

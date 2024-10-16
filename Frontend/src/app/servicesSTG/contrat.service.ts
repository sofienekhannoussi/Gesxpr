import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Contrat } from '../modelSTG/contrat';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContratService {

  private baseUrl=environment.baseurl+"/contrat"
  constructor(private router : Router,private httpClient: HttpClient) { }


  addContrat(addContrat : Contrat):Observable<Contrat>{
    const url=this.baseUrl+"/save"
    return this.httpClient.post<Contrat>(url,addContrat)
  }

  findContratById(id: number): Observable<Contrat> {
    return this.httpClient.get<Contrat>(`${this.baseUrl}/findByIdContrat/${id}`)

  }

  findContratByIdExpert(id: number): Observable<Contrat[]> {
    return this.httpClient.get<Contrat[]>(`${this.baseUrl}/findallContratbyexpert/${id}`)

  }

  findContratByIdResp(id: number): Observable<Contrat[]> {
    return this.httpClient.get<Contrat[]>(`${this.baseUrl}/findallContratbyresp/${id}`)

  }

  getListContrat(): Observable<Contrat[]> {
    return this.httpClient.get<Contrat[]>(`${this.baseUrl}/findall`)
    .pipe(
      map((response:any) => response as Contrat[])
    );

  }

  deleteByIdContrat(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/delete/${id}`)

  }
  uploadCoursDtoFile(id: number, image: File): Observable<Contrat> {
    const formData: FormData = new FormData();
    formData.append('image', image);
    return this.httpClient.post<Contrat>(`${this.baseUrl}/uploadFile/${id}`, formData);
  }

  countContrat(): Observable<number> {
    return this.httpClient.get<number>(`${this.baseUrl}/countAll`)

  }

  countContratbyexpert(id:number): Observable<number> {
    return this.httpClient.get<number>(`${this.baseUrl}/NombreContratbyexpert/${id}`)

  }

  countContratbyresp(id:number): Observable<number> {
    return this.httpClient.get<number>(`${this.baseUrl}/NombreContratbyresp/${id}`)

  }
}

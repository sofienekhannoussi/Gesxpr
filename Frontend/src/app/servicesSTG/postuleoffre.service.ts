import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Postuleoffre } from '../modelSTG/postuleoffre';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OffreService {
idoff!:number
  private baseUrl=environment.baseurl+"/offre"
  constructor(private router : Router,private httpClient: HttpClient) { }


  addoffre(addoffre : Postuleoffre):Observable<Postuleoffre>{
    console.log("fffffffffffffff",addoffre)
    const url=this.baseUrl+"/add"
    return this.httpClient.post<Postuleoffre>(url,addoffre)
  }

  finddpetById(id: number): Observable<Postuleoffre> {
    return this.httpClient.get<Postuleoffre>(`${this.baseUrl}/findByIdPostule/${id}`)

  }
  getList(id: number): Observable<Postuleoffre[]> {
    return this.httpClient.get<Postuleoffre[]>(`${this.baseUrl}/findall/${id}`)
    .pipe(
      map((response:any) => response as Postuleoffre[])
    );

  }

  deleteById(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/delete/${id}`)

  }


}

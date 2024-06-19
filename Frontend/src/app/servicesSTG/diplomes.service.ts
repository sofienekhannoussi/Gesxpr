import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Diplome} from '../modelSTG/diplome';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiplomesService {
 private baseUrl=environment.baseurl+"/diplomes"
  constructor( private router : Router,private httpClient: HttpClient) { }



  addDiplome(addDiplome : Diplome){


     const url=this.baseUrl+"/add"
     const headers = new HttpHeaders({
       'Content-Type': 'application/json'
     });

     return this.httpClient.post(url, addDiplome, { headers });

   }


   updateDiplome(diplome : Diplome){
    const url=this.baseUrl+"/update"
      const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(url, diplome, { headers });
  }



  findDiplomeById(id: number): Observable<Diplome> {
    return this.httpClient.get<Diplome>(`${this.baseUrl}/findByIdDiplome/${id}`)

  }
  getList(): Observable<Diplome[]> {
    return this.httpClient.get<Diplome[]>(`${this.baseUrl}/listeall`)
    .pipe(
      map((response:any) => response as Diplome[])
    );

  }

  deleteById(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/delete/${id}`)

  }




}

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Formation} from '../modelSTG/formation';
import { Observable, map } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class FormationService {
  private baseUrl=environment.baseurl+"/formation"

  constructor(private router : Router,private httpClient: HttpClient) { }




  addFormation(addFormation: Formation){


    const url=this.baseUrl+"/add"
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(url, addFormation, { headers });

  }


  updateFormation(formation : Formation){
   const url=this.baseUrl+"/update"
     const headers = new HttpHeaders({
     'Content-Type': 'application/json'
   });

   return this.httpClient.post(url, formation, { headers });
 }



 findFormationById(id: number): Observable<Formation> {
   return this.httpClient.get<Formation>(`${this.baseUrl}/findByIdFormation/${id}`)

 }
 getList(): Observable<Formation[]> {
   return this.httpClient.get<Formation[]>(`${this.baseUrl}/listeall`)
   .pipe(
     map((response:any) => response as Formation[])
   );

 }

 deleteById(id: number): Observable<void> {
   return this.httpClient.delete<void>(`${this.baseUrl}/delete/${id}`)

 }
}

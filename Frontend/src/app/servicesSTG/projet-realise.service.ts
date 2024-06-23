import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { ProjetRealise} from '../modelSTG/projet-realise';
import { Observable, map } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ProjetRealiseService {
  private baseUrl=environment.baseurl+"/projet"

  constructor( private router : Router,private httpClient: HttpClient) { }



  addProjet(addProjet : ProjetRealise){


    const url=this.baseUrl+"/add"
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.httpClient.post(url, addProjet, { headers });

  }


  updateProjet(projet : ProjetRealise){
   const url=this.baseUrl+"/update"
     const headers = new HttpHeaders({
     'Content-Type': 'application/json'
   });

   return this.httpClient.post(url, projet, { headers });
 }



 findProjetById(id: number): Observable<ProjetRealise> {
   return this.httpClient.get<ProjetRealise>(`${this.baseUrl}/findByIdProjet/${id}`)

 }
 getList(): Observable<ProjetRealise[]> {
   return this.httpClient.get<ProjetRealise[]>(`${this.baseUrl}/listeall`)
   .pipe(
     map((response:any) => response as ProjetRealise[])
   );

 }



 getListprojetbyExpert(id: number): Observable<ProjetRealise[]> {
  return this.httpClient.get<ProjetRealise[]>(`${this.baseUrl}/listeallprojetbyexpert/${id}`)
  .pipe(
    map((response:any) => response as ProjetRealise[])
  );

}

 deleteById(id: number): Observable<void> {
   return this.httpClient.delete<void>(`${this.baseUrl}/delete/${id}`)

 }


}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ListProfilUser } from '../modelSTG/list-profil-user';

@Injectable({
  providedIn: 'root'
})
export class GestionProfilUserService {
private baseUrl=environment.baseurl+"/gestionCOmpte"
  constructor(private router : Router,private httpClient: HttpClient) { }

  getList(): Observable<ListProfilUser[]> {
    return this.httpClient.get<ListProfilUser[]>(`${this.baseUrl}/alluser`)
    .pipe(
      map((response:any) => response as ListProfilUser[])
    );

  }
  findUserById(id: number): Observable<ListProfilUser> {
    return this.httpClient.get<ListProfilUser>(`${this.baseUrl}/findbyidUser/${id}`)
 
  }
 
  ModifiedSatusUser(status: boolean,id :number): Observable<ListProfilUser> {
    return this.httpClient.get<ListProfilUser>(`${this.baseUrl}/activateCompte/${status}/${id}`)
 
}

}
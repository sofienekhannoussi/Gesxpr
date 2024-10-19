import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Mission } from '../modelSTG/mission';
import { Observable, map } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class MissionService {
  private baseUrl=environment.baseurl+"/mission"
  constructor(private router : Router,private httpClient: HttpClient) { }

    addMission(addmission : Mission):Observable<Mission>{
      console.log("fffffffffffffff",addmission)
      const url=this.baseUrl+"/add"
      return this.httpClient.post<Mission>(url,addmission)
    }

    updateMission(updatemission : Mission):Observable<Mission>{
      const url=this.baseUrl+"/update"
      return this.httpClient.post<Mission>(url,updatemission)
    }




     finddpetById(id: number): Observable<Mission> {
      return this.httpClient.get<Mission>(`${this.baseUrl}/findByIdMission/${id}`)

    }

    countExpertByMission(id: number): Observable<number> {
      return this.httpClient.get<number>(`${this.baseUrl}/${id}/postuleoffre/count`)

    }


    countMissionByResp(responsableSocieteId: number): Observable<number> {
      return this.httpClient.get<number>(`${this.baseUrl}/missions/count/${responsableSocieteId}`)

    }
    listeallMissionByRESP(id: number): Observable<Mission[]> {
      return this.httpClient.get<Mission[]>(`${this.baseUrl}/listeallbyresp/${id}`)
      .pipe(
        map((response:any) => response as Mission[])
      );

    }
///////////////////////// note ///////////////
    listeallMissionBySTATUT(sta:string, id:number): Observable<Mission[]> {
      return this.httpClient.get<Mission[]>(`${this.baseUrl}/listeallbystatut/${sta}/${id}`)
      .pipe(
        map((response:any) => response as Mission[])
      );

    }
//////////////////////
listeallMissionBySTATUTs(sta:string): Observable<Mission[]> {
  return this.httpClient.get<Mission[]>(`${this.baseUrl}/listeallbystatuts/${sta}`)
  .pipe(
    map((response:any) => response as Mission[])
  );

}

    getList(): Observable<Mission[]> {
      return this.httpClient.get<Mission[]>(`${this.baseUrl}/listeall`)
      .pipe(
        map((response:any) => response as Mission[])
      );

    }

    deleteById(id: number): Observable<void> {
      return this.httpClient.delete<void>(`${this.baseUrl}/delete/${id}`)

    }

    getMissionCountByMonthAndYear(): Observable<any> {
      return this.httpClient.get<any>(`${this.baseUrl}/countByMonthAndYear`);
    }

    countMission(): Observable<number> {
      return this.httpClient.get<number>(`${this.baseUrl}/countAll`)

    }

  }

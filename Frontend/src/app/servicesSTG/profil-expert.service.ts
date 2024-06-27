import { Injectable } from '@angular/core';
import { CrudprofilStagiaireDTO } from '../modelSTG/crudprofil-stagiaire-dto';
import { Observable, map } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Expert } from '../modelSTG/expert';
import { Expertbyid } from '../modelSTG/expertbyid';

@Injectable({
  providedIn: 'root'
})
export class ProfilExpertService {
  private baseUrl=environment.baseurl+"/profilexpert"
  constructor(private router : Router,private httpClient: HttpClient) { }


  updateprof(profilExpert : Expertbyid ):Observable<Expertbyid>{
    const url=this.baseUrl+"/savexpert"
    return this.httpClient.post<Expertbyid>(url,profilExpert )
  }

  finddpetById(id: number): Observable<Expertbyid> {
    return this.httpClient.get<Expertbyid>(`${this.baseUrl}/findExpertbyid/${id}`)

  }


  uploadartImage(id:number,image:File): Observable<HttpEvent<{}>> {
    const formData:FormData=new FormData();
    formData.append(`image`,image)
    let url=this.baseUrl+"/uploadImage/"+id;
    const req=new HttpRequest('POST',url,formData,{reportProgress:true,responseType:'text'})
    return this.httpClient.request(req);
  }

}

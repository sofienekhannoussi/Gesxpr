import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Postuleoffre } from '../modelSTG/postuleoffre';
import { Observable, map } from 'rxjs';
import { ListpostuleBymission } from '../modelSTG/listpostule-bymission';
import { Expert } from '../modelSTG/expert';
import { Mission } from '../modelSTG/mission';

@Injectable({
  providedIn: 'root'
})
export class OffreService {
idoff!:number
  private baseUrl=environment.baseurl+"/offre"
  constructor(private router : Router,private httpClient: HttpClient) { }


  // addoffre(addoffre : Postuleoffre):Observable<Postuleoffre>{
  //   console.log("fffffffffffffff",addoffre)
  //   const url=this.baseUrl+"/add"
  //   return this.httpClient.post<Postuleoffre>(url,addoffre)
  // }

  addoffre(idexpert: number, idmission: number, cvFile: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('idexpert', idexpert.toString());
    formData.append('idmission', idmission.toString());
    formData.append('cvFile', cvFile);
   const url=this.baseUrl+"/add"
    return this.httpClient.post(url, formData);
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


  uploadCV(id:number,cv:File): Observable<HttpEvent<{}>> {
    const formData:FormData=new FormData();
    formData.append(`image`,cv)
    let url=this.baseUrl+"/uploadFile/"+id;
    const req=new HttpRequest('POST',url,formData,{reportProgress:true,responseType:'text'})
    return this.httpClient.request(req);
  }
  getLisPostulBymissiont(idmission: number): Observable<ListpostuleBymission[]> {
    return this.httpClient.get<ListpostuleBymission[]>(`${this.baseUrl}/listePostuleByMission/${idmission}`)
  
  }
  Existingpostule(expert: Expert,mission:Mission):Observable<Postuleoffre>{
    const url=this.baseUrl+"/existingPostule"
    const payload = { expert: expert, mission: mission };
    return this.httpClient.post<Postuleoffre>(url,payload)
  }
  getLisPostulByExpert(idmission:number,idexpert: number): Observable<ListpostuleBymission> {
    return this.httpClient.get<ListpostuleBymission>(`${this.baseUrl}/listePostuleByExpert/${idmission}/${idexpert}`)
  
  }

}

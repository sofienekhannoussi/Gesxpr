import { Component, OnInit } from '@angular/core';
import { Contrat } from 'src/app/modelSTG/contrat';
import { ContratService } from 'src/app/servicesSTG/contrat.service';

@Component({
  selector: 'app-gestion-contrat',
  templateUrl: './gestion-contrat.component.html',
  styleUrls: ['./gestion-contrat.component.scss']
})
export class GestionContratComponent implements OnInit{
  listContrat:Contrat[]=[]
  viewmodelContrat: Contrat= new Contrat()
constructor(private contratservice:ContratService ){

}
  ngOnInit(): void {
    const id = Number(localStorage.getItem('userId'));
    this.getlistContra(id)

  }

getlistContra(id:number) {

    this.contratservice.findContratByIdResp(id).subscribe(res => {
      this.listContrat = res
      console.log('hhhhhhhhhhhhh',res)

    } , error => {
        console.error(error)
    } , ()=> {

    })
}


getContratId(id:number)
      {

        console.log("iiiiiiiiiiiiii",id);
        if(id!=undefined && id !=null)
        {
          this.contratservice.findContratById(id).subscribe(
            res=>{
              console.log(res);
              this.viewmodelContrat=res
          },error=>{
            console.error(error)
          },()=>{

          });
        }
        console.log("iiiiiiiiiiiiii",id);
      }

}

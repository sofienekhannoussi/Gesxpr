import { Component, OnInit } from '@angular/core';
import { Mission } from 'src/app/modelSTG/mission';
import { MissionService } from 'src/app/servicesSTG/mission.service';

@Component({
  selector: 'app-gestion-messions',
  templateUrl: './gestion-messions.component.html',
  styleUrls: ['./gestion-messions.component.scss']
})
export class GestionMessionsComponent implements OnInit{

  listMission!:Mission[]
  viewmodelMission: Mission= new Mission()
constructor(private mission:MissionService ){

}
  ngOnInit(): void {
    this.getlistUser() 

  }

getlistUser() {
    this.mission.getList().subscribe(res => {
      this.listMission = res
      console.log(res)
     
    } , error => {
        console.error(error)
    } , ()=> {

    })
}


getMissionId(id:number)
      {
       
        console.log("iiiiiiiiiiiiii",id);
        if(id!=undefined && id !=null)
        {
          this.mission.finddpetById(id).subscribe(
            res=>{
              console.log(res);
              this.viewmodelMission=res
          },error=>{
            console.error(error)
          },()=>{
          
          });
        }
        console.log("iiiiiiiiiiiiii",id);
      }


  

}

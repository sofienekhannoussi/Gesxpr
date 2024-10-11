import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ListProfilUser } from 'src/app/modelSTG/list-profil-user';
import { GestionProfilUserService } from 'src/app/servicesSTG/gestion-profil-user.service';

@Component({
  selector: 'app-gestion-des-experts',
  templateUrl: './gestion-des-experts.component.html',
  styleUrls: ['./gestion-des-experts.component.scss']
})
export class GestionDesExpertsComponent implements OnInit{
  listUserr!:ListProfilUser[]
  selectedUser!: ListProfilUser | null;
  viewmodeluser: ListProfilUser = new ListProfilUser();
  status_!:boolean
  FormGroupart: any;
  IdUser!: number
constructor(private  gestionProfilService:GestionProfilUserService ){

}
  ngOnInit(): void {
    this.getlistUser() 
    this.FormGroupart = new FormGroup({
  
      'active' : new FormControl('', Validators.required),
     
    });
  }

getlistUser() {
    this.gestionProfilService.getList().subscribe(res => {
      this.listUserr = res
      console.log(res)
     
    } , error => {
        console.error(error)
    } , ()=> {

    })
}


getUserId(id:number)
      {
        this.IdUser = id
        console.log("iiiiiiiiiiiiii",id);
        if(id!=undefined && id !=null)
        {
          this.gestionProfilService.findUserById(id).subscribe(
            res=>{
              console.log(res);
              this.viewmodeluser=res
          },error=>{
            console.error(error)
          },()=>{
          
          });
        }
        console.log("iiiiiiiiiiiiii",id);
      }

 updateSatusUser() {
      
        console.log('status',this.FormGroupart.value.active)
        console.log('idUser',this.IdUser)
          this.gestionProfilService.ModifiedSatusUser(this.FormGroupart.value.active, this.IdUser ).subscribe(
            res=>{
              alert('status modifie')
              console.log(res);
              this.viewmodeluser=res
          },error=>{
            console.error(error)
          },()=>{
          
          });
        
    
      }
}
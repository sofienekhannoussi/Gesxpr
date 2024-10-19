import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CrudprofilStagiaireDTO } from 'src/app/modelSTG/crudprofil-stagiaire-dto';
import { Expert } from 'src/app/modelSTG/expert';
import { Expertbyid } from 'src/app/modelSTG/expertbyid';
import { ProfilExpertService } from 'src/app/servicesSTG/profil-expert.service';
import { routes } from 'src/app/shared/service/routes/routes';

@Component({
  selector: 'app-setting-edit-profile',
  templateUrl: './setting-edit-profile.component.html',
  styleUrls: ['./setting-edit-profile.component.scss']
})
export class SettingEditProfileComponent implements OnInit {
  editproRequest: Expertbyid = new Expertbyid();
  errorMsg=""
  public routes = routes;
  imgUrl:string | ArrayBuffer = 'assets/img/ssssss.jpeg'
  submitted: boolean= false;
  //| ArrayBuffer = 'assets/img/avatar.png'
  file!: File;


  selected ='option1';
  constructor(private profilexpert:ProfilExpertService,private router: Router) { }

  ngOnInit(): void {
    this. getbyisExpert()
//this.showuser()
  }

  /* showuser(){
  const id=Number(localStorage.getItem("userId"))
    this.crusprofil.finddpetById(id)
    .subscribe(result=>{

   this.editproRequest=result

      console.log("fffffffff",result)

    },
    (err:HttpErrorResponse)=>this.errorMsg='this email is existe')
  } */

    getbyisExpert() {
      const id=Number(localStorage.getItem("userId"))

      this.profilexpert.finddpetById(id).subscribe({
        next: (data) => {
          this.editproRequest=data
         // this.updateEvents()
         console.log(data);

        },
        error: console.log,
      });
    }

  updateprofilStagiaire(){
    console.log("SOU",this.editproRequest)
    this.editproRequest.id=Number(localStorage.getItem("userId"))

    //

    this.profilexpert.updateprof(this.editproRequest)
    .subscribe(result=>{
console.log("sofiene",this.file);
      this.profilexpert.uploadartImage(this.editproRequest.id, this.file).subscribe(
        val =>  {} , error => { alert('oups')} , () => {
          this.submitted = true ;

        });

      //this.router.navigate(["home/"])

      console.log("TTTTTTTT")
      console.log(result)

    },
    (err:HttpErrorResponse)=>this.errorMsg='this email is existe')
  }

  onFileInput(files: FileList | null): void {
    // alert("1" + JSON.stringify(files))
    if (files) {
      //  alert("2" + JSON.stringify(files))
      this.file = files.item(0) as File;
      if (this.file) {
        const fileReader = new FileReader();
        fileReader.readAsDataURL(this.file);
        fileReader.onload = (event) => {
          if (fileReader.result) {
            this.imgUrl = fileReader.result;
          }
        };
      }
    }
  }
  changeSource(event: any) {
    event.target.src = "assets/img/ssssss.jpeg";
  }




}

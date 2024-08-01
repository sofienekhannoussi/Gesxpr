import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Respsociete } from 'src/app/modelSTG/respsociete';
import { ProfilExpertService } from 'src/app/servicesSTG/profil-expert.service';
import { routes } from 'src/app/shared/service/routes/routes';

@Component({
  selector: 'app-instructor-edit-profile',
  templateUrl: './instructor-edit-profile.component.html',
  styleUrls: ['./instructor-edit-profile.component.scss']
})
export class InstructorEditProfileComponent implements OnInit {
  selected='option1';
  editproRequeste: Respsociete = new Respsociete();
  errorMsg=""
  public routes = routes;
  imgUrl:string | ArrayBuffer = 'assets/img/ssssss.jpeg'
  submitted: boolean= false;
  //| ArrayBuffer = 'assets/img/avatar.png'
  file!: File;

  constructor(private profilexpert:ProfilExpertService,private router: Router) { }

  ngOnInit(): void {
    this.getbyisRespsociete()
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

    getbyisRespsociete() {
      const id=Number(localStorage.getItem("userId"))
      console.log("nour",id)

      this.profilexpert.findrespById(id).subscribe({
        next: (data) => {
          this.editproRequeste=data
         // this.updateEvents()
         console.log(data);

        },
        error: console.log,
      });
    }

  updateprofilStagiaire(){
    console.log("nour",this.editproRequeste)
    this.editproRequeste.id=Number(localStorage.getItem("userId"))
    console.log("ali",this.editproRequeste)

    //

    this.profilexpert.updateresp(this.editproRequeste)
    .subscribe(result=>{
console.log("sofiene",this.file);
      this.profilexpert.uploadartImage(this.editproRequeste.id, this.file).subscribe(
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

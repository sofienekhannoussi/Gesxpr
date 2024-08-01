import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Expertbyid } from 'src/app/modelSTG/expertbyid';
import { Mission } from 'src/app/modelSTG/mission';
import { Offre } from 'src/app/modelSTG/offre';
import { Postuleoffre } from 'src/app/modelSTG/postuleoffre';
import { MissionService } from 'src/app/servicesSTG/mission.service';
import { OffreService } from 'src/app/servicesSTG/postuleoffre.service';
import { ProfilExpertService } from 'src/app/servicesSTG/profil-expert.service';
import { CommonService } from 'src/app/shared/service/common/common.service';
import { DataService } from 'src/app/shared/service/data/data.service';
import { routes } from 'src/app/shared/service/routes/routes';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  public routes = routes;
  public missionn: Mission [] = [];
  missionss: Mission = new Mission();
  editproRequest: Expertbyid = new Expertbyid();

  imgUrl:string | ArrayBuffer = 'assets/img/ssssss.jpeg'

  file!: File;

    errorMsg:string=""
  msgSuccess:string=""

  public postuleoffre: Postuleoffre [] = [];
  postule: Postuleoffre = new Postuleoffre();
  public isstudentHeader!: boolean;

  constructor(private missionservice:MissionService,private offreservice : OffreService, private data: DataService ,private common: CommonService,private router:Router) {
    this.common.isstudentHeader.subscribe((res: boolean) => {
      this.isstudentHeader = res;
    });
  }


  ngOnInit(): void {
    this.getlistMission()
  }
/*   offresList()
  {
    this.offrsev.getList().subscribe(res => {
      this.offreList = res
    } , error => {
        console.error(error)
    } , ()=> {

    })
  }
 recupeidoffr(idoffcom:number)

 {
alert("fffffffff")
  this.offrsev.idoff=idoffcom;
  console.log(idoffcom)
  this.router.navigate(['expert/demande']);

 } */



  Postuleoffre(idmissions: number){
    const IDD=localStorage.getItem("userId");
    this.postule.idexpert=Number(IDD)
    this.postule.idmission=idmissions


    console.log("ssssss", this.postule)


      this.offreservice.addoffre(this.postule)

      .subscribe(result=>{

this.uploadcv(Number(IDD))

console.log("ssssssof", this.file)
console.log("sssqqaqaqas", Number(IDD))



        this.msgSuccess="Votre demande de postule a été prise en charge"


      },
      (err:HttpErrorResponse)=>this.errorMsg='Votre demande de postule n a pas été prise en charge')
    //}
    }

    uploadcv(id:number) {
      this.offreservice.uploadCV(id,this.file).subscribe({
        next: (data) => {
   //      // this.updateEvents()
         console.log(data,"ssssssssssss");


        },
      error: console.log,
      });
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



   getlistMission() {
     this.missionservice.listeallMissionBySTATUT("En cours").subscribe({
       next: (data) => {
        this.missionn=data
  //      // this.updateEvents()
        console.log(data,"ssssssssssss");


       },
     error: console.log,
     });
   }
}

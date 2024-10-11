import { HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Expert } from 'src/app/modelSTG/expert';
import { Expertbyid } from 'src/app/modelSTG/expertbyid';
import { ListpostuleBymission } from 'src/app/modelSTG/listpostule-bymission';
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
  public missionn: Mission[] = [];

  missionsbyId: Mission = new Mission();
  missionss: Mission = new Mission();
  editproRequest: Expertbyid = new Expertbyid();
  loading: boolean = false;
  successMessage: string = '';
  errorMessage: string = '';
  postuleoffres:Postuleoffre = new Postuleoffre
  postuleoffresByExpert:ListpostuleBymission = new ListpostuleBymission()
  selectedFile: File | null = null;
   expert: Expert = new Expert
  idPostule: number | null = null;
  myidmission: number = 0
  imgUrl: string | ArrayBuffer = 'assets/img/student.png'

  file!: File;
  idexpert!: number
  idmission!: number


  errorMsg: string = ""
  msgSuccess: string = ""

  public postuleoffre: Postuleoffre[] = [];
  postule: Postuleoffre = new Postuleoffre();
  public isstudentHeader!: boolean;

  constructor(private missionservice: MissionService, private offreservice: OffreService,
    private data: DataService, private common: CommonService,
    private router: Router, private serviceExpert:ProfilExpertService) {
    this.common.isstudentHeader.subscribe((res: boolean) => {
      this.isstudentHeader = res;
    });
  }


  ngOnInit(): void {
    this.getlistMission()
    this.getlistMissions()
  }
 
FetchExistingPostule(){
  this.offreservice.Existingpostule(this.expert,this.missionsbyId).subscribe({
    next: (data) => {
        this.postuleoffres = data
        console.log(data);
      },
      error: console.log,
    });
  
}
getpostuleByExpertAndmission(idMission:number) {
  const IDD = Number(localStorage.getItem("userId"));
  console.log('idUser', IDD)
  this.offreservice.getLisPostulByExpert(idMission,IDD).subscribe({
    next: (data) => {
      this.postuleoffresByExpert = data

      console.log('Ahmed',data);
    },
    error: console.log,
  });
}


  getExpertById(id: number) {
    this.serviceExpert.finddpetById(id).subscribe({
      next: (data) => {
        this.expert = data
        console.log(data);
      },
      error: console.log,
    });
  }

  Postuleoffre(idmissions: number) {
    this.getpostuleByExpertAndmission(idmissions)
    this.myidmission = idmissions
  this.getMissionById(idmissions)
  const IDD = Number(localStorage.getItem("userId"));
  this.getExpertById(IDD)
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
    const IDD = localStorage.getItem("userId");
    this.missionservice.listeallMissionBySTATUT("En cours", Number(IDD)).subscribe({
      next: (data) => {
        this.missionn = data
        //      // this.updateEvents()
        console.log(data);


      },
      error: console.log,
    });
  }

  getMissionById(id: number) {

    this.missionservice.finddpetById(id).subscribe({
      next: (data) => {
        this.missionsbyId = data
        //      // this.updateEvents()
        console.log(data);


      },
      error: console.log,
    });
  }

  getlistMissions() {

    this.missionservice.listeallMissionBySTATUTs("En cours").subscribe({
      next: (data) => {
        this.missionn = data
        //      // this.updateEvents()
        console.log(data);


      },
      error: console.log,
    });
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0]; // Récupérer le fichier sélectionné
  }




  addpostule() {
  
    if (!this.file) {
      this.errorMessage = 'Veuillez sélectionner un fichier CV avant de postuler.'; // Message d'erreur
      return; // Arrêter l'exécution si aucun fichier n'est sélectionné
    }
    if (this.postuleoffresByExpert.id) {
      console.log('jjjjjj', this.postuleoffresByExpert)
      this.errorMessage = 'vous avez déja postuler.'; // Message d'erreur
      return; 
    }

    const IDD = localStorage.getItem("userId");
    this.idexpert = Number(IDD)
    this.idmission = this.myidmission
    console.log("idExp", this.idexpert)
    console.log("idmiss", this.idmission)
    console.log("file", this.file)
    this.loading = true;
    this.successMessage = ''; // Réinitialiser les messages
    this.errorMessage = '';
    this.offreservice.addoffre(this.idexpert, this.idmission, this.file).subscribe({
      next: (res) => {
        console.log(res);
        // Désactiver le spinner
        const idPostule = res.id;
        console.log('hi rima', idPostule)

        this.loading = false;
        this.successMessage = 'Postule effectuée avec succès !';

      },
      error: (err) => {
        console.error(err);
        // Désactiver le spinner en cas d'erreur
        this.loading = false;
        this.errorMessage = 'Une erreur s\'est produite lors de la postule. Veuillez réessayer.';
      }
    });
  }


}

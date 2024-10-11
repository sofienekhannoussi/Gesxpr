import { AfterViewInit, Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Validators } from 'ngx-editor';
import { ProjetRealise } from 'src/app/modelSTG/projet-realise';
import { ProjetRealiseService } from 'src/app/servicesSTG/projet-realise.service';
import { ExperienceDialogComponent } from '../../student-profile/experience-dialog/experience-dialog.component';

@Component({
  selector: 'app-experience-dialog-resp',
  templateUrl: './experience-dialog-resp.component.html',
  styleUrls: ['./experience-dialog-resp.component.scss']
})
export class ExperienceDialogRespComponent  implements OnInit ,AfterViewInit , OnDestroy {

  addNewExperience: boolean = false;
  experienceForm: FormGroup;
  experienceSelected: ProjetRealise = {
    id: -1,
    title: '',
    description: '',
    nomSociete: '',
    dateDebut: '',
    dateFin: '',
    idexpert: 0
  };

  constructor(
    private projetservices: ProjetRealiseService,

    public dialogRef: MatDialogRef<ExperienceDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ProjetRealise[],
    private fb: FormBuilder
  ) {
    this.experienceForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      nomSociete: ['', Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
    });
  }


  ngAfterViewInit(): void {

  }
  ngOnDestroy(): void {
  }

  ngOnInit(): void {

  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  selectExperience(experience : ProjetRealise) {
    this.experienceSelected = experience
    this.experienceForm.patchValue({
      title: experience.title,
      description: experience.description,
      nomSociete: experience.nomSociete,
      dateDebut: experience.dateDebut,
      dateFin: experience.dateFin,
    })
    this.addNewExperience= true
  }

  addExperience(){
    this.initialSelectedExperience()
    this.experienceForm.patchValue({
      title: '',
      description:'',
      nomSociete: '',
      dateDebut: '',
      dateFin: '',
    })
    this.addNewExperience = true
  }
  closeFormExperience(){
    this.initialSelectedExperience()
    this.addNewExperience= false
  }
  initialSelectedExperience(){
    this.experienceSelected = {
      id: -1,
      title: '',
      description: '',
      nomSociete: '',
      dateDebut: '',
      dateFin: '',
      idexpert: 0
    };
  }


  getlistProjet(id : number) {
    this.projetservices.getListprojetbyExpert(id).subscribe({
      next: (list) => {
        this.data=list
      },
      error: (error) => console.log(error),
    });
  }


  deleteExperience(id: number): void {
    const userId = Number(localStorage.getItem('userId'));
    if (confirm('Are you sure you want to delete this experience item?')) {
      this.projetservices.deleteById(id).subscribe( {
        next: () => {
          this.closeFormExperience()
          this.getlistProjet(userId)

        },  error: (er) => {
          console.log(er);
        },
      })
    }
  }

  onSubmit(){
    if(this.experienceForm.invalid){
      return;
    }
    const {
      title , description , nomSociete , dateDebut , dateFin
    } = this.experienceForm.value
    const userId = Number(localStorage.getItem('userId'));
   if(this.experienceSelected.id > 0 ){
    let input : ProjetRealise = {
      id: this.experienceSelected.id,
      title: title,
      description: description,
      nomSociete: nomSociete,
      dateDebut: dateDebut,
      dateFin: dateFin,
      idexpert: userId
    }
    this.projetservices.updateProjet(input).subscribe( {
      next: () => {
        this.closeFormExperience()
        this.getlistProjet(userId)

      },  error: (er) => {
        console.log(er);
      },
    })
   }else {
    let input : ProjetRealise = {
      id: -1,
      title: title,
      description: description,
      nomSociete: nomSociete,
      dateDebut: dateDebut,
      dateFin: dateFin,
      idexpert: userId
    }
    this.projetservices.addProjet(input).subscribe( {
      next: () => {
        this.closeFormExperience()
        this.getlistProjet(userId)

      },  error: (er) => {
        console.log(er);
      },
    })
   }
  }

}


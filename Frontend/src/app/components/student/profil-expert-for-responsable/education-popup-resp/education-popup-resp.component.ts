import { AfterViewInit, Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Diplome } from 'src/app/modelSTG/diplome';
import { DiplomesService } from 'src/app/servicesSTG/diplomes.service';
import { EducationsPopupComponent } from '../../student-profile/educations-popup/educations-popup.component';

@Component({
  selector: 'app-education-popup-resp',
  templateUrl: './education-popup-resp.component.html',
  styleUrls: ['./education-popup-resp.component.scss']
})
export class EducationPopupRespComponent implements OnInit , AfterViewInit , OnDestroy {
  addNewEducation : boolean = false
  educationForm: FormGroup;
  educationSelected: Diplome = {
    id: -1,
    title: '',
    description: '',
    universityName: '',
    dateDebut: '',
    dateFin: '',
    idexpert: 0
  };
  constructor(
    private diplomeservices: DiplomesService,
    public dialogRef: MatDialogRef<EducationsPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Diplome[],
    private fb: FormBuilder
  ){
    this.educationForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      universityName: ['', Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],


    });
  }
  ngOnInit(): void {

  }
  ngAfterViewInit(): void {

  }
  ngOnDestroy(): void {

  }
  /**
   * public methods
   */

  closeDialog(): void {
    this.dialogRef.close();
  }
  selectEducation(education : Diplome) {
    this.educationSelected = education
    this.educationForm.patchValue({
      title: education.title,
      description: education.description,
      universityName: education.universityName,
      dateDebut: education.dateDebut,
      dateFin: education.dateFin,
    })
    this.addNewEducation = true
  }
  addEducation(){
    this.initialSelectedEducation()
    this.educationForm.patchValue({
      title: '',
      description:'',
      universityName: '',
      dateDebut: '',
      dateFin: '',
    })
    this.addNewEducation = true
  }
  closeFormEducation(){
    this.initialSelectedEducation()
    this.addNewEducation = false
  }
  initialSelectedEducation(){
    this.educationSelected = {
      id: -1,
      title: '',
      description: '',
      universityName: '',
      dateDebut: '',
      dateFin: '',
      idexpert: 0
    };
  }

  getlistDiplome(id : number) {
    this.diplomeservices.getListdiplomebyExpert(id).subscribe({
      next: (list) => {
        this.data=list
      },
      error: (error) => console.log(error),
    });
  }
  deleteEducation(id: number): void {
    const userId = Number(localStorage.getItem('userId'));
    if (confirm('Are you sure you want to delete this education item?')) {
      this.diplomeservices.deleteById(id).subscribe( {
        next: () => {
          this.closeFormEducation()
          this.getlistDiplome(userId)

        },  error: (er) => {
          console.log(er);
        },
      })
    }
  }
  onSubmit(){
    if(this.educationForm.invalid){
      return;
    }
    const {
      title , description , universityName , dateDebut , dateFin
    } = this.educationForm.value
    const userId = Number(localStorage.getItem('userId'));
   if(this.educationSelected.id > 0 ){
    let input : Diplome = {
      id: this.educationSelected.id,
      title: title,
      description: description,
      universityName: universityName,
      dateDebut: dateDebut,
      dateFin: dateFin,
      idexpert: userId
    }
    this.diplomeservices.updateDiplome(input).subscribe( {
      next: () => {
        this.closeFormEducation()
        this.getlistDiplome(userId)

      },  error: (er) => {
        console.log(er);
      },
    })
   }else {
    let input : Diplome = {
      id: -1,
      title: title,
      description: description,
      universityName: universityName,
      dateDebut: dateDebut,
      dateFin: dateFin,
      idexpert: userId
    }
    this.diplomeservices.addDiplome(input).subscribe( {
      next: () => {
        this.closeFormEducation()
        this.getlistDiplome(userId)

      },  error: (er) => {
        console.log(er);
      },
    })
   }
  }

}

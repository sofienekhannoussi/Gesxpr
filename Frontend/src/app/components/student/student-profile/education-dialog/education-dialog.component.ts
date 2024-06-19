import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Title } from 'chart.js';
import { Validators } from 'ngx-editor';
import { Diplome } from 'src/app/modelSTG/diplome';
import { DiplomesService } from 'src/app/servicesSTG/diplomes.service';

@Component({
  selector: 'app-education-dialog',
  templateUrl: './education-dialog.component.html',
  styleUrls: ['./education-dialog.component.scss'],
})
export class EducationDialogComponent implements OnInit, OnDestroy {
  educationForms: FormGroup[] = [];
  newEducationForm: FormGroup;
  editingtitle: boolean[] = [];
  editingdescription: boolean[] = [];
  editinguniversityName: boolean[] = [];
  editingdateDebut: boolean[] = [];
  editingdateFin: boolean[] = [];

  addNewEducation: boolean = false;
  constructor(
    private diplomeservices: DiplomesService,

    public dialogRef: MatDialogRef<EducationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Diplome[],
    private fb: FormBuilder
  ) {
    this.newEducationForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      universityName: ['', Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],


    });
  }
  ngOnDestroy(): void {
    this.addNewEducation = false;
  }

  ngOnInit(): void {
    this.data.forEach((item, index) => {
      this.educationForms[index] = this.fb.group({
        title: new FormControl(item.title),
        description: new FormControl(item.description),
        universityName: new FormControl(item.universityName),
        dateDebut: new FormControl(item.dateDebut),
        dateFin: new FormControl(item.dateFin),


      });
      this.editingtitle[index] = false;
      this.editingdescription[index] = false;
      this.editinguniversityName[index] = false;
    });
  }

  closeDialog(): void {
    this.dialogRef.close();
  }
  addNew() {
    this.addNewEducation = true;
  }
  toggleEdit(index: number, field: string): void {
    switch (field) {
      case 'title':
        if (this.editingtitle[index]) {
          this.data[index].title = this.educationForms[index].value.title;
        }
        this.editingtitle[index] = !this.editingtitle[index];
        break;
      case 'description':
        if (this.editingdescription[index]) {
          this.data[index].description =
            this.educationForms[index].value.description;
        }
        this.editingdescription[index] = !this.editingdescription[index];
        break;
      case 'universityName':
        if (this.editinguniversityName[index]) {
          this.data[index].universityName = this.educationForms[index].value.universityName;
        }
        this.editinguniversityName[index] = !this.editinguniversityName[index];
        break;

        case 'dateDebut':
        if (this.editingdateDebut[index]) {
          this.data[index].dateDebut = this.educationForms[index].value.dateDebut
        }
        this.editingdateDebut[index] = !this.editingdateDebut[index];
        break;

        case 'dateFin':
        if (this.editingdateFin[index]) {
          this.data[index].dateFin = this.educationForms[index].value.dateFin;
        }
        this.editingdateFin[index] = !this.editingdateFin[index];
        break;
    }
  }

  deleteEducation(index: number): void {
    if (confirm('Are you sure you want to delete this education item?')) {
      this.data.splice(index, 1);
      this.educationForms.splice(index, 1);
      this.editingtitle.splice(index, 1);
      this.editingdescription.splice(index, 1);
      this.editinguniversityName.splice(index, 1);
      this.editingdateDebut.splice(index, 1);
      this.editingdateFin.splice(index, 1);


    }
  }

  /*addEducation(): void {
    this.newEducationForm.markAllAsTouched();
    const newEdu = this.newEducationForm.value;

    if (this.newEducationForm.invalid) {
      console.log(this.newEducationForm.invalid, newEdu);
      return;
    }


    this.data.push({
      title: newEdu.title,
      description: newEdu.description,
      universityName: newEdu.universityName,
      dateDebut: newEdu.dateDebut,
      dateFin: newEdu.dateFin,
      id: 0,
      idexpert: 0
    });

    this.educationForms.push(
      this.fb.group({
        title: new FormControl(newEdu.title),
        description: new FormControl(newEdu.description),
        universityName: new FormControl(newEdu.universityName),
        dateDebut: new FormControl(newEdu.dateDebut),
        dateFin: new FormControl(newEdu.dateFin),

      })
    );

    this.editingtitle.push(false);
    this.editingdescription.push(false);
    this.editinguniversityName.push(false);
    this.editingdateDebut.push(false);
    this.editingdateFin.push(false);

    this.newEducationForm.reset();
    this.addNewEducation = false;
  }
*/

addDiplome(): void {
  if (this.newEducationForm.valid) {


      const id = Number(localStorage.getItem('userId'));
      let input: Diplome = {
        id: 0,
        title :  this.newEducationForm.value.title,
        description :this.newEducationForm.value.description,
        universityName : this.newEducationForm.value.universityName,
        dateDebut : this.newEducationForm.value.dateDebut,
        dateFin : this.newEducationForm.value.dateFin,
        idexpert: id,
      };

console.log(input);


      this.diplomeservices.addDiplome(input).subscribe({

        next: (res) => {
//          this.data.push(input);
          console.log(res);

          this.newEducationForm.reset();
        },
        error: (er) => {
          console.log(er);
        },
      });

      //inside subscribe create object

  }
}



updateDiplome(): void {
  if (this.newEducationForm.valid) {


      const id = Number(localStorage.getItem('userId'));
      let input: Diplome = {
        id: 0,
        title :  this.newEducationForm.value.title,
        description :this.newEducationForm.value.description,
        universityName : this.newEducationForm.value.universityName,
        dateDebut : this.newEducationForm.value.dateDebut,
        dateFin : this.newEducationForm.value.dateFin,
        idexpert: id,
      };

console.log(input);


      this.diplomeservices.updateDiplome(input).subscribe({

        next: (res) => {
//          this.data.push(input);
          console.log(res);

          this.newEducationForm.reset();
        },
        error: (er) => {
          console.log(er);
        },
      });

      //inside subscribe create object

  }
}

deleteDiplome(index: number): void {
  let input = this.data[index];

  this.diplomeservices.deleteById(input.id).subscribe({
    next: () => {
      this.data.splice(index, 1);
      this.educationForms.splice(index, 1);
    },
    error: (er) => {
      console.log(er);
    },
  });
}



}

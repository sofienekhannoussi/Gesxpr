import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Validators } from 'ngx-editor';
import { ProjetRealise } from 'src/app/modelSTG/projet-realise';
import { ProjetRealiseService } from 'src/app/servicesSTG/projet-realise.service';

@Component({
  selector: 'app-experience-dialog',
  templateUrl: './experience-dialog.component.html',
  styleUrls: ['./experience-dialog.component.scss']
})
export class ExperienceDialogComponent implements OnInit , OnDestroy {
  experienceForms: FormGroup[] = [];
  newExperienceForm: FormGroup;
  editingtitle: boolean[] = [];
  editingdescription: boolean[] = [];
  editingnomSociete: boolean[] = [];
  editingdateDebut: boolean[] = [];
  editingdateFin: boolean[] = [];
  addNewExperience: boolean = false;
  constructor(
    private projetservices: ProjetRealiseService,

    public dialogRef: MatDialogRef<ExperienceDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ProjetRealise[],
    private fb: FormBuilder
  ) {
    this.newExperienceForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      nomSociete: ['', Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
    });
  }
  ngOnDestroy(): void {
    this.addNewExperience = false;
  }

  ngOnInit(): void {
    this.data.forEach((item, index) => {
      this.experienceForms[index] = this.fb.group({
        title: new FormControl(item.title),
        description: new FormControl(item.description),
        nomSociete: new FormControl(item.nomSociete),
        dateDebut: new FormControl(item.dateDebut),
        dateFin: new FormControl(item.dateFin),
      });
      this.editingtitle[index] = false;
      this.editingdescription[index] = false;
      this.editingnomSociete[index] = false;
    });
  }

  closeDialog(): void {
    this.dialogRef.close();
  }
  addNew() {
    this.addNewExperience = true;
  }
  toggleEdit(index: number, field: string): void {
    switch (field) {
      case 'title':
        if (this.editingtitle[index]) {
          this.data[index].title = this.experienceForms[index].value.title;
        }
        this.editingtitle[index] = !this.editingtitle[index];
        break;
      case 'description':
        if (this.editingdescription[index]) {
          this.data[index].description =
            this.experienceForms[index].value.description;
        }
        this.editingdescription[index] = !this.editingdescription[index];
        break;
      case 'nomSociete':
        if (this.editingnomSociete[index]) {
          this.data[index].nomSociete = this.experienceForms[index].value.universityName;
        }
        this.editingnomSociete[index] = !this.editingnomSociete[index];
        break;

        case 'dateDebut':
        if (this.editingdateDebut[index]) {
          this.data[index].dateDebut = this.experienceForms[index].value.dateDebut
        }
        this.editingdateDebut[index] = !this.editingdateDebut[index];
        break;

        case 'dateFin':
        if (this.editingdateFin[index]) {
          this.data[index].dateFin = this.experienceForms[index].value.dateFin;
        }
        this.editingdateFin[index] = !this.editingdateFin[index];
        break;
    }
  }

  deleteExperience(index: number): void {
    if (confirm('Are you sure you want to delete this experience item?')) {
      this.data.splice(index, 1);
      this.experienceForms.splice(index, 1);
      this.editingtitle.splice(index, 1);
      this.editingdescription.splice(index, 1);
      this.editingnomSociete.splice(index, 1);
      this.editingdateDebut.splice(index, 1);
      this.editingdateFin.splice(index, 1);
    }
  }
/*
  addExperience(): void {
    this.newExperienceForm.markAllAsTouched();
    const newEdu = this.newExperienceForm.value;

    if (this.newExperienceForm.invalid) {
      console.log(this.newExperienceForm.invalid, newEdu);
      return;
    }


    this.data.push({
      heading: newEdu.heading,
      paragraph: newEdu.paragraph,
      content: newEdu.content,
      letter: newEdu.heading.charAt(0).toUpperCase(),
    });

    this.experienceForms.push(
      this.fb.group({
        heading: new FormControl(newEdu.heading),
        paragraph: new FormControl(newEdu.paragraph),
        content: new FormControl(newEdu.content),
      })
    );

    this.editingHeading.push(false);
    this.editingParagraph.push(false);
    this.editingContent.push(false);

    this.newExperienceForm.reset();
    this.addNewExperience = false;
  }

*/
deleteprojet(index: number): void {
  let input = this.data[index];

  this.projetservices.deleteById(input.id).subscribe({
    next: () => {
      this.data.splice(index, 1);
      this.experienceForms.splice(index, 1);
      this.editingtitle.splice(index, 1);
      this.editingdescription.splice(index, 1);
      this.editingnomSociete.splice(index, 1);
      this.editingdateDebut.splice(index, 1);
      this.editingdateFin.splice(index, 1);
    },
    error: (er) => {
      console.log(er);
    },
  });
}


addProjet(): void {
  if (this.newExperienceForm.valid) {


      const id = Number(localStorage.getItem('userId'));
      let input: ProjetRealise = {
        id: 0,
        title :  this.newExperienceForm.value.title,
        description :this.newExperienceForm.value.description,
        nomSociete : this.newExperienceForm.value.nomSociete,
        dateDebut : this.newExperienceForm.value.dateDebut,
        dateFin : this.newExperienceForm.value.dateFin,
        idexpert: id,
      };

console.log(input);


      this.projetservices.addProjet(input).subscribe({

        next: (res) => {
//          this.data.push(input);
          console.log(res);

          this.newExperienceForm.reset();
        },
        error: (er) => {
          console.log(er);
        },
      });

      //inside subscribe create object

  }
}
}

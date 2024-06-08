import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormControl, Validators } from '@angular/forms';
import { CompetenceService } from 'src/app/servicesSTG/competence.service';
import { Competence } from 'src/app/modelSTG/competence';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-skills-dialog',
  templateUrl: './skills-dialog.component.html',
  styleUrls: ['./skills-dialog.component.scss'],
})
export class SkillsDialogComponent implements OnInit {
  isEditing: boolean[] = [];
  skillControls: FormControl[] = [];
  newSkillControl: FormControl;

  errorMsg!: String;

  constructor(
    private competenceservice: CompetenceService,
    public dialogRef: MatDialogRef<SkillsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Competence[]
  ) {
    this.isEditing = new Array(data.length).fill(false);
    this.skillControls = data.map(
      (skill) => new FormControl(skill.competenceName, Validators.required)
    );
    this.newSkillControl = new FormControl('', Validators.required);
  }
  ngOnInit(): void {
    console.log('competenc', this.data);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  editSkill(index: number): void {
    this.isEditing[index] = true;
  }

  saveEdit(index: number): void {
    if (this.skillControls[index].valid) {
      const skill = this.skillControls[index].value;
      if (skill !== null) {
        // this.data[index] = skill.com;
        let input = this.data[index];
        const id = Number(localStorage.getItem('userId'));
        input.idexpert = id;
        input.competenceName = skill;
        console.log(input);

        this.competenceservice.updateCompetence(input).subscribe({
          next: () => {
            this.data[index] = input;
          },
          error: (er) => {
            console.log(er);
          },
        });
      }
      this.isEditing[index] = false;
    }
  }

  deleteSkill(index: number): void {
    let input = this.data[index];

    this.competenceservice.deleteById(input.id).subscribe({
      next: () => {
        this.data.splice(index, 1);
        this.skillControls.splice(index, 1);
        this.isEditing.splice(index, 1);
      },
      error: (er) => {
        console.log(er);
      },
    });
  }

  addSkill(): void {
    if (this.newSkillControl.valid) {
      const newSkill = this.newSkillControl.value;
      if (newSkill !== null && newSkill.trim() !== '') {
        const id = Number(localStorage.getItem('userId'));
        let input: Competence = {
          id: 0,
          competenceName: newSkill,
          isActive: true,
          idexpert: id,
        };
        this.competenceservice.addCompetence(input).subscribe({
          next: () => {
            this.data.push(input);
            console.log(this.data);
            this.skillControls.push(
              new FormControl(newSkill.trim(), Validators.required)
            );
            this.isEditing.push(false);
            this.newSkillControl.reset();
          },
          error: (er) => {
            console.log(er);
          },
        });

        //inside subscribe create object
      }
    }
  }

  /* addCompetence(){

    const id=Number(localStorage.getItem("userId"))
this.competence.idexpert=id
this.competence.isActive=true



const newSkill = this.newSkillControl.value;
this.competence.competenceName= newSkill
console.log("sofiene",this.competence)

    this.competenceservice.addCompetence(this.competence)
    .subscribe(result=>{

    console.log(result)

    },
    (err:HttpErrorResponse)=>this.errorMsg='this email is existe')
  }


  ajoutstagiaire(): void {

    const id=Number(localStorage.getItem("userId"))
    this.competence.idexpert=id
    this.competence.isActive=true


const newSkill = this.newSkillControl.value;
this.competence.competenceName= newSkill
console.log("sofiene",this.competence)

      this.competenceservice.addCompetence(this.competence)
        .subscribe({
          next: (res) => {
           // console.log(res);

          },
         // error: (e) => console.error(e)
        });


  }


*/
}

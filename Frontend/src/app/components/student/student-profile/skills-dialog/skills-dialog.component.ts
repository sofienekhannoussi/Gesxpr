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


  competence : Competence = {
    id:0,
    competenceName : "",
    isActive : false,
    idexpert : 0

  };

  errorMsg !: String;

  constructor( private competenceservice : CompetenceService,
    public dialogRef: MatDialogRef<SkillsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Competence[]
  ) {
    this.isEditing = new Array(data.length).fill(false);
    this.skillControls = data.map(skill => new FormControl(skill, Validators.required));
    this.newSkillControl = new FormControl('', Validators.required);
  }
  ngOnInit(): void {
    console.log("competenc",this.data)

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
        this.data[index] = skill.com;
      }
      this.isEditing[index] = false;
    }
  }

  deleteSkill(index: number): void {
    this.data.splice(index, 1);
    this.skillControls.splice(index, 1);
    this.isEditing.splice(index, 1);
  }

  addSkill(): void {
    if (this.newSkillControl.valid) {
      const newSkill = this.newSkillControl.value;
      if (newSkill !== null && newSkill.trim() !== '') {
        //api add skill.subscribe

        //inside subscribe create object
        this.data.push(newSkill.trim());
        this.skillControls.push(new FormControl(newSkill.trim(), Validators.required));
        this.isEditing.push(false);
        this.newSkillControl.reset();
      }
    }
  }

  addCompetence(){

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





}

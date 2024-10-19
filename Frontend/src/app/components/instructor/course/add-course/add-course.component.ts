import { Component, OnInit, OnDestroy, ViewEncapsulation } from '@angular/core';
import { toHTML } from 'ngx-editor';
import { AbstractControl, FormControl, FormGroup } from '@angular/forms';
import { Validators, Editor, Toolbar } from 'ngx-editor';
import { routes } from 'src/app/shared/service/routes/routes';
import { Mission } from 'src/app/modelSTG/mission';
import { OffreService } from 'src/app/servicesSTG/offre.service';
import { HttpErrorResponse } from '@angular/common/http';
import { MissionService } from 'src/app/servicesSTG/mission.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class AddCourseComponent implements OnInit, OnDestroy {
  registerOffer: Mission = new Mission();
  errorMsg:string=""
  msgSuccess:string=""

  public routes = routes;
  selected="1";
  selected2="1";
  submitted= false;
  public activeIndex:number=0
  editor!: Editor;
form!:FormGroup;
  toolbar: Toolbar = [
    ['bold', 'italic'],
    ['underline', 'strike'],
    ['code', 'blockquote'],
    ['ordered_list', 'bullet_list'],
    [{ heading: ['h1', 'h2', 'h3', 'h4', 'h5', 'h6'] }],
    ['link', 'image'],
    ['text_color', 'background_color'],
    ['align_left', 'align_center', 'align_right', 'align_justify'],
  ];
  constructor( private servoffre:MissionService,private router:Router){

  }

  ngOnInit(): void {
    this.submitted= false
    this.form = new FormGroup({
      title:new FormControl(null,Validators.required()),
      description:new FormControl(null,Validators.required()),
      dateDebut:new FormControl(null,Validators.required()),
      dateFin:new FormControl(null,Validators.required()),
      typeContrat:new FormControl(null,Validators.required()),
      typeLieu:new FormControl(null,Validators.required())
    });

    // this.editor = new Editor();
    const IDD=localStorage.getItem("userId");
    // console.log("ggggggg",IDD);
  }

  registeroffre(){

    if (this.form.invalid && this.submitted==true) {
      alert("Vous devez remplir les champs");

      return;
    }

    const IDD=localStorage.getItem("userId");
    this.registerOffer.idresponsablesoci=Number(IDD)
    this.registerOffer.title =this.form.value.title
    this.registerOffer.description=    this.form.value.description
    this.registerOffer.dateDebut= this.form.value.dateDebut
    this.registerOffer.dateFin=this.form.value.dateFin
    this.registerOffer.typeContrat=this.form.value.typeContrat
    this.registerOffer.typeLieu=this.form.value.typeLieu
    this.registerOffer.statut="En cours"

    console.log("ssssss", this.registerOffer)




    console.log("sou", this.form.value)
    // if(this.form.valid){




      // this.registerOffer.description= this.form.g
      this.servoffre.addMission(this.registerOffer)

      .subscribe(result=>{


        this.submitted= true



        this.msgSuccess="Mission created successfully"
        this.router.navigate(['instructor', 'mymissions'])

        setTimeout(() => {
          this.router.navigate(["instructor/mymissions"])
        }, 2000);


      },
      (err:HttpErrorResponse)=>this.errorMsg='not saved')
    //}
    }

  ngOnDestroy(): void {
    this.editor.destroy();
  }

  public onSubmit(index:number){
    this.activeIndex = index
  }

  public onBack(index:number){
    this.activeIndex = index

  }

  }

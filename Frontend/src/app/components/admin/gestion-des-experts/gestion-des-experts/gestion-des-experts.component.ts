import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ListProfilUser } from 'src/app/modelSTG/list-profil-user';
import { Respsociete } from 'src/app/modelSTG/respsociete';
import { AuthService } from 'src/app/servicesSTG/auth.service';
import { GestionProfilUserService } from 'src/app/servicesSTG/gestion-profil-user.service';

@Component({
  selector: 'app-gestion-des-experts',
  templateUrl: './gestion-des-experts.component.html',
  styleUrls: ['./gestion-des-experts.component.scss']
})
export class GestionDesExpertsComponent implements OnInit{
  listUserr:ListProfilUser[]=[]
  selectedUser!: ListProfilUser | null;
  viewmodeluser: ListProfilUser = new ListProfilUser();
  status_!:boolean
  FormGroupart: any;
  IdUser!: number
  currentPage: number = 1;
  itemsPerPage: number = 5; // Number of items per page
  totalItems: number = 0; // Total items for pagination
  sortedColumn: string = ''; // Column for sorting
  sortDirections: boolean = true; // true for ascending, false for descending
  sortField: string = '';
  sortDirection: string = 'asc';
  errorMsg ! : string;
  registerRequest: Respsociete = new Respsociete();
  FormGroupviewupd!:FormGroup


constructor(private  gestionProfilService:GestionProfilUserService,private authService : AuthService, ){

}
  ngOnInit(): void {
 
    this.FormGroupviewupd = new FormGroup({
      'email': new FormControl('', Validators.required),
      'password': new FormControl('', Validators.required),
      'fullname': new FormControl(''),
      'phone': new FormControl('', Validators.required),
   
      'adresse': new FormControl('', Validators.required),
   
      'presentationsociete': new FormControl('', Validators.required),
    });

    this.getlistUser() 
    this.totalItems = this.listUserr.length; 
    this.FormGroupart = new FormGroup({
  
      'active' : new FormControl('', Validators.required),
     
    });
  }



  get paginatedList() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    return this.listUserr.slice(startIndex, startIndex + this.itemsPerPage);
  }
  get totalPages(): number {
    return Math.ceil(this.listUserr.length / this.itemsPerPage);
  }
  changePage(page: number) {
    this.currentPage = page;
  }
  sortData(field: keyof ListProfilUser) {
    if (this.sortField === field) {
        // Toggle the sort direction if the same field is clicked
        this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
        // Set new field and default sort direction to ascending
        this.sortField = field;
        this.sortDirection = 'asc';
    }

    // Perform the sorting on `listUserr`
    this.listUserr.sort((a, b) => {
        const valueA = a[field];
        const valueB = b[field];

        if (valueA < valueB) return this.sortDirection === 'asc' ? -1 : 1;
        if (valueA > valueB) return this.sortDirection === 'asc' ? 1 : -1;
        return 0;
    });
}

getlistUser() {
    this.gestionProfilService.getList().subscribe(res => {
      this.listUserr = res
      console.log(res)
     
    } , error => {
        console.error(error)
    } , ()=> {

    })
}


getUserId(id:number)
      {
        this.IdUser = id
        console.log("iiiiiiiiiiiiii",id);
        if(id!=undefined && id !=null)
        {
          this.gestionProfilService.findUserById(id).subscribe(
            res=>{
              console.log(res);
              this.viewmodeluser=res
          },error=>{
            console.error(error)
          },()=>{
          
          });
        }
        console.log("iiiiiiiiiiiiii",id);
      }

 updateSatusUser() {
      
        console.log('status',this.FormGroupart.value.active)
        console.log('idUser',this.IdUser)
          this.gestionProfilService.ModifiedSatusUser(this.FormGroupart.value.active, this.IdUser ).subscribe(
            res=>{
              alert('status modifie')
              console.log(res);
              this.viewmodeluser=res
              this.getlistUser() 
          },error=>{
            console.error(error)
          },()=>{
          
          });
        
    
      }
      registerresponsables(){

        this.registerRequest.email = this.FormGroupviewupd.value.email;
        this.registerRequest.password = this.FormGroupviewupd.value.password;
        this.registerRequest.fullname = this.FormGroupviewupd.value.fullname;
        this.registerRequest.adresse = this.FormGroupviewupd.value.adresse;
        this.registerRequest.phone = this.FormGroupviewupd.value.phone;
        this.registerRequest.presentationsociete = this.FormGroupviewupd.value.presentationsociete;
        this.authService.registerresposable(this.registerRequest)
        .subscribe(result=>{
          console.log("TTTTTTTT")
          console.log(result)
          this.getlistUser() 
        },
      
        (err:HttpErrorResponse)=>this.errorMsg='this email is existe')
      }
}
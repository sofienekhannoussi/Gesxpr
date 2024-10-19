import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { Contrat } from 'src/app/modelSTG/contrat';
import { Respsociete } from 'src/app/modelSTG/respsociete';
import { ContratService } from 'src/app/servicesSTG/contrat.service';
import { routes } from 'src/app/shared/service/routes/routes';

@Component({
  selector: 'app-contrat',
  templateUrl: './contrat.component.html',
  styleUrls: ['./contrat.component.scss']
})
export class ContratComponent implements OnInit{
  public routes = routes;
  errorMsg:string=""
  expertCount=0
  form!:FormGroup;
  msgSuccess:string="";
  file!:File
  submitted!:boolean
  idMission!:number
  id!:number
  imgUrl: string | ArrayBuffer = 'assets/components/logo.png'
  public contrat:Contrat = new Contrat();




  expertCountMap: Map<number, number> = new Map();
  public exp:Respsociete = new Respsociete();


  viewmodelcontrat: Contrat = new Contrat();
  listContrat:Contrat[] = [];

  public instructorCourse: any = [];
  public searchDataValue = '';
  dataSource!: MatTableDataSource<any>;

  // pagination variables
  public lastIndex: number = 0;
  public pageSize: number = 10;
  public totalData: any = 0;
  public skip: number = 0;
  public limit: number = this.pageSize;
  public pageIndex: number = 0;
  public serialNumberArray: Array<any> = [];
  public currentPage: number = 1;
  public pageNumberArray: Array<any> = [];
  public pageSelection: Array<pageSelection> = [];
  public totalPages: number = 0;
  selected = '1';
  registerOffer: any;
  servoffre: any;
  constructor(private serviceContrat: ContratService,private route : ActivatedRoute) {


  }


  ngOnInit(): void {


    /* this.getinstructorCourse();
  }
  private getinstructorCourse(): void {
    this.instructorCourse = [];
    this.serialNumberArray = [];

    this.data.instructorCourseList().subscribe((res: any) => {
      this.totalData = res.totalData;
      res.data.map((res: any, index: number) => {
        let serialNumber = index + 1;
        if (index >= this.skip && serialNumber <= this.limit) {
          res.id = serialNumber;
          this.instructorCourse.push(res);
          this.serialNumberArray.push(serialNumber);
        }
      });
         this.dataSource = new MatTableDataSource<any>(this.instructorCourse);
    this.calculateTotalPages(this.totalData, this.pageSize);
    });
 */
    this.id = +this.route.snapshot.paramMap.get('id')!;
    this.idMission = +this.route.snapshot.paramMap.get('idMission')!;
    const id = Number(localStorage.getItem('userId'));

        // getExpertByMission()



  }
////////////// ADD CONTRAT ///////////

AddContrat(){
  this.serviceContrat.addContrat(this.contrat)
  .subscribe(result=>{
    console.log(result)

  },
  (err:HttpErrorResponse)=>this.errorMsg='not saved')
}
/////////////////////////
saveContrat() {
  const ids=Number(localStorage.getItem("userId"))
  this.contrat.responsibleId = ids
  this.contrat.missionId=this.idMission
  this.contrat.expertId = this.id
  console.log("contrat",this.contrat)
  this.serviceContrat.addContrat(this.contrat)
      .subscribe({
        next: (res) => {
          console.log(res)
          this.serviceContrat.uploadCoursDtoFile(res.id, this.file).subscribe(
            val =>  {} , error => { alert('oups')} , () => {
              this.submitted = true ;
            alert("contrat a été ajouté!")
            // this.router.navigate(['/vendeur/gererarticle']);
             // this.FormGroupart.reset();
           //   this.toastrService.success('Success!', 'produit a été ajouté!');
            });

        },
      });}

//////////GET CONTRAT BY ID ////////////////
getContratId(id:number)
      {
        console.log("iiiiiiiiiiiiii",id);
        if(id!=undefined && id !=null)
        {
          this.serviceContrat.findContratById(id).subscribe(
            res=>{
              console.log(res);
              this.viewmodelcontrat=res
          },error=>{
            console.error(error)
          },()=>{

          });
        }
        console.log("iiiiiiiiiiiiii",id);
      }




  getLisContrat(id:number)
  {
    this.serviceContrat.getListContrat().subscribe(res => {
      this.listContrat = res
      console.log(res)

    } , error => {
        console.error(error)
    } , ()=> {

    })
  }
  deleteContrat(id: number): void {
    this.serviceContrat.deleteByIdContrat(id).subscribe({
      next: (data) => {
        console.log(`Expert count for mission ${id}: ${data}`);
      },
      error: (error) => {
        console.error('Error fetching expert count:', error);
      }
    });
  }


  // deleteMission(id: number): void {
  //   const userId = Number(localStorage.getItem('userId'));
  //   if (confirm('Are you sure you want to delete this experience item?')) {
  //     this.missionservice.deleteById(id).subscribe( {
  //       next: () => {


  //       },  error: (er) => {
  //         console.log(er);
  //       },
  //     })
  //   }
  // }
/*
  openEducationsDialog() {


    const dialogRef = this.dialog.open(EducationsPopupComponent, {
      width: '600px',
      data: this.missionn,
    });

    dialogRef.afterClosed().subscribe((result) => {
      const id = Number(localStorage.getItem('userId'));
      this.missionservice.getList(id)
    });
  }
 */

  public sortData(sort: Sort) {
    const data = this.instructorCourse.slice();

    if (!sort.active || sort.direction === '') {
      this.instructorCourse = data;
    } else {
      this.instructorCourse = data.sort((a: any, b: any) => {
        const aValue = (a as any)[sort.active];
        const bValue = (b as any)[sort.active];
        return (aValue < bValue ? -1 : 1) * (sort.direction === 'asc' ? 1 : -1);
      });
    }
  }

  public searchData(value: any): void {
    this.dataSource.filter = value.trim().toLowerCase();
    this.instructorCourse = this.dataSource.filteredData;
  }

/* public getMoreData(event: string): void {
    if (event == 'next') {
      this.currentPage++;
      this.pageIndex = this.currentPage - 1;
      this.limit += this.pageSize;
      this.skip = this.pageSize * this.pageIndex;
      this.getinstructorCourse();
    } else if (event == 'previous') {
      this.currentPage--;
      this.pageIndex = this.currentPage - 1;
      this.limit -= this.pageSize;
      this.skip = this.pageSize * this.pageIndex;
      this.getinstructorCourse();
    }
}*/

/*   public moveToPage(pageNumber: number): void {
    this.currentPage = pageNumber;
    this.skip = this.pageSelection[pageNumber - 1].skip;
    this.limit = this.pageSelection[pageNumber - 1].limit;
    if (pageNumber > this.currentPage) {
      this.pageIndex = pageNumber - 1;
    } else if (pageNumber < this.currentPage) {
      this.pageIndex = pageNumber + 1;
    }
    this.getinstructorCourse();
    } */
/*
  public changePageSize(): void {
    this.pageSelection = [];
    this.limit = this.pageSize;
    this.skip = 0;
    this.currentPage = 1;
    this.getinstructorCourse();
  }
 */
  private calculateTotalPages(totalData: number, pageSize: number): void {
    this.pageNumberArray = [];
    this.totalPages = totalData / pageSize;
    if (this.totalPages % 1 != 0) {
      this.totalPages = Math.trunc(this.totalPages + 1);
    }
    for (var i = 1; i <= this.totalPages; i++) {
      let limit = pageSize * i;
      let skip = limit - pageSize;
      this.pageNumberArray.push(i);
      this.pageSelection.push({ skip: skip, limit: limit });
    }
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
      event.target.src = "assets/components/logo.png";
    }

}





export interface pageSelection {
  skip: number;
  limit: number;

}

import { AfterViewInit, Component, ElementRef, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { DataService } from 'src/app/shared/service/data/data.service';
import { Sort } from '@angular/material/sort';
import { routes } from 'src/app/shared/service/routes/routes';
import { MissionService } from 'src/app/servicesSTG/mission.service';
import { Router } from '@angular/router';
import { Mission } from 'src/app/modelSTG/mission';
import { HttpErrorResponse } from '@angular/common/http';
import { ProfilExpertService } from 'src/app/servicesSTG/profil-expert.service';
import { Respsociete } from 'src/app/modelSTG/respsociete';
import { tap } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { FormControl, FormGroup} from '@angular/forms';
import { Validators, Editor, Toolbar } from 'ngx-editor';
import { OffreService } from 'src/app/servicesSTG/postuleoffre.service';
import { ListpostuleBymission } from 'src/app/modelSTG/listpostule-bymission';
@Component({
  selector: 'app-instructor-course',
  templateUrl: './instructor-course.component.html',
  styleUrls: ['./instructor-course.component.scss']
})
export class InstructorCourseComponent implements OnInit {
  public routes = routes;
  errorMsg:string=""
  expertCount=0
  idmission!: number
  submitted!:boolean
  form!:FormGroup;
  msgSuccess:string=""
  listPostuleoffre!:ListpostuleBymission[]



  expertCountMap: Map<number, number> = new Map();
  public exp:Respsociete = new Respsociete();
    public missionn: Mission [] = [];
  missionss: Mission = new Mission();
  viewmodelmission: Mission = new Mission();

  public instructorCourse: any = [];
  public searchDataValue = '';
  dataSource!: MatTableDataSource<any>;

  @ViewChild('closeModalBtns') closeModalBtns!: ElementRef;


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
  constructor(private data: DataService,private  profilexpert : ProfilExpertService ,private missionservice:MissionService,
    private router: Router, private postuleoffre:OffreService) {
this.missionn = this.instructorCourse
  }


  ngOnInit(): void {

    this.form = new FormGroup({
      title:new FormControl("",Validators.required()),
      description:new FormControl("",Validators.required()),
      dateDebut:new FormControl("",Validators.required()),
      dateFin:new FormControl("",Validators.required()),
      typeContrat:new FormControl("",Validators.required()),
      typeLieu:new FormControl("",Validators.required())
    });
    
    const id = Number(localStorage.getItem('userId'));
    this.getbyisresp(id);
    this.getlistMission(id);
    // Wait for missionn to be populated before calling getExpertByMission
    this.getlistMission(id).subscribe(() => {
      this.missionn.forEach(mission => {
        this.getExpertByMission(mission.id);
      });
    });


  }




getMission(id:number)
      {
        console.log("iiiiiiiiiiiiii",id);
        if(id!=undefined && id !=null)
        {
          this.missionservice.finddpetById(id).subscribe(
            res=>{
              console.log(res);
              this.viewmodelmission=res
          },error=>{
            console.error(error)
          },()=>{


            this.form.get("title")?.setValue(this.viewmodelmission.title);
            this.form.get("Description")?.setValue(this.viewmodelmission.description);
            this.form.get("dateDebut")?.setValue(this.viewmodelmission.dateDebut);
            this.form.get("dateFin")?.setValue(this.viewmodelmission.dateFin);
            this.form.get("typeContrat")?.setValue(this.viewmodelmission.typeContrat);
            this.form.get("typeLieu")?.setValue(this.viewmodelmission.typeLieu);

            this.form.updateValueAndValidity()
          });
        }
        console.log("iiiiiiiiiiiiii",id);
      }


  getbyisresp(id : number) {
    this.profilexpert.findrespById(id).subscribe({
      next: (data) => {
        this.instructorCourse=data
       // this.updateEvents()
       console.log(data);

      },
      error: console.log,
    });
  }


  // getlistMission(id : number) {
  //   this.missionservice.listeallMissionByRESP(id).subscribe({
  //     next: (data) => {
  //       this.missionn=data
  //      // this.updateEvents()
  //      console.log(data,"ssssssssssss");


  //     },
  //     error: console.log,
  //   });
  // }

  getlistMission(id: number) {
    return this.missionservice.listeallMissionByRESP(id).pipe(
      tap((data: Mission[]) => {
        this.missionn = data;
        console.log(data, "ssssssssssss");
      })
    );
  }

  getExpertByMission(id: number): void {
    this.missionservice.countExpertByMission(id).subscribe({
      next: (data) => {
        this.expertCountMap.set(id, data);
        console.log(`Expert count for mission ${id}: ${data}`);
      },
      error: (error) => {
        console.error('Error fetching expert count:', error);
      }
    });
  }
  getPostuletByMission(id: number): void {
    this.postuleoffre.getLisPostulBymissiont(id).subscribe({
      next: (data) => {
        this.listPostuleoffre = data
      },
      error: (error) => {
      
      }
    });
  }


  deleteMission(id: number): void {
    const userId = Number(localStorage.getItem('userId'));
    if (confirm('Are you sure you want to delete this experience item?')) {
      this.missionservice.deleteById(id).subscribe( {
        next: () => {


        },  error: (er) => {
          console.log(er);
        },
      })
    }
  }
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

navigateToProfil(id:number){
  console.log('idExpert',id)
  this.closeModalBtns.nativeElement.click()
  this.router.navigate(["expert/profil-expert-for-responsable", id]);

}
navigateToContrat(id:number,idMission:number){
  console.log('idExpert',id)
  this.closeModalBtns.nativeElement.click()
  this.router.navigate(["instructor/contrat", id,idMission]);

}





}
export interface pageSelection {
  skip: number;
  limit: number;
}

import { AfterViewInit, Component, ElementRef, OnInit, QueryList, ViewChildren } from '@angular/core';
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

  expertCountMap: Map<number, number> = new Map();
  public exp:Respsociete = new Respsociete();
    public missionn: Mission [] = [];
  missionss: Mission = new Mission();

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
  constructor(private data: DataService,private  profilexpert : ProfilExpertService ,private missionservice:MissionService,private router: Router) {
this.missionn = this.instructorCourse
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

    const id = Number(localStorage.getItem('userId'));
    this.getbyisresp(id);
    this.getlistMission(id);
    // Wait for missionn to be populated before calling getExpertByMission
    this.getlistMission(id).subscribe(() => {
      this.missionn.forEach(mission => {
        this.getExpertByMission(mission.id);
      });
    });

        // getExpertByMission()



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
}
export interface pageSelection {
  skip: number;
  limit: number;
}

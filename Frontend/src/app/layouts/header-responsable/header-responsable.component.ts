import { Component, OnInit } from '@angular/core';
import { Expertbyid } from 'src/app/modelSTG/expertbyid';
import { ProfilExpertService } from 'src/app/servicesSTG/profil-expert.service';
import { CommonService } from 'src/app/shared/service/common/common.service';
import { DataService } from 'src/app/shared/service/data/data.service';
import { routes } from 'src/app/shared/service/routes/routes';
import { SidebarService } from 'src/app/shared/service/sidebar/sidebar.service';

@Component({
  selector: 'app-header-responsable',
  templateUrl: './header-responsable.component.html',
  styleUrls: ['./header-responsable.component.scss']
})
export class HeaderResponsableComponent implements OnInit {

  base: string = '';
  page: string = '';
  last: string = '';
  public routes = routes;
  public exp:Expertbyid = new Expertbyid();



  sidebar: Array<any> = [];
  constructor(
    private  profilexpert : ProfilExpertService ,
    private common: CommonService,
    private data: DataService,
    private sidebarService: SidebarService
  ) {
    // this.Router.events.subscribe((data: any) => {
    //   if (data instanceof NavigationStart) {
    //     this.hideSidebar();
    //     this.base = data.url.split('/')[1];
    //     this.page = data.url.split('/')[2];
    //     this.last = data.url.split('/')[3];
    //   }
    // });
    this.common.base.subscribe((res: any) => {
      this.base = res;
    });
    this.common.page.subscribe((res: any) => {
      this.page = res;
    });
    this.common.last.subscribe((res: any) => {
      this.last = res;
    });
    this.sidebar = this.data.sideBar;
  }

  getbyisExpert() {
    const id=Number(localStorage.getItem("userId"))

    this.profilexpert.finddpetById(id).subscribe({
      next: (data) => {
        this.exp=data
       // this.updateEvents()
       console.log("qqqqqqq",data);

      },
      error: console.log,
    });
  }

  ngOnInit(): void {
    this.getbyisExpert();

  }
  public toggleSidebar(): void {
    this.sidebarService.openSidebar();
  }
  public hideSidebar(): void {
    this.sidebarService.closeSidebar();
  }
}



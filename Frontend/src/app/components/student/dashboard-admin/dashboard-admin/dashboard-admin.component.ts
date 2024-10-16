import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { CompetenceService } from 'src/app/servicesSTG/competence.service';
import { ContratService } from 'src/app/servicesSTG/contrat.service';
import { MissionService } from 'src/app/servicesSTG/mission.service';
import { OffreService } from 'src/app/servicesSTG/postuleoffre.service';

@Component({
  selector: 'app-dashboard-admin',
  templateUrl: './dashboard-admin.component.html',
  styleUrls: ['./dashboard-admin.component.scss']
})
export class DashboardAdminComponent implements OnInit{
  constructor(private missionServive:MissionService, private contratService:ContratService,private competencesService:CompetenceService,
    private PostuleService: OffreService
   ){}
   numberCompetences!:number
  numberMission!:number
  numberContrat!:number
  numberPostule!:number
  ngOnInit(): void {

    this.countAllCompetencesbyExpert()
    this.fetchDataMission()
    this.countAllContratbyExpert()
    this.countAllPostule()
  }

 /*  fetchDataMission(){
    this.missionServive.getMissionCountByMonthAndYear().subscribe(data => {
      const transformedData = this.transformData(data);
      console.log(data)
      this.renderChart(transformedData);
    });

  }
  transformData(data: any): any {
    const labels = [];
    const counts = [];


    for (const year in data) {
      for (const month in data[year]) {
        const label = `${year}-${String(month).padStart(2, '0')}`;
        labels.push(label);
        counts.push(data[year][month]);
      }
    }

} */
    fetchDataMission() {
      this.missionServive.getMissionCountByMonthAndYear().subscribe(data => {
        const transformedData = this.transformData(data);
        console.log(data);
        this.renderChart(transformedData);
      });
    }

    transformData(data: any): any {
      const labels: string[] = [];
      const counts: number[] = [];

      for (const year in data) {
        for (const month in data[year]) {
          const label = `${year}-${String(month).padStart(2, '0')}`; // Créer une étiquette du type 'YYYY-MM'
          labels.push(label); // Ajouter l'étiquette à la liste des labels
          counts.push(data[year][month]); // Ajouter le nombre de missions au tableau des counts
        }
      }

      // Retourner les données au format attendu par Chart.js
      return {
        labels: labels,
        datasets: [{
          label: 'Nombre de missions',
          data: counts,
          backgroundColor: 'rgba(75, 192, 192, 0.2)',
          borderColor: 'rgba(75, 192, 192, 1)',
          borderWidth: 1
        }]
      };
    }
renderChart(data: any): void {
  const ctx = document.getElementById('missionChart') as HTMLCanvasElement;
  new Chart(ctx, {
    type: 'bar',
    data: data,
    options: {
      scales: {
        x: {
          beginAtZero: true
        },
        y: {
          beginAtZero: true
        }
      }
    }
  });
}

countAllCompetencesbyExpert(){
  const id=Number(localStorage.getItem("userId"))

  this.competencesService.countCompetencesbyexpert(id).subscribe(data => {
    console.log(data);
    this.numberCompetences = data

  });
}

countAllContratbyExpert(){
  const id=Number(localStorage.getItem("userId"))

  this.contratService.countContratbyexpert(id).subscribe(data => {
    console.log(data);
    this.numberContrat = data

  });
}
countAllPostule(){
  this.PostuleService.countPostule().subscribe(data => {
    console.log(data);
    this.numberPostule = data

  });
}


}

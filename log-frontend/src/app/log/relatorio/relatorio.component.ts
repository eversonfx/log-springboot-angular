import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LogReport } from '../model/logReport';
import { LogService } from '../services';

@Component({
  selector: 'app-relatorio',
  templateUrl: './relatorio.component.html',
  styleUrls: ['./relatorio.component.css']
})
export class RelatorioComponent implements OnInit {
  logReports: LogReport[];

  constructor(private logService: LogService, private router: Router) { }

  ngOnInit(): void {
    this.getLogReports("ip");
  }

  getLogReports(option?: string) {
    this.logService.groupBySearch(option).subscribe(data => {
      this.logReports = data;
    });
  }

}

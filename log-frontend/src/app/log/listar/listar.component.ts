import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Log } from '../model';
import { LogService } from '../services';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {
  logs: Log[];

  searchText: string = '';

  totalNumRecords: number;
  totalNumPages: number;
  elementsPerPage: number = 15;
  currentPage: number = 1;
  begin: number = 0;
  end: number = this.begin + this.elementsPerPage;

  constructor(private logService: LogService, private router: Router) { }

  ngOnInit(): void {
    this.getLogs();
    this.getTotalReg();
  }

  onSearch() {
    this.logService.searchLogs(this.searchText).subscribe(data => {
      this.searchText = '';
      this.logs = data;
      this.totalNumPages = 1;
    });
  }

  cleanSearch() {
    this.getLogs();
    this.getTotalReg();
  }

  getTotalReg() {
    this.logService.getTotalNumReg().subscribe(data => {
      this.totalNumRecords = data;
      this.totalNumPages = Math.ceil(data / this.elementsPerPage);
    });
  }

  private getLogs() {
    this.logService.getLogsList(this.begin, this.end).subscribe(data => {
      this.logs = data;
    });
  }

  addLog() {
    this.router.navigate(['add-log']);
  }

  updateLog(id: number) {
    this.router.navigate(['edit-log', id]);
  }

  logDetails(id: number) {
    this.router.navigate(['details-log', id]);
  }

  deleteLog(id: number) {
    this.logService.deleteLog(id).subscribe(data => {
      this.getLogs();
    })
  }

  prevPage(pos?: string) {
    if (pos == "first") {
      this.currentPage = 1;
      this.begin = 1;
      this.end = this.elementsPerPage;
    } else if (this.currentPage > 1) {
      this.currentPage = this.currentPage-1;
      this.begin = this.begin - this.elementsPerPage;
      this.end = (this.begin - 1) + this.elementsPerPage;
    }

    this.logService.getLogsList(this.begin, this.end).subscribe(data => {
      this.logs = data;
    });
  }

  nextPage(pos?: string) {
    if (pos == "last") {
      this.currentPage = this.totalNumPages;
      this.begin = this.elementsPerPage * (this.totalNumPages - 1);
      this.end = this.totalNumRecords;
    } else if (this.currentPage < this.totalNumPages) {
      this.currentPage = this.currentPage + 1;
      this.begin = this.end + 1;
      this.end = this.end + this.elementsPerPage;
    }

    this.logService.getLogsList(this.begin, this.end).subscribe(data => {
      this.logs = data;
    });
  }
}

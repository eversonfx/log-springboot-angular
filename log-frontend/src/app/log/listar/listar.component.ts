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

  constructor(private logService: LogService, private router: Router) { }

  ngOnInit(): void {
    this.getLogs();
  }

  private getLogs() {
    this.logService.getLogsList().subscribe(data => {
      this.logs = data;
      console.log(data);
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
      console.log(data);
      this.getLogs();
    })
  }
}

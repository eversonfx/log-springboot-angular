import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Log } from '../model';
import { LogService } from '../services';

@Component({
  selector: 'app-adicionar',
  templateUrl: './adicionar.component.html',
  styleUrls: ['./adicionar.component.css']
})
export class AdicionarComponent implements OnInit {

  log: Log = new Log();
  constructor(private logService: LogService, private router: Router) { }

  ngOnInit(): void {
  }

  saveLog() {
    this.logService.createLog(this.log).subscribe( data => {
      console.log(data);
      this.gotToLogList();
    },
      error => console.log(error));
  }

  gotToLogList() {
    this.router.navigate(['/list-log']);
  }

  onSubmit() {
    console.log(this.log);    
    this.saveLog();
  }

}

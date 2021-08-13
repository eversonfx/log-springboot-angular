import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Log } from '../model';
import { LogService } from '../services';

@Component({
  selector: 'app-editar',
  templateUrl: './editar.component.html',
  styleUrls: ['./editar.component.css']
})
export class EditarComponent implements OnInit {
  id: number;
  log: Log = new Log();
  
  constructor(private logService: LogService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
     this.logService.getLogById(this.id).subscribe(data => {
        this.log = data;
     }, error => console.log(error));
  }

  onSubmit() {
    this.logService.updateLog(this.id, this.log).subscribe(data => {
        this.gotToLogList();
   }, error => console.log(error));
  }

gotToLogList() {
  this.router.navigate(['/list-log']);
}

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Log } from '../model';
import { LogService } from '../services';

@Component({
  selector: 'app-exibir',
  templateUrl: './exibir.component.html',
  styleUrls: ['./exibir.component.css']
})
export class ExibirComponent implements OnInit {
  id: number
  log: Log

  constructor(private route: ActivatedRoute, private logService: LogService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.log = new Log();
    this.logService.getLogById(this.id).subscribe(data => {
      this.log = data;
    });
  }
}

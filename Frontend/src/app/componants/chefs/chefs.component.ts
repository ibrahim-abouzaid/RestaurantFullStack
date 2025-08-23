import {Component, OnInit} from '@angular/core';
import {Chef} from '../../../model/chef';
import {ChefService} from '../../../service/chef.service';


@Component({
  selector: 'app-chefs',
  templateUrl: './chefs.component.html',
  styleUrls: ['./chefs.component.css']
})
export class ChefsComponent implements OnInit {

  chefs: Chef[] = [];
  ms:number =1;
  constructor(private chefService: ChefService) {

  }

  ngOnInit(): void {
   this.getChefs();
  }


  getChefs():void {
    this.chefService.getChefs().subscribe(
      value => this.chefs = value
    );
  }

}

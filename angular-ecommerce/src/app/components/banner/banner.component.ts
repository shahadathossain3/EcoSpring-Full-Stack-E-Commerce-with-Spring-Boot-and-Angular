import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css',]
})
export class BannerComponent implements OnInit{
  images: string='assets/images/image1.jpg'


  constructor() { }

  ngOnInit(): void {

  }



}

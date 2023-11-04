import {AfterViewInit, Component, OnInit} from '@angular/core';
import { Chart } from 'chart.js/auto';
import {ProductOrderService} from "../../services/product-order.service";

@Component({
  selector: 'app-admin-template',
  templateUrl: './admin-template.component.html',
  styleUrls: ['./admin-template.component.css']
})
export class AdminTemplateComponent implements OnInit, AfterViewInit{

  pending=0;
  delivery=0;

  constructor(private productOrderService:ProductOrderService) {
  }

  ngAfterViewInit(): void {
    this.createPieChart();
    this.createBarChart();
  }


  ngOnInit(): void {
    this.countData();
  }

  countData()
  {
    this.productOrderService.getPendingDeviveryCount().subscribe(data=>{
      this.pending=data.pendingQuantity;
      this.delivery=data.pendingDelivery;

      console.log(data)
    })
  }


  createPieChart() {
    const pieData = {
      labels: ['Red', 'Blue', 'Yellow'],
      datasets: [{
        data: [10, 20, 30],
        backgroundColor: ['red', 'blue', 'yellow']
      }]
    };

    const pieOptions = {
      responsive: true
    };

    new Chart('pieChart', {
      type: 'pie',
      data: pieData,
      options: pieOptions
    });
  }

  createBarChart() {
    const barData = {
      labels: ['January', 'February', 'March', 'April', 'May'],
      datasets: [{
        label: 'Sales',
        data: [50, 75, 100, 60, 120],
        backgroundColor: 'blue'
      }]
    };

    const barOptions = {
      responsive: true,
      scales: {
        y: {
          beginAtZero: true
        }
      }
    };

    new Chart('barChart', {
      type: 'bar',
      data: barData,
      options: barOptions
    });
  }




}

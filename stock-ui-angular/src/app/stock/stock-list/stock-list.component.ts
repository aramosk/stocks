import{Component, OnInit}from '@angular/core';
import {Stock}from "../stock";
import {StockService}from "../stock.service";
import {Router}from '@angular/router';

@Component({
  selector: 'app-stock-list',
  templateUrl: './stock-list.component.html',
  styleUrls: ['./stock-list.component.css'],
  providers: [StockService]
})
export class StockListComponent implements OnInit {

  private stocks: Stock[];

  constructor(private router: Router,
    private stockService: StockService) { }

  ngOnInit() {
    this.getAllStocks();
  }

  getAllStocks() {
    this.stockService.findAll().subscribe(
      stocks => {
        this.stocks = stocks;
      },
      err => {
        console.log(err);
      }

    );
  }

  redirectNewStockPage() {
    this.router.navigate(['/stock/create']);
  }

  editStockPage(stock: Stock) {
    if (stock) {
      this.router.navigate(['/stock/edit', stock.id]);
    }
  }

  deleteStock(stock: Stock) {
    if (stock) {
      this.stockService.deleteStockById(stock.id).subscribe(
        res => {
          this.getAllStocks();
          this.router.navigate(['/stock']);
          console.log('done');
        }
      );
    }
  }

}

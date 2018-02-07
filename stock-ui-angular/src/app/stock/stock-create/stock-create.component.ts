import{Component, OnDestroy, OnInit}from '@angular/core';
import {FormControl, FormGroup, Validators}from "@angular/forms";
import {StockService}from "../stock.service";
import {Stock}from "../stock";
import {ActivatedRoute, Router}from '@angular/router';


@Component({
  selector: 'app-stock-create',
  templateUrl: './stock-create.component.html',
  styleUrls: ['./stock-create.component.css'],
  providers: [StockService]
})
export class StockCreateComponent implements OnInit, OnDestroy {

  id: number;
  stock: Stock;

  stockForm: FormGroup;
  private sub: any;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private stockService: StockService) { }

  ngOnInit() {
      this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
      });

      this.stockForm = new FormGroup({
      name: new FormControl('', Validators.required),
      currentPrice: new FormControl('', Validators.required)
      });

      if (this.id) { //edit form
      this.stockService.findById(this.id).subscribe(
        stock => {
            this.id = stock.id;
            this.stockForm.patchValue({
            name: stock.name,
            currentPrice: stock.currentPrice,
          });
         },error => {
          console.log(error);
         }
      );

    }

  }

  ngOnDestroy(): void {
  }

  onSubmit() {
    if (this.stockForm.valid) {

        this.sub = this.route.params.subscribe(params => {
        this.id = params['id'];
        });

        let stock: Stock = new Stock(this.id,
        this.stockForm.controls['name'].value,
        this.stockForm.controls['currentPrice'].value, null);
        this.stockService.saveStock(stock).subscribe();

     }
      this.stockForm.reset();
      this.router.navigate(['/stock']);
  }

  redirectStockPage() {
    this.router.navigate(['/stock']);

  }
}

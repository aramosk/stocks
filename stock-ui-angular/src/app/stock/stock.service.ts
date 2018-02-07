import{Injectable}from'@angular/core';
import {Stock }from "./stock";
import {Http, Response}from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import {Observable }from "rxjs/Observable";

@Injectable()
export class StockService {

private apiUrl ='http://localhost:8080/api/stocks'

constructor(private http: Http) {
  }

  findAll(): Observable<Stock[]> {
    return this.http.get(this.apiUrl)
      .map((res:Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  saveStock(stock: Stock): Observable<Stock> {
    return this.http.post(this.apiUrl, stock)
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));

  }

 findById(id: number): Observable<Stock> {
    return this.http.get(this.apiUrl + '/' + id)
      .map((res:Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Error'));
  }

  updateStock(stock: Stock): Observable<Stock> {
    return this.http.put(this.apiUrl, stock)
      .map((res:Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  deleteStockById(id: number): Observable<boolean> {
    return this.http.delete(this.apiUrl + '/' + id)
      .map((res:Response) => res)
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'))
      ;
  }

}

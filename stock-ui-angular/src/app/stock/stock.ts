export class Stock {

  id: number;
name: string;
currentPrice: number;
lastUpdate: string;

constructor(id: number, name: string, currentPrice: number, lastUpdate: string){
    this.id = id;
    this.name = name;
    this.currentPrice = currentPrice;
    this.lastUpdate = lastUpdate;
  }

}

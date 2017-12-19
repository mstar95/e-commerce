export class AuctionDetail {
  id: number;
  name: string;
  imageId: number;
  description: string;
  price: number;
  create: Date;
  deadline: Date;
  buyNow: boolean;
  owner = false;
  winner = false;
}

import {Component, OnInit} from '@angular/core';
import {AuctionDetail} from '../auctionDetail';
import {Router} from '@angular/router';
import {AuctionService} from '../auction.service';
import {ImageService} from "../../image/image.service";

@Component({
  selector: 'app-add-auction',
  templateUrl: './add-auction.component.html',
  styleUrls: ['./add-auction.component.css']
})
export class AddAuctionComponent implements OnInit {

  auctionDetail: AuctionDetail = new AuctionDetail();
  imageUrl: any;
  imageData: any;
  loading = false;

  constructor(private router: Router,
              private auctionService: AuctionService,
              private imageService: ImageService) {
  }

  ngOnInit() {
  }

  fileSelected(image: any) {
    const reader = new FileReader();
    reader.onload = (e: any) => {
      this.imageUrl = e.target.result;
    };
    reader.readAsDataURL(image);
    this.imageData = image;
  }

  add() {
    //  this.loading = true;
    this.imageService.save(this.imageData)
      .subscribe(imageId => {
        this.auctionDetail.imageId = imageId;
        this.auctionService.addAuction(this.auctionDetail)
          .subscribe((id) => this.goDetails(id));
      });
  }

  goDetails(id: number) {
    if (id) {
      this.router.navigate(['auction-detail/' + id]);
    }
    this.loading = false;
  }

}

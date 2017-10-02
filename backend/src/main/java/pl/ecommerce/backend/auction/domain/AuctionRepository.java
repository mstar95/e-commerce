package pl.ecommerce.backend.auction.domain;

import org.springframework.data.repository.CrudRepository;

interface AuctionRepository extends CrudRepository<Auction, Long> {

}

package pl.ecommerce.backend.auction.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface AuctionRepository extends CrudRepository<Auction, Long> {
    List<Auction> findAuctionsByUserId(Long userId);
}
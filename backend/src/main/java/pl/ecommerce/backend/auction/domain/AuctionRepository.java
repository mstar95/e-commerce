package pl.ecommerce.backend.auction.domain;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface AuctionRepository extends Repository<Auction, Long> {
    Auction save(Auction entity);
    Optional<Auction> findById(Long id);
    List<Auction> findAuctionsByUserId(Long userId);
}
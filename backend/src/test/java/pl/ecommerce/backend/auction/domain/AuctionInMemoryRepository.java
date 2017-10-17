package pl.ecommerce.backend.auction.domain;

import pl.ecommerce.backend.repository.InMemoryRepository;

import java.util.List;
import java.util.stream.Collectors;

class AuctionInMemoryRepository  extends InMemoryRepository<Auction> implements AuctionRepository {

    @Override
    public Auction save(Auction entity) {
        Long id =  entity.getId();
        if (entity.getId() == null) {
            id = nextId++;
            entity.setId(id);
        }
        repository.put(id, entity);
        return entity;
    }

    @Override
    public List<Auction> findAuctionsByUserId(Long userId) {
        return repository.values().stream()
            .filter(auction -> userId.equals(auction.getId()))
            .collect(Collectors.toList());
    }
}

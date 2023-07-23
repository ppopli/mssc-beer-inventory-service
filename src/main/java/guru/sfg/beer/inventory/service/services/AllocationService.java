package guru.sfg.beer.inventory.service.services;

import com.pulkit.sfgBrewery.model.BeerOrderDto;

public interface AllocationService {
  Boolean allocateOrder(BeerOrderDto beerOrderDto);
  void deallocateOrder(BeerOrderDto beerOrderDto);
}

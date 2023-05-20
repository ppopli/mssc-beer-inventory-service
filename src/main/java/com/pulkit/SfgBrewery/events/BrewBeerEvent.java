package com.pulkit.SfgBrewery.events;

import com.pulkit.SfgBrewery.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent{
  public BrewBeerEvent(BeerDto beerDto) {
    super(beerDto);
  }
}

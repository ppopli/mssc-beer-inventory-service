package com.pulkit.SfgBrewery.events;

import com.pulkit.SfgBrewery.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent{
  public NewInventoryEvent(BeerDto beerDto) {
    super(beerDto);
  }
}

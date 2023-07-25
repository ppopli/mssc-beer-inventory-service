package guru.sfg.beer.inventory.service.services;

import com.pulkit.sfgBrewery.events.AllocateBeerOrderRequest;
import com.pulkit.sfgBrewery.events.AllocateBeerOrderResult;
import guru.sfg.beer.inventory.service.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AllocationListener {
  private final AllocationService allocationService;
  private final JmsTemplate jmsTemplate;

  @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
  public void listen(AllocateBeerOrderRequest request) {
    AllocateBeerOrderResult.AllocateBeerOrderResultBuilder builder = AllocateBeerOrderResult.builder();
    builder.beerOrderDto(request.getBeerOrderDto());
    try {
      Boolean allocationResult = allocationService.allocateOrder(request.getBeerOrderDto());
      builder.pendingInventory(!allocationResult);
      builder.allocationError(false);
    } catch (Exception e) {
      log.error("error while allocating inventory");
      builder.allocationError(true);
    }
    jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE, builder.build());
  }
}

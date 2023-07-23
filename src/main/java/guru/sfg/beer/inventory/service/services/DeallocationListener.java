package guru.sfg.beer.inventory.service.services;

import com.pulkit.sfgBrewery.events.DeallocateBeerOrderRequest;
import guru.sfg.beer.inventory.service.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeallocationListener {
    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(DeallocateBeerOrderRequest request) {
        allocationService.deallocateOrder(request.getBeerOrderDto());
    }
}

package br.com.daniel.orderserviceapi.service.impl;

import br.com.daniel.orderserviceapi.mapper.OrderMapper;
import br.com.daniel.orderserviceapi.repositories.OrderRepository;
import br.com.daniel.orderserviceapi.service.OrderService;
import br.com.userservice.commonslib.model.requests.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public void save(CreateOrderRequest createOrderRequest) {
        final var entity = orderRepository.save(orderMapper.fromRequest(createOrderRequest));
        log.info("Order saved: {}", entity);
    }
}

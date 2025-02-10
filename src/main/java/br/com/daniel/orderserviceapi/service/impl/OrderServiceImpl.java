package br.com.daniel.orderserviceapi.service.impl;

import br.com.daniel.orderserviceapi.mapper.OrderMapper;
import br.com.daniel.orderserviceapi.repositories.OrderRepository;
import br.com.daniel.orderserviceapi.service.OrderService;
import br.com.userservice.commonslib.model.requests.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public void save(CreateOrderRequest createOrderRequest) {
        orderRepository.save(orderMapper.fromRequest(createOrderRequest));
    }
}

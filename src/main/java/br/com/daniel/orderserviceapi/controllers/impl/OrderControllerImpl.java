package br.com.daniel.orderserviceapi.controllers.impl;

import br.com.daniel.orderserviceapi.controllers.OrderController;
import br.com.daniel.orderserviceapi.mapper.OrderMapper;
import br.com.daniel.orderserviceapi.service.OrderService;
import br.com.userservice.commonslib.model.requests.CreateOrderRequest;
import br.com.userservice.commonslib.model.requests.UpdateOrderRequest;
import br.com.userservice.commonslib.model.responses.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Override
    public ResponseEntity<OrderResponse> findById(Long id) {
        return ResponseEntity.ok(orderMapper.fromEntity(orderService.findById(id)));
    }

    @Override
    public ResponseEntity<List<OrderResponse>> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<Void> save(CreateOrderRequest request) {
        orderService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<OrderResponse> update(Long id, UpdateOrderRequest request) {
        return ResponseEntity.ok().body(orderService.update(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

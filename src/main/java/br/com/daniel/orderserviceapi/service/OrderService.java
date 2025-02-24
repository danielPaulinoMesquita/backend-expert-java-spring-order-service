package br.com.daniel.orderserviceapi.service;

import br.com.daniel.orderserviceapi.entities.Order;
import br.com.userservice.commonslib.model.requests.CreateOrderRequest;
import br.com.userservice.commonslib.model.requests.UpdateOrderRequest;
import br.com.userservice.commonslib.model.responses.OrderResponse;

public interface OrderService {

    Order findById(final Long id);

    void save(CreateOrderRequest createOrderRequest);

    OrderResponse update(final Long id, UpdateOrderRequest request);

    void deleteById(final Long id);
}

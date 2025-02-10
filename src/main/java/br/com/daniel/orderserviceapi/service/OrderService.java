package br.com.daniel.orderserviceapi.service;

import br.com.userservice.commonslib.model.requests.CreateOrderRequest;

public interface OrderService {

    void save(CreateOrderRequest createOrderRequest);
}

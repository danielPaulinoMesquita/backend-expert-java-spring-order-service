package br.com.daniel.orderserviceapi.service.impl;

import br.com.daniel.orderserviceapi.clients.UserServiceFeignClient;
import br.com.daniel.orderserviceapi.entities.Order;
import br.com.daniel.orderserviceapi.mapper.OrderMapper;
import br.com.daniel.orderserviceapi.repositories.OrderRepository;
import br.com.daniel.orderserviceapi.service.OrderService;
import br.com.userservice.commonslib.model.enums.OrderStatusEnum;
import br.com.userservice.commonslib.model.exceptions.ResourceNotFoundException;
import br.com.userservice.commonslib.model.requests.CreateOrderRequest;
import br.com.userservice.commonslib.model.requests.UpdateOrderRequest;
import br.com.userservice.commonslib.model.responses.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserServiceFeignClient userServiceFeignClient;

    @Override
    public Order findById(final Long id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Object not found. id: " +id+ ", type: " + Order.class.getSimpleName())
                );
    }

    @Override
    public void save(CreateOrderRequest createOrderRequest) {
        validateString(createOrderRequest.requesterId());
        final var entity = orderRepository.save(orderMapper.fromRequest(createOrderRequest));
        log.info("Order saved: {}", entity);
    }

    @Override
    public OrderResponse update(Long id, UpdateOrderRequest request) {
        var entity = findById(id);
        entity = orderMapper.fromRequest(entity, request);

        if (entity.getStatus().equals(OrderStatusEnum.CLOSED)){
            entity.setClosedAt(LocalDateTime.now());
        }

        return orderMapper.fromEntity(orderRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.delete(findById(id));
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> findAllPaginated(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return orderRepository.findAll(pageRequest);
    }

    void validateString(final String userId) {
        final var response = userServiceFeignClient.findById(userId).getBody();

        log.info("User found: {}", response);
        if(response == null) {
            throw new ResourceNotFoundException("User not found");
        }

    }
}

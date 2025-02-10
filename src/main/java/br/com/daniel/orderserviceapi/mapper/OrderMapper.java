package br.com.daniel.orderserviceapi.mapper;

import br.com.daniel.orderserviceapi.entities.Order;
import br.com.userservice.commonslib.model.requests.CreateOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    Order fromRequest(CreateOrderRequest request);
}

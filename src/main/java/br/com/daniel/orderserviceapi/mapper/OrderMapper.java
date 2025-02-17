package br.com.daniel.orderserviceapi.mapper;

import br.com.daniel.orderserviceapi.entities.Order;
import br.com.userservice.commonslib.model.enums.OrderStatusEnum;
import br.com.userservice.commonslib.model.requests.CreateOrderRequest;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    @Mapping(target = "createdAt", expression = "java(mapCreatedAt())")
    Order fromRequest(CreateOrderRequest request);

    @Named("mapStatus")
    default OrderStatusEnum mapStatus(final String status) {
        return OrderStatusEnum.toEnum(status);
    }

    default LocalDateTime mapCreatedAt(){
        return LocalDateTime.now();
    }
}

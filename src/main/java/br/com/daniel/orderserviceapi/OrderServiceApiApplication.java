package br.com.daniel.orderserviceapi;

import br.com.daniel.orderserviceapi.entities.Order;
import br.com.daniel.orderserviceapi.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServiceApiApplication {

//    @Override
//    public void run(String... args) throws Exception {
//        // run belongs to CommandLineRunner, is one way to execute instructions when application is starting
//       // orderRepository.save(new Order(null, "1","2","Order 1","Description of order", null, null));
//    }

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApiApplication.class, args);
    }

}

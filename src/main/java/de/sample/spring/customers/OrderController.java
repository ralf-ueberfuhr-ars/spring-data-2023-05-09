package de.sample.spring.customers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository repo;

    @GetMapping("/{id}")
    public Order findById(@PathVariable("id") Long id) {
        return this.repo
          .findById(id)
          .orElseThrow(NotFoundException::new);
    }

}

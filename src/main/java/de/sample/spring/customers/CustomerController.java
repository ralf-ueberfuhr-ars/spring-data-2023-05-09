package de.sample.spring.customers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository repo;
    private final OrderRepository orderRepository;

    @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Collection<Customer> findAllCustomers(
      @RequestParam(value = "interest", required = false) String interest
    ) {
        return (null != interest)
          ? repo.findAllByInterestsContainingIgnoreCase(interest)
          : repo.findAll();
    }

    @GetMapping(
      value = "/active",
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Collection<Customer> findActiveCustomers() {
        return this.repo.findAllActive();
    }


    @GetMapping(
      value = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Customer findCustomer(@PathVariable("id") UUID id) {
        return repo
          .findById(id)
          .orElseThrow(NotFoundException::new);
    }

    @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer newCustomer) {
        final var result = this.repo.save(newCustomer);
        var newId = result.getUuid();
        //customers.put(newId, newCustomer);
        URI location = linkTo(
          methodOn(CustomerController.class).findCustomer(newId)
        ).toUri();
        return ResponseEntity.created(location).body(result);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("id") UUID id) {
        if (this.repo.existsById(id)) {
            this.repo.deleteById(id);
        } else {
            throw new NotFoundException();
        }
    }

    @PutMapping("/{id}/avatar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignAvatarToCustomer(@PathVariable("id") UUID id, @Valid @RequestBody Avatar avatar) {
        final var customer = this.repo
          .findById(id)
          .orElseThrow(NotFoundException::new);
        customer.setAvatar(avatar);
        this.repo.save(customer);
    }

    @GetMapping("/{id}/orders")
    public List<Order> findAllOrders(@PathVariable("id") UUID customerId) {
        final var customer = this.repo
          .findById(customerId)
          .orElseThrow(NotFoundException::new);
        return this.orderRepository.findAllByCustomer(customer);
    }

    @PostMapping("/{id}/orders")
    public ResponseEntity<Order> createOrder(
      @PathVariable("id") UUID customerId,
      @Valid @RequestBody Order order
    ) {
        final var customer = this.repo
          .findById(customerId)
          .orElseThrow(NotFoundException::new);
        order.setCustomer(customer);
        var result = this.orderRepository.save(order);
        URI location = linkTo(
          methodOn(OrderController.class).findById(result.getId())
        ).toUri();
        return ResponseEntity.created(location).body(result);
    }

}

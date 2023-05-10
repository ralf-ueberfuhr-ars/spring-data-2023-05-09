package de.sample.spring.customers.boundary;

import de.sample.spring.customers.entities.Customer;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class JpaSampleController {

    private final EntityManager em;

    @GetMapping(
      value = "/samples/jpa/simple",
      produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    public String simple() {
        StringBuilder sb = new StringBuilder();
        sb.append(em);
        final var result = em
          // createNamedQuery möglich, siehe @NamedQuery an Customer-Klasse
          .createQuery("SELECT c FROM Customer c", Customer.class)
          .getResultList();
        for(Customer c : result) {
            sb.append('\n')
              .append(c.getName()) // name
              .append(" (")
              .append(c.getUuid()) // uuid
              .append(")");
        }
        return sb.toString();
    }

    @GetMapping(
      value = "/samples/jpa/query",
      produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    public String query(@RequestParam("name") String name) {
        StringBuilder sb = new StringBuilder();
        sb.append(em);
        final var result = em
          .createQuery("SELECT c FROM Customer c WHERE c.name = :name", Customer.class)
          .setParameter("name", name)
          .getResultList();
        for(Customer c : result) {
            sb.append('\n')
              .append(c.getName()) // name
              .append(" (")
              .append(c.getUuid()) // uuid
              .append(")");
        }
        return sb.toString();
    }

}

package de.sample.spring.customers.boundary;

import de.sample.spring.customers.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
public class JdbcTemplateSampleController {

    private final JdbcTemplate template;
    private final CustomerRowMapper rowMapper;

    @GetMapping(
      value = "/samples/jdbc-template/simple",
      produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    public String simple() {
        StringBuilder sb = new StringBuilder();
        sb.append(template);
        final var result = template
          .query("SELECT uuid, name FROM customers", rowMapper);
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
      value = "/samples/jdbc-template/query",
      produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    public String query(@RequestParam("name") String name) {
        StringBuilder sb = new StringBuilder();
        sb.append(template);
        final var result = template
          .query("SELECT uuid, name FROM customers WHERE name = ?", rowMapper, name);
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

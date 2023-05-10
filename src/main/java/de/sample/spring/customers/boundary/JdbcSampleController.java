package de.sample.spring.customers.boundary;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
@RequiredArgsConstructor
public class JdbcSampleController {

    private final DataSource dataSource;

    @GetMapping(
      value = "/samples/jdbc/simple",
      produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    public String simple() throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append(dataSource);
        try(Connection con = dataSource.getConnection();
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT uuid, name FROM customers")
        ) {
            while (rs.next()) {
                sb.append('\n')
                  .append(rs.getString(2)) // name
                  .append(" (")
                  .append(rs.getString(1)) // uuid
                  .append(")");
            }
        }
        return sb.toString();
    }

    @GetMapping(
      value = "/samples/jdbc/query",
      produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    public String query(@RequestParam("name") String name) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append(dataSource);
        try(Connection con = dataSource.getConnection();
          PreparedStatement stmt = con.prepareStatement("SELECT uuid, name FROM customers WHERE name=?")
        ) {
            stmt.setString(1, name);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    sb.append('\n')
                      .append(rs.getString(2)) // name
                      .append(" (")
                      .append(rs.getString(1)) // uuid
                      .append(")");
                }
            }
        }
        return sb.toString();
    }

}

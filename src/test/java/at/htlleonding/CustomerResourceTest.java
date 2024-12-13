package at.htlleonding;

import at.htlleonding.boundary.CustomerResource;
import at.htlleonding.model.Customer;
import at.htlleonding.repository.CustomerRepository;
import io.quarkus.test.InjectMock;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestHTTPEndpoint(CustomerResource.class)
public class CustomerResourceTest {

    @InjectMock
    CustomerRepository customerRepository;

    private static Customer c;
    @BeforeAll
    static void setup() {
        c = new Customer();
        c.setFirstname("John");
        c.setLastname("Doe");
        c.setEmail("johndoe@gmail.com");
        c.setPhone("+43 677 1234567890");
        c.setPassword("password");
        c.setStreet("Main Street");
        c.setZip("1234");
        c.setCity("City");
        c.setCountry("Country");
        c.setHouseNo("1");
    }

    @Order(1)
    @Test
    public void testCreateCustomer() {
        Mockito.doAnswer(invocationOnMock -> {
            Customer cust = invocationOnMock.getArgument(0);
            cust.setId("1");
            return null;
        }).when(customerRepository).persist(any(Customer.class));
        Mockito.when(customerRepository.isPersistent(any(Customer.class))).thenReturn(true);
        c.setId("1");

        given()
                .contentType(ContentType.JSON)
                .body(c)
                .accept(ContentType.JSON)
                .when().post()
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .body("id", is("1"),
                        "firstname", is("John"),
                        "lastname", is("Doe"),
                        "email", is("johndoe@gmail.com"),
                        "phone", is("+43 677 1234567890"),
                        "password", is("password"),
                        "street", is("Main Street"),
                        "zip", is("1234"),
                        "city", is("City"),
                        "country", is("Country"),
                        "houseNo", is("1"));
    }

    @Order(2)
    @Test
    public void testGetCustomerById() {
        Mockito.when(customerRepository.findById(1L)).thenReturn(c);

        given()
                .accept(ContentType.JSON)
                .when().get("/1")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("id", is("1"),
                        "firstname", is("John"),
                        "lastname", is("Doe"),
                        "email", is("johndoe@gmail.com"),
                        "phone", is("+43 677 1234567890"),
                        "password", is("password"),
                        "street", is("Main Street"),
                        "zip", is("1234"),
                        "city", is("City"),
                        "country", is("Country"),
                        "houseNo", is("1"));
    }

    @Order(3)
    @Test
    public void testUpdateCustomer() {
        c.setFirstname("Jane");
        c.setLastname("Doe");
        Mockito.doAnswer(invocationOnMock -> {
            Customer cust = invocationOnMock.getArgument(0);
            cust.setFirstname("Jane");
            cust.setLastname("Doe");
            return cust;
        }).when(customerRepository).update(any(Customer.class));

        given()
                .contentType(ContentType.JSON)
                .body(c)
                .accept(ContentType.JSON)
                .when().put()
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("id", is("1"),
                        "firstname", is("Jane"),
                        "lastname", is("Doe"),
                        "email", is("johndoe@gmail.com"),
                        "phone", is("+43 677 1234567890"),
                        "password", is("password"),
                        "street", is("Main Street"),
                        "zip", is("1234"),
                        "city", is("City"),
                        "country", is("Country"),
                        "houseNo", is("1"));
    }

    @Order(4)
    @Test
    public void testDeleteCustomer() {
        Mockito.doAnswer(invocationOnMock -> null).when(customerRepository).deleteById(anyLong());

        given()
                .when().delete("/1")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }
}

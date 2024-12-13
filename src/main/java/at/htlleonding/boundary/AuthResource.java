package at.htlleonding.boundary;

import at.htlleonding.dto.CredentialsDTO;
import at.htlleonding.repository.CustomerRepository;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import java.time.Instant;
import java.util.Set;

@Path("api/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    @ConfigProperty(name = "smallrye.jwt.new-token.lifespan")
    long lifespan;

    @GET
    @Path("health")
    @Produces(MediaType.TEXT_PLAIN)
    public String healthCheck() {
        return "AuthResource is alive!";
    }




    @POST
    @Path("login")
    public Response login(CredentialsDTO credentials) {
        System.out.println("login");
        System.out.println(credentials.userId());
        System.out.println(credentials.password());
        long anz = customerRepository.find("id=?1 and password=?2", credentials.userId(), credentials.password()).count();
        System.out.println(anz);
        if (anz != 1) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        long exp = Instant.now().getEpochSecond() + lifespan;
        String token = Jwt
                .claim(Claims.upn.name(), credentials.userId())
                .groups(Set.of("customer", "admin")).sign();

        String entity = Json.createObjectBuilder()
                .add("token", token)
                .add("exp", exp)
                .build().toString();
        return Response.ok().entity(entity).build();
    }
}

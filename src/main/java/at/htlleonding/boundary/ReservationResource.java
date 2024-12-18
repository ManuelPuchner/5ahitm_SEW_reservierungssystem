package at.htlleonding.boundary;

import at.htlleonding.model.Reservation;
import at.htlleonding.repository.ReservationRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("api/reservations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {

    @Inject
    ReservationRepository reservationRepository;

    @GET
    @Path("health")
    @Produces(MediaType.TEXT_PLAIN)
    public String healthCheck() {
        return "ReservationResource is alive!";
    }


    @GET
    @Path("list")
    public List<Reservation> listReservations() {
        return reservationRepository.listAll();
    }

}

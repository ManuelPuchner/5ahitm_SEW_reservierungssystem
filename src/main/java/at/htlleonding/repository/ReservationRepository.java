package at.htlleonding.repository;

import at.htlleonding.model.Reservation;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReservationRepository implements PanacheRepository<Reservation> {

}

package at.htlleonding.repository;

import at.htlleonding.model.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    public void update(Customer customer) {
        getEntityManager().merge(customer);
    }
}

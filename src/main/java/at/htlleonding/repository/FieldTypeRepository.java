package at.htlleonding.repository;

import at.htlleonding.model.FieldType;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FieldTypeRepository implements PanacheRepository<FieldType> {

}

package at.htlleonding.model;

import jakarta.persistence.*;

@Entity
public class FieldType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ft_id", nullable = false)
    private Long id;

    @Column(name = "ft_name", nullable = false)
    private String name;

    @Column(name = "ft_description", nullable = false)
    private String description;


    //region Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //endregion
}

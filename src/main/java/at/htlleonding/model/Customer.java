package at.htlleonding.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "c_id", nullable = false)
    private Long id;

    @Column(name = "c_name", nullable = false)
    private String name;

    @Column(name = "c_email", nullable = false)
    private String email;

    @Column(name = "c_phone", nullable = false)
    private String phone;

    @Column(name = "c_password", nullable = false)
    private String password;

    @Column(name = "c_street", nullable = false)
    private String street;

    @Column(name = "c_zip", nullable = false)
    private String zip;

    @Column(name = "c_city", nullable = false)
    private String city;

    @Column(name = "c_country", nullable = false)
    private String country;

    @Column(name = "c_houseno", nullable = false)
    private String houseNo;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }
    //endregion
}

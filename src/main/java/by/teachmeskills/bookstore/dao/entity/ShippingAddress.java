package by.teachmeskills.bookstore.dao.entity;

import javax.persistence.*;

@Entity
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shippingAddressName;
    private String shippingAddressStreet1;
    private String shippingAddressStreet2;
    private String shippingAddressCity;
    private String shippingAddressState;
    private String shippingAddressCountry;
    private String shippingAddressZipCode;

    @OneToOne
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShippingAddressName() {
        return shippingAddressName;
    }

    public void setShippingAddressName(String shippingAddressName) {
        this.shippingAddressName = shippingAddressName;
    }

    public String getShippingAddressStreet1() {
        return shippingAddressStreet1;
    }

    public void setShippingAddressStreet1(String shippingAddressStreetFirst) {
        this.shippingAddressStreet1 = shippingAddressStreetFirst;
    }

    public String getShippingAddressStreet2() {
        return shippingAddressStreet2;
    }

    public void setShippingAddressStreet2(String shippingAddressStreetSecond) {
        this.shippingAddressStreet2 = shippingAddressStreetSecond;
    }

    public String getShippingAddressCity() {
        return shippingAddressCity;
    }

    public void setShippingAddressCity(String shippingAddressCity) {
        this.shippingAddressCity = shippingAddressCity;
    }

    public String getShippingAddressState() {
        return shippingAddressState;
    }

    public void setShippingAddressState(String shippingAddressState) {
        this.shippingAddressState = shippingAddressState;
    }

    public String getShippingAddressCountry() {
        return shippingAddressCountry;
    }

    public void setShippingAddressCountry(String shippingAddressCountry) {
        this.shippingAddressCountry = shippingAddressCountry;
    }

    public String getShippingAddressZipCode() {
        return shippingAddressZipCode;
    }

    public void setShippingAddressZipCode(String shippingAddressZipCode) {
        this.shippingAddressZipCode = shippingAddressZipCode;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

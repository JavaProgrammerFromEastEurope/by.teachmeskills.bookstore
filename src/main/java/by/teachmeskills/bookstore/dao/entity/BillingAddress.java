package by.teachmeskills.bookstore.dao.entity;

import javax.persistence.*;

@Entity
public class BillingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String billingAddressName;
    private String firstBillingAddressStreet;
    private String secondBillingAddressStreet;
    private String billingAddressCity;
    private String billingAddressState;
    private String billingAddressCountry;
    private String billingAddressZipCode;

    @OneToOne
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillingAddressName() {
        return billingAddressName;
    }

    public void setBillingAddressName(String billingAddressName) {
        this.billingAddressName = billingAddressName;
    }

    public String getFirstBillingAddressStreet() {
        return firstBillingAddressStreet;
    }

    public void setFirstBillingAddressStreet(String firstBillingAddressStreet) {
        this.firstBillingAddressStreet = firstBillingAddressStreet;
    }

    public String getSecondBillingAddressStreet() {
        return secondBillingAddressStreet;
    }

    public void setSecondBillingAddressStreet(String secondBillingAddressStreet) {
        this.secondBillingAddressStreet = secondBillingAddressStreet;
    }

    public String getBillingAddressCity() {
        return billingAddressCity;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }

    public String getBillingAddressState() {
        return billingAddressState;
    }

    public void setBillingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
    }

    public String getBillingAddressCountry() {
        return billingAddressCountry;
    }

    public void setBillingAddressCountry(String billingAddressCountry) {
        this.billingAddressCountry = billingAddressCountry;
    }

    public String getBillingAddressZipCode() {
        return billingAddressZipCode;
    }

    public void setBillingAddressZipCode(String billingAddressZipCode) {
        this.billingAddressZipCode = billingAddressZipCode;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

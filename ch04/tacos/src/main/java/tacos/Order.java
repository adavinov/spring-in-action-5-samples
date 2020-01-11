package tacos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt;

    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    private String deliveryState;

    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();
    @ManyToOne
    private User user;

    public Order() {
    }

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryZip() {
        return deliveryZip;
    }

    public void setDeliveryZip(String deliveryZip) {
        this.deliveryZip = deliveryZip;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public List<Taco> getTacos() {
        return tacos;
    }

    public void setTacos(List<Taco> tacos) {
        this.tacos = tacos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ccCVV == null) ? 0 : ccCVV.hashCode());
        result = prime * result + ((ccExpiration == null) ? 0 : ccExpiration.hashCode());
        result = prime * result + ((ccNumber == null) ? 0 : ccNumber.hashCode());
        result = prime * result + ((deliveryCity == null) ? 0 : deliveryCity.hashCode());
        result = prime * result + ((deliveryName == null) ? 0 : deliveryName.hashCode());
        result = prime * result + ((deliveryState == null) ? 0 : deliveryState.hashCode());
        result = prime * result + ((deliveryStreet == null) ? 0 : deliveryStreet.hashCode());
        result = prime * result + ((deliveryZip == null) ? 0 : deliveryZip.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((placedAt == null) ? 0 : placedAt.hashCode());
        result = prime * result + ((tacos == null) ? 0 : tacos.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (ccCVV == null) {
            if (other.ccCVV != null)
                return false;
        } else if (!ccCVV.equals(other.ccCVV))
            return false;
        if (ccExpiration == null) {
            if (other.ccExpiration != null)
                return false;
        } else if (!ccExpiration.equals(other.ccExpiration))
            return false;
        if (ccNumber == null) {
            if (other.ccNumber != null)
                return false;
        } else if (!ccNumber.equals(other.ccNumber))
            return false;
        if (deliveryCity == null) {
            if (other.deliveryCity != null)
                return false;
        } else if (!deliveryCity.equals(other.deliveryCity))
            return false;
        if (deliveryName == null) {
            if (other.deliveryName != null)
                return false;
        } else if (!deliveryName.equals(other.deliveryName))
            return false;
        if (deliveryState == null) {
            if (other.deliveryState != null)
                return false;
        } else if (!deliveryState.equals(other.deliveryState))
            return false;
        if (deliveryStreet == null) {
            if (other.deliveryStreet != null)
                return false;
        } else if (!deliveryStreet.equals(other.deliveryStreet))
            return false;
        if (deliveryZip == null) {
            if (other.deliveryZip != null)
                return false;
        } else if (!deliveryZip.equals(other.deliveryZip))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (placedAt == null) {
            if (other.placedAt != null)
                return false;
        } else if (!placedAt.equals(other.placedAt))
            return false;
        if (tacos == null) {
            if (other.tacos != null)
                return false;
        } else if (!tacos.equals(other.tacos))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

}

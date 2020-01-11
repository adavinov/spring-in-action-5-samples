//tag::all[]
//tag::allButValidation[]
package tacos;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

public class Order {


    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Street is required")
    // tag::allButValidation[]
    private String street;
    // end::allButValidation[]

    @NotBlank(message = "City is required")
    // tag::allButValidation[]
    private String city;
    // end::allButValidation[]

    @NotBlank(message = "State is required")
    // tag::allButValidation[]
    private String state;
    // end::allButValidation[]

    @NotBlank(message = "Zip code is required")
    // tag::allButValidation[]
    private String zip;
    // end::allButValidation[]

    @CreditCardNumber(message = "Not a valid credit card number")
    // tag::allButValidation[]
    private String ccNumber;
    // end::allButValidation[]

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    // tag::allButValidation[]
    private String ccExpiration;
    // end::allButValidation[]

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    // tag::allButValidation[]
    private String ccCVV;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order [name=");
        builder.append(name);
        builder.append(", street=");
        builder.append(street);
        builder.append(", city=");
        builder.append(city);
        builder.append(", state=");
        builder.append(state);
        builder.append(", zip=");
        builder.append(zip);
        builder.append(", ccNumber=");
        builder.append(ccNumber);
        builder.append(", ccExpiration=");
        builder.append(ccExpiration);
        builder.append(", ccCVV=");
        builder.append(ccCVV);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ccCVV == null) ? 0 : ccCVV.hashCode());
        result = prime * result + ((ccExpiration == null) ? 0 : ccExpiration.hashCode());
        result = prime * result + ((ccNumber == null) ? 0 : ccNumber.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result + ((zip == null) ? 0 : zip.hashCode());
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
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (street == null) {
            if (other.street != null)
                return false;
        } else if (!street.equals(other.street))
            return false;
        if (zip == null) {
            if (other.zip != null)
                return false;
        } else if (!zip.equals(other.zip))
            return false;
        return true;
    }

}
//end::allButValidation[]
//end::all[]

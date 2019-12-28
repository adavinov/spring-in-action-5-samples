//tag::all[]
//tag::allButValidation[]
package tacos;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

public class Order {

//end::allButValidation[]
	@NotBlank(message = "Name is required")
	// tag::allButValidation[]
	private String name;
	// end::allButValidation[]

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

}
//end::allButValidation[]
//end::all[]

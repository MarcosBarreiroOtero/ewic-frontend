package es.ewic.frontend.model;

import java.io.Serializable;

public class Seller implements Serializable {

	private static final long serialVersionUID = 34900857428990048L;

	private int idSeller;
	private String loginName;
	private String firstName;
	private String lastName;
	private String email;

	public Seller() {
	}

	public Seller(int idSeller, String loginName, String firstName, String lastName, String email) {
		this.idSeller = idSeller;
		this.loginName = loginName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getIdSeller() {
		return idSeller;
	}

	public void setIdSeller(int idSeller) {
		this.idSeller = idSeller;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

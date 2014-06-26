package com.bluthco.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@NamedQueries({
	@NamedQuery(name = Employee.QUERY_FIND_BY_COMPANY, query = "from Employee where company = :company")
})
public class Employee implements Serializable {

	public static final String QUERY_FIND_BY_COMPANY = "Employee.findByCompany";
	private static final long serialVersionUID = -7862603055867511693L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id", nullable = false)
	private Long employeeId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	public static Employee from(Company company, String lastName, String firstName) {
		return new Employee().setCompany(company).setLastName(lastName).setFirstName(firstName);
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public Company getCompany() {
		return company;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public Employee setCompany(Company company) {
		this.company = company;
		return this;
	}

	public Employee setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Employee setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Employee other = (Employee) obj;
		if (company == null) {
			if (other.company != null) {
				return false;
			}
		} else if (!company.equals(other.company)) {
			return false;
		}
		if (employeeId == null) {
			if (other.employeeId != null) {
				return false;
			}
		} else if (!employeeId.equals(other.employeeId)) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", company=" + company + ", lastName=" + lastName + ", firstName=" + firstName + "]";
	}

}

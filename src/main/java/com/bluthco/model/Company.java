package com.bluthco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "company")
@NamedQueries({
	@NamedQuery(name = Company.QUERY_FIND_BY_NAME, query = "from Company where name = :name")
})
public class Company implements Serializable {

	public static final String QUERY_FIND_BY_NAME = "Company.findByName";
	private static final long serialVersionUID = -4379972094625421680L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "company_id", nullable = false)
	private Long companyId;

	@Column(name = "name", nullable = false)
	private String name;

	public static Company from(String name) {
		return new Company().setName(name);
	}

	public Long getCompanyId() {
		return companyId;
	}

	public String getName() {
		return name;
	}

	public Company setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Company other = (Company) obj;
		if (companyId == null) {
			if (other.companyId != null) {
				return false;
			}
		} else if (!companyId.equals(other.companyId)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", name=" + name + "]";
	}

}

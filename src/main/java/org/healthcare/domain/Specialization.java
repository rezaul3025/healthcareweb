package org.healthcare.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="specialization")
public class Specialization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3352773471727700104L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="specialization")
	private Set<DoctorSpecialization> doctorSpecializations;

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

	@JsonIgnore
	public Set<DoctorSpecialization> getDoctorSpecializations() {
		return doctorSpecializations;
	}

	public void setDoctorSpecializations(Set<DoctorSpecialization> doctorSpecializations) {
		this.doctorSpecializations = doctorSpecializations;
	}
	
	
}

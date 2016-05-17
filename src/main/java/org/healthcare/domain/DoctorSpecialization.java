package org.healthcare.domain;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name="doctor_specialization")
public class DoctorSpecialization {
	
	public DoctorSpecialization(){
		
	}
	
	public DoctorSpecialization(Doctor doctor, Specialization specialization){
		this.doctor = doctor;
		this.specialization = specialization;
		this.id = new DoctorSpecializationKey(doctor.getId(), specialization.getId());
	}
	
	@EmbeddedId
	private DoctorSpecializationKey id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	@JoinColumn(name = "doctor_id", referencedColumnName = "id", insertable=false ,updatable=false)
	private Doctor doctor;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "specialization_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Specialization specialization;

	public DoctorSpecializationKey getId() {
		return id;
	}

	public void setId(DoctorSpecializationKey id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

}

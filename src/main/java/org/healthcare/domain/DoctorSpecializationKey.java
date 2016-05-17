package org.healthcare.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DoctorSpecializationKey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5090585202717422139L;
	@Column(name="doctor_id")
	private Long doctor;
	@Column(name="specialization_id")
	private Long specliazation;
	
	public DoctorSpecializationKey(){
		
	}
	
	public DoctorSpecializationKey(Long doctor, Long specliazation){
		this.doctor = doctor;
		this.specliazation = specliazation;
	}

	public Long getDoctor() {
		return doctor;
	}

	public void setDoctor(Long doctor) {
		this.doctor = doctor;
	}

	public Long getSpecliazation() {
		return specliazation;
	}

	public void setSpecliazation(Long specliazation) {
		this.specliazation = specliazation;
	}
}

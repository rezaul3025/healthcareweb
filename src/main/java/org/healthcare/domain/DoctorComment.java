/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.healthcare.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author rkarim
 */
@Entity(name = "doctor_comment")
public class DoctorComment implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private DoctorCommentsKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Doctor doctor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Comment comment;
    
    public DoctorComment(){
        
    }
    
    public DoctorComment(Doctor doctor, Comment comment){
        this.doctor = doctor;
        this.comment = comment;
    }

    public DoctorCommentsKey getId() {
        return id;
    }

    public void setId(DoctorCommentsKey id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
    
}

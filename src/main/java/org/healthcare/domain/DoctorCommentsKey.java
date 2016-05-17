/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.healthcare.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author rkarim
 */
@Embeddable
public class DoctorCommentsKey implements Serializable {

    private static final long serialVersionUID = -5090585202717422139L;
    @Column(name = "doctor_id")
    private Long doctor;
    @Column(name = "comment_id")
    private Long comment;

    public DoctorCommentsKey() {

    }

    public Long getDoctor() {
        return doctor;
    }

    public void setDoctor(Long doctor) {
        this.doctor = doctor;
    }

    public Long getComment() {
        return comment;
    }

    public void setComment(Long comment) {
        this.comment = comment;
    }
    
    
}

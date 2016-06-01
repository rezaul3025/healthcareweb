package org.healthcare.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "doctor")
public class Doctor implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7552589038792827839L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address_line1")
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "city")
    private String city;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "website")
    private String website;

    @Column(name = "description", length=Integer.MAX_VALUE)
    private String description;

    @Transient
    private List<String> specializations = new ArrayList<String>();
    
    @Transient
    private User user;

    @OneToMany(mappedBy = "doctor")
    private Set<DoctorSpecialization> doctorSpecializations;
    
    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JsonManagedReference
    private Set<Comment> comments;
    
    @OneToOne(mappedBy="doctor",fetch=FetchType.EAGER, cascade =CascadeType.ALL)
    @JsonManagedReference
    private ImageMeta imageMeta;
    
    @OneToOne(mappedBy="doctor",fetch=FetchType.EAGER, cascade =CascadeType.ALL)
    @JsonManagedReference
    private OpeningTime openingTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<String> getSpecializations() {
    	if(this.doctorSpecializations != null){
    	for(DoctorSpecialization dsp : this.doctorSpecializations){
    		specializations.add(dsp.getSpecialization().getName());
    	}}
        return specializations;
    }

    public void setSpecializations(List<String> specializations) {
    	
        this.specializations = specializations;
    }

    public Set<DoctorSpecialization> getDoctorSpecializations() {
        return doctorSpecializations;
    }

    public void setDoctorSpecializations(Set<DoctorSpecialization> doctorSpecializations) {
    	
        this.doctorSpecializations = doctorSpecializations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public ImageMeta getImageMeta() {
		return imageMeta;
	}

	public void setImageMeta(ImageMeta imageMeta) {
		this.imageMeta = imageMeta;
	}

	public OpeningTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(OpeningTime openingTime) {
		this.openingTime = openingTime;
	}
}

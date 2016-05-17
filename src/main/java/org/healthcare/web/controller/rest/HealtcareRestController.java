package org.healthcare.web.controller.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.healthcare.domain.Comment;
import org.healthcare.domain.Doctor;
import org.healthcare.domain.OpeningTime;
import org.healthcare.domain.Specialization;
import org.healthcare.domain.User;
import org.healthcare.persistence.service.CommentService;
import org.healthcare.persistence.service.DoctorService;
import org.healthcare.persistence.service.SearchService;
import org.healthcare.persistence.service.SpecializationService;
import org.healthcare.persistence.service.UserService;
import org.healthcare.web.form.CommentForm;
import org.healthcare.web.form.DoctorForm;
import org.healthcare.web.form.SearchForm;
import org.healthcare.web.form.UserForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping(value = "/rest/healthcare")
public class HealtcareRestController {

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private SearchService searchService;
    
    @Autowired
    private CommentService commentSevice;

    @RequestMapping(value = "/specializations", method = RequestMethod.GET)
    public List<Specialization> getAllSpecialization() {
        return specializationService.findAll();
    }
    
    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public List<String> getCities() {
        return doctorService.findCities();
    }

    @RequestMapping(value = "/doctor-signup", method = RequestMethod.POST)
    public void doctorSignup(@RequestBody DoctorForm doctorForm) throws IllegalAccessException, InvocationTargetException {
    	Doctor doctor = new Doctor();
    	BeanUtils.copyProperties(doctorForm, doctor);
    	
    	User user = new User();
    	BeanUtils.copyProperties(doctorForm.getUser() ,user);
    	doctor.setUser(user);
    	
    	OpeningTime openingTime = new OpeningTime();
    	BeanUtils.copyProperties(doctorForm.getOpeningTime() ,openingTime);
    	doctor.setOpeningTime(openingTime);
    	openingTime.setDoctor(doctor);
    	
        doctorService.add(doctor);
    }
    
    @RequestMapping(value="/doctor-img-upload/{id}", method=RequestMethod.POST)
	public void doctorImageUploadWithSignup(MultipartHttpServletRequest request, @PathVariable("id") Long id)
	{
    	doctorService.imageUpload(request, id);
	}
    
    @RequestMapping(value = "/user-signup", method = RequestMethod.POST)
    public void userSignup(@RequestBody UserForm userForm) throws IllegalAccessException, InvocationTargetException {
    	User user = new User();
    	BeanUtils.copyProperties(user, userForm);
    	userService.addUser(user);
    }

    @RequestMapping(value = "/search-autocomplete", method = RequestMethod.GET)
    public Set<String> searchAutocomplete(@RequestParam("queryStr") String queryStr) {
        return searchService.searchAutoComplete(queryStr);
    }

    @RequestMapping(value = "/simple-doctor-serch", method = RequestMethod.GET)
    public Set<Doctor> simpleDoctorSearch(@RequestParam("term") String term, @RequestParam("page") Integer page) {
        return searchService.simapleDoctorSearch(term, page);
    }
    
    @RequestMapping(value = "/simple-doctor-serch-count", method = RequestMethod.GET)
    public Integer getSimpleSearchCount(@RequestParam("term") String term){
       return  searchService.getSimpleSearchCount(term);
    }
    
    @RequestMapping(value = "/advance-doctor-serch", method = RequestMethod.GET)
    public Set<Doctor> advanceDoctorSearch(@RequestParam("specializations") String specializations, @RequestParam("cities") String cities, @RequestParam("page") Integer page) {
    	SearchForm searchForm = new SearchForm(Arrays.asList(specializations.split(",")), Arrays.asList(cities.split(",")), page);
    	return searchService.advanceDoctorSearch(searchForm);
    }
    
    @RequestMapping(value = "/doctor/{id}", method = RequestMethod.GET)
    public Doctor getDoctorById(@PathVariable("id") Long id){
        return doctorService.getDoctorById(id);
    }
    
	@RequestMapping(value = "/comment-on-doctor",  method = RequestMethod.POST)
    public void addCommentOnDoctor(@RequestBody CommentForm commentForm) throws IllegalAccessException, InvocationTargetException{
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentForm, comment);
    	commentSevice.add(comment);
    }
}

package org.healthcare.persistence.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.healthcare.domain.Doctor;
import org.healthcare.persistence.repository.SpecializationRepository;
import org.healthcare.web.form.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.healthcare.persistence.repository.DoctorRepository;

@Service
public class SearchServiceHandler implements SearchService {

	@Autowired
	private DoctorRepository doctorRespository;

	@Autowired
	private SpecializationRepository specializationRepository;

	@Override
	public Set<String> searchAutoComplete(String queryStr) {
		Set<String> searchAutocomplete = new HashSet<String>();
		queryStr = "%" + queryStr + "%";
		Set<String> cityAutomcomplete = doctorRespository.simpleSearchGetSity(queryStr);
		searchAutocomplete.addAll(cityAutomcomplete);

		Set<String> specializationAutocomplete = specializationRepository.simpleSearchGetSpecialization(queryStr);
		searchAutocomplete.addAll(specializationAutocomplete);

		return searchAutocomplete;
	}

	@Override
	public Set<Doctor> simapleDoctorSearch(String term, Integer page) {

		return doctorRespository.simapleDoctorSearch(term, 10, page * 10);
	}

	@Override
	public Integer getSimpleSearchCount(String term) {
		return doctorRespository.getSimpleSearchCount(term);
	}

	@Override
	public Set<Doctor> advanceDoctorSearch(SearchForm searchFrom) {
		boolean isCityAval = true;
		List<String> city = searchFrom.getCity();
		 if(city == null || city.isEmpty()){
			 isCityAval = false;
		 }
		 else if(!city.isEmpty() && city.size() == 1){
			 if(city.get(0).toLowerCase().equals("none") || city.get(0).isEmpty()){
				 isCityAval = false;
			 }
		 }
		 
		 return doctorRespository.advanceDoctorSearch(new HashSet<String>(searchFrom.getSpecilizations()), isCityAval, new HashSet<String>(city), 10, searchFrom.getPage()*10);
	}

}

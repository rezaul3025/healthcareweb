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

		List<String> city = searchFrom.getCity();
		
		List<String> specilizations = searchFrom.getSpecilizations();

		boolean isCityAval = normalizeExtraQueryInput(city);
		
		boolean isSpAval = normalizeExtraQueryInput(specilizations);

		return doctorRespository.advanceDoctorSearch(isSpAval, new HashSet<String>(specilizations), isCityAval,
				new HashSet<String>(city), 10, searchFrom.getPage() * 10);
	}

	private boolean normalizeExtraQueryInput(List<String> input) {
		boolean isInputAval = true;

		if (input == null || input.isEmpty()) {
			isInputAval = false;
		} else if (!input.isEmpty() && input.size() == 1) {
			if (input.get(0).toLowerCase().equals("none") || input.get(0).isEmpty() || input.get(0).equals("null")) {
				isInputAval = false;
			}
		}

		return isInputAval;
	}

}

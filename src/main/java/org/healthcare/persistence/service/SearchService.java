package org.healthcare.persistence.service;

import java.util.Set;

import org.healthcare.domain.Doctor;
import org.healthcare.web.form.SearchForm;

public interface SearchService {

    Set<String> searchAutoComplete(String queryStr);

    Set<Doctor> simapleDoctorSearch(String term, Integer page);

    Integer getSimpleSearchCount(String term);
    
    Set<Doctor> advanceDoctorSearch(SearchForm searchFrom);
}

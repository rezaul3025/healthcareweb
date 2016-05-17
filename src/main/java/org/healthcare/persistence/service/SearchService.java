package org.healthcare.persistence.service;

import java.util.Set;

import org.healthcare.domain.Doctor;

public interface SearchService {

    Set<String> searchAutoComplete(String queryStr);

    Set<Doctor> simapleDoctorSearch(String term, Integer page);

    Integer getSimpleSearchCount(String term);
}

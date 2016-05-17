package org.healthcare.persistence.service;

import java.util.List;

import org.healthcare.domain.Doctor;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface DoctorService {

    Doctor add(Doctor doctor);
    
    Doctor getDoctorById(Long id);
    
    public void imageUpload(MultipartHttpServletRequest request, Long id);
    
    List<String> findCities();

}

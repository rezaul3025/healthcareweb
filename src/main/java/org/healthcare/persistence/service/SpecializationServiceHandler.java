package org.healthcare.persistence.service;

import java.util.List;

import org.healthcare.domain.Specialization;
import org.healthcare.persistence.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecializationServiceHandler implements SpecializationService {

	@Autowired
	private SpecializationRepository spRepo;
	
	@Override
	public List<Specialization> findAll() {
		
		return spRepo.findAll();
	}

}

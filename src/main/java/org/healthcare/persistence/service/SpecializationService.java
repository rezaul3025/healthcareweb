package org.healthcare.persistence.service;

import java.util.List;

import org.healthcare.domain.Specialization;

public interface SpecializationService {
	List<Specialization> findAll();
}

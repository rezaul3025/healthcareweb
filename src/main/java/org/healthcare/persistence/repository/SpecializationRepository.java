package org.healthcare.persistence.repository;

import java.util.List;
import java.util.Set;

import org.healthcare.domain.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
	@Query(value="SELECT *FROM specialization where name IN(:names)", nativeQuery=true)
	List<Specialization> getSpecializationByNames(@Param("names") List<String> names);
	
	@Query(value="SELECT name FROM specialization where name LIKE :queryStr",nativeQuery=true)
	Set<String> simpleSearchGetSpecialization(@Param("queryStr") String queryStr);
}

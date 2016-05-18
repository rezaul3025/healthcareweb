package org.healthcare.persistence.repository;

import java.util.List;
import java.util.Set;

import org.healthcare.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query(value = "SELECT city FROM doctor where city LIKE :queryStr", nativeQuery = true)
    Set<String> simpleSearchGetSity(@Param("queryStr") String queryStr);

    @Query(value = "SELECT * FROM doctor d"
            + " INNER JOIN doctor_specialization ds on d.id = ds.doctor_id"
            + " INNER JOIN specialization s on s.id = ds.specialization_id"
            + " where d.city =:term or s.name =:term LIMIT :page, :pageSize", nativeQuery = true)
    Set<Doctor> simapleDoctorSearch(@Param("term") String term, @Param("pageSize") Integer pageSize, @Param("page") Integer page);
    
    @Query(value = "SELECT * FROM doctor d"
            + " INNER JOIN doctor_specialization ds on d.id = ds.doctor_id"
            + " INNER JOIN specialization s on s.id = ds.specialization_id"
            + " where  s.name IN (:sp) AND case when :city != '' and :city != 'none' then d.city IN (:city) else 1 end  LIMIT :page, :pageSize", nativeQuery = true)
    Set<Doctor> advanceDoctorSearch(@Param("sp") List<String> sp, @Param("city") List<String> city , @Param("pageSize") Integer pageSize, @Param("page") Integer page);
    
    @Query(value = "SELECT COUNT(distinct d.id) FROM doctor d"
            + " INNER JOIN doctor_specialization ds on d.id = ds.doctor_id"
            + " INNER JOIN specialization s on s.id = ds.specialization_id"
            + " where d.city =:term or s.name =:term", nativeQuery = true)
    Integer getSimpleSearchCount(@Param("term") String term);
    
    @Query(value="SELECT DISTINCT city from doctor", nativeQuery=true)
    List<String> findCities();
}

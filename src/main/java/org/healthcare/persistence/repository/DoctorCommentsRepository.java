package org.healthcare.persistence.repository;

import org.healthcare.domain.DoctorComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorCommentsRepository extends JpaRepository<DoctorComment, Long> {

}

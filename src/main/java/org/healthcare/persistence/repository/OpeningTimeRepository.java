package org.healthcare.persistence.repository;

import org.healthcare.domain.OpeningTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpeningTimeRepository extends JpaRepository<OpeningTime, Long> {

}

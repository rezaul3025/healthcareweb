package org.healthcare.persistence.repository;

import org.healthcare.domain.Doctor;
import org.healthcare.domain.ImageMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageMeta, Long> {
	ImageMeta findByDoctor(Doctor id);
}

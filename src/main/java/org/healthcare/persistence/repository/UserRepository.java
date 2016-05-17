/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.healthcare.persistence.repository;

import org.healthcare.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rkarim
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
    User findByParentId(Long id);
}

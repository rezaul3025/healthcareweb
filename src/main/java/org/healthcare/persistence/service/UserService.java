/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.healthcare.persistence.service;

import org.healthcare.domain.User;

/**
 *
 * @author rkarim
 */
public interface UserService {
    User addUser(User user);
    User getUserByEmail(String email);
    User getUserByParent(Long id);
}

package org.healthcare.config.security.auth;

import org.healthcare.domain.User;
import org.healthcare.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userService.getUserByEmail(username);
        if(user == null){
        	throw new UsernameNotFoundException("Unable to load user by username = '" + username + "'");
        }
        return new CurrentUser(user);
    }
}

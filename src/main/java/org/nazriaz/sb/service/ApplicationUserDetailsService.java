package org.nazriaz.sb.service;

import org.nazriaz.sb.entity.ApplicationUser;
import org.nazriaz.sb.repository.ApplicationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    ApplicationUserRepo applicationUserRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws NoSuchElementException {
        return applicationUserRepo.findById(username).orElseThrow();
    }
    public String registerNewApplicationUser(String username,String password){
        try {
            loadUserByUsername(username);
            return "Exists";
        }
        catch (NoSuchElementException e){
            applicationUserRepo.save(new ApplicationUser(passwordEncoder.encode(password), username,
                    true, true, true, true));
            return "OK";
        }
    }
}

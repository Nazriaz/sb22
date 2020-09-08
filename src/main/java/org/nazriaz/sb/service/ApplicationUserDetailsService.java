package org.nazriaz.sb.service;

import org.nazriaz.sb.entity.ApplicationUser;
import org.nazriaz.sb.repository.ApplicationUserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private final
    ApplicationUserRepo applicationUserRepo;
    private final
    PasswordEncoder passwordEncoder;

    public ApplicationUserDetailsService(ApplicationUserRepo applicationUserRepo, PasswordEncoder passwordEncoder) {
        this.applicationUserRepo = applicationUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NoSuchElementException {
        return applicationUserRepo.findById(username).orElseThrow();
    }

    public boolean registerNewApplicationUser(String username, String password) {
        if (!applicationUserRepo.existsById(username)) {
            applicationUserRepo.save(new ApplicationUser(passwordEncoder.encode(password), username,
                    true, true, true, true));
            return true;
        } else {
            return false;
        }
    }
}
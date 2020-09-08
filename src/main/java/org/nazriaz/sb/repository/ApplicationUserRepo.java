package org.nazriaz.sb.repository;

import org.nazriaz.sb.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepo extends JpaRepository<ApplicationUser, String> {
}
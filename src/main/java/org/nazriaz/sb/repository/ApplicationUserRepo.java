package org.nazriaz.sb.repository;

import org.nazriaz.sb.entity.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepo extends CrudRepository<ApplicationUser,String> {

}

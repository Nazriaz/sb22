package org.nazriaz.sb.repository;

import org.nazriaz.sb.entity.Curs;
import org.springframework.data.repository.CrudRepository;

public interface CursRepo extends CrudRepository<Curs, Integer> {
    String findByDate(String date);
}

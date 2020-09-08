package org.nazriaz.sb.repository;

import org.nazriaz.sb.entity.Curs;
import org.nazriaz.sb.entity.Valute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CursRepo extends JpaRepository<Curs, Integer> {
    Curs findFirstByDate(String date);
    Curs findFirstByValute(Valute valute);
    boolean existsByDate(String date);
}
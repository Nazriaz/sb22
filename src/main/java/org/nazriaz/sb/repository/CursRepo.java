package org.nazriaz.sb.repository;

import org.nazriaz.sb.entity.Curs;
import org.nazriaz.sb.entity.Valute;
import org.springframework.data.repository.CrudRepository;

public interface CursRepo extends CrudRepository<Curs, Integer> {
    Curs findFirstByDate(String date);
    Curs findFirstByValute(Valute valute);
    Curs findByDateAndValute(String date, Valute valute);
}

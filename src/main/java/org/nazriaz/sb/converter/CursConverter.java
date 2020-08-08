package org.nazriaz.sb.converter;

import org.nazriaz.sb.dto.front.CursDto;
import org.nazriaz.sb.entity.Curs;
import org.springframework.stereotype.Component;

@Component
public class CursConverter {
    public CursDto toCursDto(Curs curs) {
        return new CursDto(
                curs.getValute().getNominal(),
                curs.getValue(),
                curs.getDate(),
                curs.getValute().getId());
    }
}

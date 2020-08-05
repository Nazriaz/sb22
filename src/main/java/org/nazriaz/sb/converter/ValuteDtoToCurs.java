package org.nazriaz.sb.converter;

import org.nazriaz.sb.dto.ValuteDto;
import org.nazriaz.sb.entity.Curs;
import org.nazriaz.sb.entity.ValCursDate;
import org.nazriaz.sb.entity.Valute;
import org.springframework.stereotype.Component;

@Component
public class ValuteDtoToCurs {
    public Curs toCurs(ValuteDto valuteDto, ValCursDate valCursDate, Valute valute) {
        if (valuteDto == null && valCursDate == null && valute == null) {
            return null;
        }
        return new Curs(
                valuteDto.getValue(),
                valCursDate,
                valute);
    }
}

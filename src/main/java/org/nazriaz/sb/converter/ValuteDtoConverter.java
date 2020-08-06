package org.nazriaz.sb.converter;

import org.nazriaz.sb.dto.ValuteDto;
import org.nazriaz.sb.entity.Curs;
import org.nazriaz.sb.entity.Valute;
import org.springframework.stereotype.Component;

@Component
public class ValuteDtoConverter {

    public Curs toCurs(ValuteDto valuteDto, String date, Valute valute) {
        if (valuteDto == null && date == null && valute == null) {
            return null;
        }
        return new Curs(
                valuteDto.getValue(),
                date,
                valute);
    }

    public Valute toValute(ValuteDto valuteDto) {
        if (valuteDto == null) {
            return null;
        }
        return new Valute(valuteDto.getId(),
                valuteDto.getNumCode(),
                valuteDto.getCharCode(),
                valuteDto.getNominal(),
                valuteDto.getName());
    }


}

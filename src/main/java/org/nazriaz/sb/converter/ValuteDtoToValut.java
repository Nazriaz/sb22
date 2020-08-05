package org.nazriaz.sb.converter;

import org.nazriaz.sb.dto.ValuteDto;
import org.nazriaz.sb.entity.Valute;
import org.springframework.stereotype.Component;

@Component
public class ValuteDtoToValut {
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

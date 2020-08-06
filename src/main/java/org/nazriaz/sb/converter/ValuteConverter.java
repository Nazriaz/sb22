package org.nazriaz.sb.converter;

import org.nazriaz.sb.dto.ValuteDto;
import org.nazriaz.sb.dto.front.ValuteFrontDto;
import org.nazriaz.sb.entity.Valute;
import org.springframework.stereotype.Component;

@Component
public class ValuteConverter {

    public ValuteDto toValuteDto(Valute valute) {
        if (valute == null) {
            return null;
        }
        return new ValuteDto(
                valute.getId(),
                valute.getNumCode(),
                valute.getCharCode(),
                valute.getNominal(),
                valute.getName(), null);
    }

    public ValuteFrontDto toValuteFrontDto(Valute valute) {
        if (valute == null) {
            return null;
        }
        return new ValuteFrontDto(
                valute.getId(),
                valute.getName(),
                valute.getCharCode(),
                valute.getNominal());
    }
}

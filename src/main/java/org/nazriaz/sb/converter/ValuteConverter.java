package org.nazriaz.sb.converter;

import org.nazriaz.sb.dto.ValuteDto;
import org.nazriaz.sb.dto.front.ValuteFrontDto;
import org.nazriaz.sb.entity.Valute;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    private ValuteFrontDto toValuteFrontDto(Valute valute) {
        if (valute == null) {
            return null;
        }
        return new ValuteFrontDto(
                valute.getId(),
                valute.getName(),
                valute.getCharCode(),
                valute.getNominal());
    }

    public List<ValuteFrontDto> toValuteFrontDto(List<Valute> valuteList) {
        return valuteList.stream()
                .map(this::toValuteFrontDto)
                .collect(Collectors.toList());
    }
}
package org.nazriaz.sb.util;

import org.nazriaz.sb.dto.ValuteDto;
import org.nazriaz.sb.entity.Valute;
import org.springframework.stereotype.Service;

@Service
public class ValuteDtoConverter {
    public Valute convert(ValuteDto valuteDto){
        return new Valute(valuteDto.getId(),
                valuteDto.getNumCode(),
                valuteDto.getCharCode(),
                valuteDto.getNominal(),
                valuteDto.getName(),
                valuteDto.getValue());
    }
}

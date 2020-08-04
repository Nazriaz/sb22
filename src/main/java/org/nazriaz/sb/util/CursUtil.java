package org.nazriaz.sb.util;

import org.nazriaz.sb.dto.ValCursDto;
import org.nazriaz.sb.dto.ValuteDto;
import org.nazriaz.sb.entity.Curs;
import org.nazriaz.sb.entity.ValCursDate;
import org.nazriaz.sb.entity.Valut;
import org.springframework.stereotype.Component;

@Component
public class CursUtil {
    public Curs convert(ValCursDto valCursDto, ValuteDto valuteDto) {
        return new Curs(
                valuteDto.getValue(),
                new ValCursDate(valCursDto.getDate(), valCursDto.getName()),
                new Valut(valuteDto.getId(),
                        valuteDto.getNumCode(),
                        valuteDto.getCharCode(),
                        valuteDto.getNominal(),
                        valuteDto.getName()));
    }
}

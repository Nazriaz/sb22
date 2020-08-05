package org.nazriaz.sb.converter;

import org.nazriaz.sb.dto.ValCursDto;
import org.nazriaz.sb.entity.ValCursDate;
import org.springframework.stereotype.Component;

@Component
public class ValCursDtoToValCursDate {
    public ValCursDate toValCursDate(ValCursDto valCursDto) {
        if (valCursDto == null) {
            return null;
        }
        return new ValCursDate(valCursDto.getDate(), valCursDto.getName());
    }
}

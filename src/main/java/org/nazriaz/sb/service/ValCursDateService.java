package org.nazriaz.sb.service;

import org.nazriaz.sb.converter.ValCursDtoToValCursDate;
import org.nazriaz.sb.dto.ValCursDto;
import org.nazriaz.sb.entity.ValCursDate;
import org.nazriaz.sb.repository.ValCursDateRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class ValCursDateService {
    @Autowired
    ValCursDateRepo valCursDateRepo;
    @Autowired
    ValCursDtoToValCursDate valCursDtoToValCursDate;

    void save(ValCursDto valCursDto) {
        if (valCursDateRepo.findById(valCursDto.getDate()).isEmpty()) {
            ValCursDate valCursDate = valCursDtoToValCursDate.toValCursDate(valCursDto);
            valCursDateRepo.save(valCursDate);
        }
    }
}
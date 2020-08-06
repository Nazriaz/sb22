package org.nazriaz.sb.service;

import org.nazriaz.sb.converter.ValuteConverter;
import org.nazriaz.sb.dto.ValuteDto;
import org.springframework.beans.factory.annotation.Autowired;

public class ValuteDtoService {
    @Autowired
    ValuteService valuteService;
    @Autowired
    ValuteConverter valuteConverter;

    public ValuteDto findById(String id) {
        return valuteConverter.toValuteDto(valuteService.findById(id));
    }
}

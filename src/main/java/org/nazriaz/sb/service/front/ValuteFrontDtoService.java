package org.nazriaz.sb.service.front;

import org.nazriaz.sb.converter.ValuteConverter;
import org.nazriaz.sb.dto.front.ValuteFrontDto;
import org.nazriaz.sb.service.ValuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValuteFrontDtoService {
    @Autowired
    ValuteService valuteService;
    @Autowired
    ValuteConverter valuteConverter;

    public ValuteFrontDto findById(String id) {

        return valuteConverter.toValuteFrontDto(valuteService.findById(id));
    }

    public Iterable<ValuteFrontDto> findAll() {
        return valuteConverter.toValuteFrontDto(valuteService.findAll());
    }
}

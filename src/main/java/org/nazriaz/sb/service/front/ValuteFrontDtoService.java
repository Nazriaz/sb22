package org.nazriaz.sb.service.front;

import org.nazriaz.sb.converter.ValuteConverter;
import org.nazriaz.sb.dto.front.ValuteFrontDto;
import org.nazriaz.sb.service.ValuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<ValuteFrontDto> valuteFrontDtoList = new ArrayList<>();
        valuteService.findAll().forEach(valute -> valuteFrontDtoList.add(valuteConverter.toValuteFrontDto(valute)));
        return valuteFrontDtoList;
    }
}

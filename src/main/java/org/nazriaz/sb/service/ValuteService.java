package org.nazriaz.sb.service;

import org.nazriaz.sb.converter.ValuteConverter;
import org.nazriaz.sb.dto.front.ValuteFrontDto;
import org.nazriaz.sb.entity.Valute;
import org.nazriaz.sb.repository.ValuteRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValuteService {
    private final
    ValuteRepo valuteRepo;
    private final
    ValuteConverter valuteConverter;

    public ValuteService(ValuteRepo valuteRepo, ValuteConverter valuteConverter) {
        this.valuteRepo = valuteRepo;
        this.valuteConverter = valuteConverter;
    }

    public Iterable<ValuteFrontDto> findAllValuteFrontDto() {
        List<Valute> valuteList = valuteRepo.findAll();
        return valuteConverter.toValuteFrontDto(valuteList);
    }
}
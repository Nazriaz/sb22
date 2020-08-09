package org.nazriaz.sb.service;

import org.nazriaz.sb.converter.ValuteConverter;
import org.nazriaz.sb.dto.ValuteDto;
import org.nazriaz.sb.dto.front.ValuteFrontDto;
import org.nazriaz.sb.entity.Valute;
import org.nazriaz.sb.repository.ValuteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ValuteService {
    @Autowired
    ValuteRepo valuteRepo;
    @Autowired
    ValuteConverter valuteConverter;

    public Valute findValuteById(String id) {
        Optional<Valute> optionalValute = valuteRepo.findById(id);
        if (optionalValute.isEmpty()) {
            return null;
        }
        return optionalValute.get();
    }

    public List<Valute> findAll() {
        return StreamSupport
                .stream(valuteRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public ValuteDto findValuteDtoById(String id) {
        return valuteConverter.toValuteDto(findValuteById(id));
    }

    public ValuteFrontDto findValuteFrontDtoById(String id) {

        return valuteConverter.toValuteFrontDto(findValuteById(id));
    }

    public Iterable<ValuteFrontDto> findAllValuteFrontDto() {
        return valuteConverter.toValuteFrontDto(findAll());
    }
}

package org.nazriaz.sb.service;

import org.nazriaz.sb.dto.ValuteDto;
import org.nazriaz.sb.entity.Valute;
import org.nazriaz.sb.repository.ValuteRepository;
import org.nazriaz.sb.converter.ValuteDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValuteDtoSevice {
    @Autowired
    ValuteDtoConverter valuteDtoConverter;
    @Autowired
    ValuteRepository valuteRepository;
    public void save(ValuteDto valuteDto){
        Valute valute = valuteDtoConverter.convert(valuteDto);
        valuteRepository.save(valute);
    }

}

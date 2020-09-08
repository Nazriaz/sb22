package org.nazriaz.sb.service;

import org.nazriaz.sb.converter.ValuteDtoConverter;
import org.nazriaz.sb.dto.XmlDto;
import org.nazriaz.sb.entity.Curs;
import org.nazriaz.sb.entity.Valute;
import org.nazriaz.sb.repository.CursRepo;
import org.nazriaz.sb.repository.ValuteRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class XmlDtoService {
    private final
    CursRepo cursRepo;
    private final
    ValuteRepo valuteRepo;
    private final
    ValuteDtoConverter valuteDtoConverter;

    public XmlDtoService(CursRepo cursRepo, ValuteRepo valuteRepo, ValuteDtoConverter valuteDtoConverter) {
        this.cursRepo = cursRepo;
        this.valuteRepo = valuteRepo;
        this.valuteDtoConverter = valuteDtoConverter;
    }

    public void save(XmlDto xmlDto) throws Exception {
        if (cursRepo.findFirstByDate(xmlDto.getDate()) == null) {
            List<Curs> cursList = xmlDto.getValuteDtoList().stream().map(valuteDto ->
                    valuteDtoConverter.toCurs(
                            valuteDto,
                            xmlDto.getDate(),
                            valuteDtoConverter.toValute(valuteDto)))
                    .collect(Collectors.toList());

            List<Valute> valuteList = xmlDto.getValuteDtoList()
                    .stream().map(valuteDtoConverter::toValute)
                    .collect(Collectors.toList());

            valuteRepo.saveAll(valuteList);
            cursRepo.saveAll(cursList);
        } else {
            throw new Exception();

        }
    }
}
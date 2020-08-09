package org.nazriaz.sb.service;

import org.nazriaz.sb.converter.ValuteDtoConverter;
import org.nazriaz.sb.dto.ValCursDto;
import org.nazriaz.sb.entity.Curs;
import org.nazriaz.sb.entity.Valute;
import org.nazriaz.sb.repository.CursRepo;
import org.nazriaz.sb.repository.ValuteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaveToDb {
    @Autowired
    CursRepo cursRepo;
    @Autowired
    ValuteRepo valuteRepo;
    @Autowired
    ValuteDtoConverter valuteDtoConverter;

    public void save(ValCursDto valCursDto){
        System.out.println(cursRepo.findByDate(valCursDto.getDate()));
        if (cursRepo.findByDate(valCursDto.getDate()) == null) {
            List<Curs> cursList = valCursDto.getValuteDtoList().stream().map(valuteDto ->
                    valuteDtoConverter.toCurs(
                            valuteDto,
                            valCursDto.getDate(),
                            valuteDtoConverter.toValute(valuteDto)))
                    .collect(Collectors.toList());

            List<Valute> valuteList = valCursDto.getValuteDtoList().stream().map(valuteDto ->
                    valuteDtoConverter.toValute(valuteDto))
                    .collect(Collectors.toList());

            valuteRepo.saveAll(valuteList);
            cursRepo.saveAll(cursList);
        }
    }

    public void del() {
        cursRepo.deleteAll();
        valuteRepo.deleteAll();
    }
}

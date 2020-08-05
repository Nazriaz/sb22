package org.nazriaz.sb.service;

import org.nazriaz.sb.converter.ValuteDtoToCurs;
import org.nazriaz.sb.converter.ValuteDtoToValut;
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
    ValuteDtoToValut valuteDtoToValut;
    @Autowired
    ValuteDtoToCurs valuteDtoToCurs;

    public void save(ValCursDto valCursDto) throws InterruptedException {
        System.out.println(cursRepo.findByDate(valCursDto.getDate()));
        if (cursRepo.findByDate(valCursDto.getDate()) == null) {
            List<Curs> cursList = valCursDto.getValuteDtoList().stream().map(valuteDto ->
                    valuteDtoToCurs.toCurs(
                            valuteDto,
                            valCursDto.getDate(),
                            valuteDtoToValut.toValute(valuteDto)))
                    .collect(Collectors.toList());

            List<Valute> valuteList = valCursDto.getValuteDtoList().stream().map(valuteDto ->
                    valuteDtoToValut.toValute(valuteDto))
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

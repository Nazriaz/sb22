package org.nazriaz.sb.service;

import org.nazriaz.sb.converter.ValCursDtoToValCursDate;
import org.nazriaz.sb.converter.ValuteDtoToCurs;
import org.nazriaz.sb.converter.ValuteDtoToValut;
import org.nazriaz.sb.dto.ValCursDto;
import org.nazriaz.sb.entity.Curs;
import org.nazriaz.sb.entity.ValCursDate;
import org.nazriaz.sb.entity.Valute;
import org.nazriaz.sb.repository.CursRepo;
import org.nazriaz.sb.repository.ValCursDateRepo;
import org.nazriaz.sb.repository.ValuteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaveToDb {
    @Autowired
    ValCursDateRepo valCursDateRepo;
    @Autowired
    CursRepo cursRepo;
    @Autowired
    ValuteRepository valuteRepository;
    @Autowired
    ValCursDtoToValCursDate valCursDtoToValCursDate;
    @Autowired
    ValuteDtoToValut valuteDtoToValut;
    @Autowired
    ValuteDtoToCurs valuteDtoToCurs;

    public void save(ValCursDto valCursDto) throws InterruptedException {
        if (valCursDateRepo.findById(valCursDto.getDate()).isEmpty()) {
            ValCursDate valCursDate = valCursDtoToValCursDate.toValCursDate(valCursDto);

            List<Curs> cursList = valCursDto.getValuteDtoList().stream().map(valuteDto ->
                    valuteDtoToCurs.toCurs(
                            valuteDto,
                            valCursDate,
                            valuteDtoToValut.toValute(valuteDto)))
                    .collect(Collectors.toList());

            List<Valute> valuteList = valCursDto.getValuteDtoList().stream().map(valuteDto ->
                    valuteDtoToValut.toValute(valuteDto)).collect(Collectors.toList());
            valCursDateRepo.save(valCursDate);
            valuteRepository.saveAll(valuteList);
            cursRepo.saveAll(cursList);
        }
    }

    public void del() {
        valCursDateRepo.deleteAll();
        valuteRepository.deleteAll();
    }
}

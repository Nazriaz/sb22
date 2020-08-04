package org.nazriaz.sb.service;

import org.nazriaz.sb.dto.ValCursDto;
import org.nazriaz.sb.entity.Curs;
import org.nazriaz.sb.entity.ValCursDate;
import org.nazriaz.sb.entity.Valut;
import org.nazriaz.sb.repository.CursRepo;
import org.nazriaz.sb.repository.ValCursDateRepo;
import org.nazriaz.sb.repository.ValutRepo;
import org.nazriaz.sb.util.CursUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SAVE {
    @Autowired
    CursRepo cursRepo;
    @Autowired
    ValutRepo valutRepo;
    @Autowired
    CursUtil cursUtil;
    @Autowired
    ValCursDateRepo valCursDateRepo;

    public void save(ValCursDto valCursDto) {
        if (valCursDateRepo.findById(valCursDto.getDate()).isEmpty()) {
            List<Curs> cursList = valCursDto.getValuteDtoList().stream().map(valuteDto -> new Curs(
                    valuteDto.getValue(),
                    new ValCursDate(valCursDto.getDate(), valCursDto.getName()),
                    new Valut(valuteDto.getId(),
                            valuteDto.getNumCode(),
                            valuteDto.getCharCode(),
                            valuteDto.getNominal(),
                            valuteDto.getName()))).collect(Collectors.toList());

            valCursDateRepo.save(new ValCursDate(valCursDto.getDate(), valCursDto.getName()));
            List<Valut> list = valCursDto.getValuteDtoList().stream().map(valuteDto -> new Valut(valuteDto.getId(),
                    valuteDto.getNumCode(),
                    valuteDto.getCharCode(),
                    valuteDto.getNominal(),
                    valuteDto.getName())).collect(Collectors.toList());
            list.forEach(elem -> valutRepo.save(elem));
            cursList.forEach(curs -> cursRepo.save(curs));
        }
    }
    public void del(){
        valCursDateRepo.deleteAll();
    }
}

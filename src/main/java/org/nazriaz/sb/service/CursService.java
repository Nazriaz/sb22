package org.nazriaz.sb.service;

import org.nazriaz.sb.converter.CursConverter;
import org.nazriaz.sb.dto.front.CursDto;
import org.nazriaz.sb.entity.Curs;
import org.nazriaz.sb.repository.CursRepo;
import org.nazriaz.sb.repository.ValuteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursService {
    @Autowired
    CursRepo cursRepo;
    @Autowired
    CursConverter cursConverter;
    @Autowired
    ValuteRepo valuteRepo;
    Curs findCursByValuteId(String id){
        return cursRepo.findFirstByValute(valuteRepo.findById(id).get());
    }
    public CursDto findCursDtoByValuteId(String id){
        return cursConverter.toCursDto(findCursByValuteId(id));
    }
}

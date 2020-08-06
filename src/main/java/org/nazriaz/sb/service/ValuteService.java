package org.nazriaz.sb.service;

import org.nazriaz.sb.entity.Valute;
import org.nazriaz.sb.repository.ValuteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValuteService {
    @Autowired
    ValuteRepo valuteRepo;

    public Valute findById(String id) {
        Optional<Valute> optionalValute = valuteRepo.findById(id);
        if (optionalValute.isEmpty()) {
            return null;
        }
        return optionalValute.get();
    }
    public Iterable <Valute> findAll(){
        return valuteRepo.findAll();
    }
}

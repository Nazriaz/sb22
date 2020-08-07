package org.nazriaz.sb.service;

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

    public Valute findById(String id) {
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
}

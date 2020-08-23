package org.nazriaz.sb.controller;

import org.nazriaz.sb.dto.ValCursDto;
import org.nazriaz.sb.dto.front.CursDto;
import org.nazriaz.sb.dto.front.ValuteFrontDto;
import org.nazriaz.sb.entity.Valute;
import org.nazriaz.sb.repository.ValuteRepo;
import org.nazriaz.sb.service.CursService;
import org.nazriaz.sb.service.SaveToDb;
import org.nazriaz.sb.service.ValuteService;
import org.nazriaz.sb.util.UtilXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/")
public class MainController {
    @Autowired
    SaveToDb saveToDb;
    @Autowired
    ValuteRepo valuteRepo;
    @Autowired
    ValuteService valuteService;
    @Autowired
    CursService cursService;
    @Autowired
    UtilXML utilXML;

    @GetMapping("/valute")
    Iterable<Valute> findAll() {
        return valuteRepo.findAll();
    }

    @GetMapping("/savetoday")
    String saveTodayCursesToDb() throws JAXBException, MalformedURLException {
        LocalDate now = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String stringDate = dtf.format(now);
        if (!cursService.cursForDateExists(stringDate)) {
            ValCursDto valCursDto = utilXML.getData(stringDate);
            valCursDto.setDate(stringDate);//связанно с выходными
            try {
                saveToDb.save(valCursDto);
                return "ok";
            } catch (Exception ex) {
                return "fail";
            }
        } else return "already in base";
    }

    @GetMapping("/valutes")
    Iterable<ValuteFrontDto> getAll() throws JAXBException, MalformedURLException {
        saveTodayCursesToDb();
        return valuteService.findAllValuteFrontDto();
    }

    @GetMapping("/curses/{id}")
    CursDto getCurs(@PathVariable String id) throws JAXBException, MalformedURLException {
        saveTodayCursesToDb();
        return cursService.findCursDtoByValuteId(id);
    }

    @GetMapping("/666")
    String del() {
        saveToDb.del();
        return "Deleted";
    }

}

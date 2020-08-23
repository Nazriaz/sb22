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
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/")
public class MainController {
    @Autowired
    SaveToDb save;
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
                    save.save(valCursDto);
                    return "ok";
                } catch (Exception ex) {
                    return "fail";
                }
            }
        else return "already in base";
    }

    @GetMapping("/front")
    String requestFromFront() {
        LocalDate date = LocalDate.now();


        return null;
    }

    @GetMapping("/save")
    String sss(@RequestParam String date) throws JAXBException, InterruptedException, MalformedURLException {
        JAXBContext context = JAXBContext.newInstance(ValCursDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp" + "?date_req=" + date);
        ValCursDto valCursDto = (ValCursDto) unmarshaller.unmarshal(url);
        if (valCursDto.getDate() == null) {
            return "Can't find Curs for " + date;
        }
        try {
            save.save(valCursDto);
            return "Saved " + date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed to save";
    }

    @GetMapping("/valutes")
    Iterable<ValuteFrontDto> getAll() throws JAXBException, MalformedURLException {
        saveTodayCursesToDb();
        return valuteService.findAllValuteFrontDto();
    }

    @GetMapping("/valutes/{id}")
    CursDto getCurs(@PathVariable String id) throws JAXBException, MalformedURLException {
        saveTodayCursesToDb();
        return cursService.findCursDtoByValuteId(id);
    }

    @GetMapping("/666")
    String del() {
        save.del();
        return "Deleted";
    }

}

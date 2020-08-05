package org.nazriaz.sb.controller;

import org.nazriaz.sb.dto.ValCursDto;
import org.nazriaz.sb.entity.Valute;
import org.nazriaz.sb.repository.ValuteRepo;
import org.nazriaz.sb.service.SaveToDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/")
public class MainController {
    @Autowired
    SaveToDb save;
    @Autowired
    ValuteRepo valuteRepo;

    @GetMapping
    String get() {
        return "HW";
    }

    @GetMapping("/valute")
    Iterable<Valute> findAll() {
        return valuteRepo.findAll();
    }

    @GetMapping("/current")
    String saveCurrentCurssToDb() throws JAXBException, MalformedURLException, InterruptedException {
        JAXBContext context = JAXBContext.newInstance(ValCursDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ValCursDto valCursDto = (ValCursDto) unmarshaller.unmarshal(new URL("http://www.cbr.ru/scripts/XML_daily.asp"));
        save.save(valCursDto);
        return "Current saved";
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
        save.save(valCursDto);
        return "Saved " + date;
    }

    @GetMapping("/666")
    String del() {
        save.del();
        return "Deleted";
    }
}

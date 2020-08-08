package org.nazriaz.sb.controller;

import org.nazriaz.sb.dto.ValCursDto;
import org.nazriaz.sb.dto.front.CursDto;
import org.nazriaz.sb.dto.front.ValuteFrontDto;
import org.nazriaz.sb.entity.Curs;
import org.nazriaz.sb.entity.Valute;
import org.nazriaz.sb.repository.ValuteRepo;
import org.nazriaz.sb.service.CursService;
import org.nazriaz.sb.service.SaveToDb;
import org.nazriaz.sb.service.front.ValuteFrontDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    ValuteFrontDtoService valuteFrontDtoService;
    @Autowired
    CursService cursService;


//    @GetMapping
//    String get() {
//        return "HW";
//    }

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

    @GetMapping("/get")
    ValuteFrontDto getOne() {
        return valuteFrontDtoService.findById("R01235");
    }
    @GetMapping("/valutes")
    Iterable<ValuteFrontDto> getAll(){
        return valuteFrontDtoService.findAll();
    }
    @GetMapping("/valutes/{id}")
    CursDto getCurs(@PathVariable String id){
        return cursService.findCursDtoByValuteId(id);
    }

    @GetMapping("/666")
    String del() {
        save.del();
        return "Deleted";
    }
}

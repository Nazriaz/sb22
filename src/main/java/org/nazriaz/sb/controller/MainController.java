package org.nazriaz.sb.controller;

import org.nazriaz.sb.dto.ValCursDto;
import org.nazriaz.sb.entity.Valute;
import org.nazriaz.sb.repository.ValuteRepository;
import org.nazriaz.sb.service.SaveToDb;
import org.nazriaz.sb.service.ValuteDtoSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    ValuteRepository valuteRepository;
    @Autowired
    ValuteDtoSevice valuteDtoSevice;

    @GetMapping
    String get() {
        return "HW";
    }

    @GetMapping("/valute")
    Iterable<Valute> findAll() {
        return valuteRepository.findAll();
    }

    @GetMapping("/123")
    String sss() throws MalformedURLException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(ValCursDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ValCursDto valCursDto = (ValCursDto) unmarshaller.unmarshal(new URL("http://www.cbr.ru/scripts/XML_daily.asp"));
        valCursDto.getValuteDtoList().forEach(valuteDto -> valuteDtoSevice.save(valuteDto));
        return "Saved";
    }
    @GetMapping("/321")
    String mmm() throws JAXBException, MalformedURLException, InterruptedException {
        JAXBContext context = JAXBContext.newInstance(ValCursDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
//        ValCursDto valCursDto = (ValCursDto) unmarshaller.unmarshal(new URL("http://www.cbr.ru/scripts/XML_daily.asp"));
        ValCursDto valCursDto = (ValCursDto) unmarshaller.unmarshal(new URL("http://www.cbr.ru/scripts/XML_daily.asp"));
        save.save(valCursDto);

        return "Saved2";
    }
    @GetMapping("/666")
    String del(){
        save.del();
        return "Deleted";
    }
}

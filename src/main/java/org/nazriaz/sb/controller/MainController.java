package org.nazriaz.sb.controller;

import org.nazriaz.sb.dto.front.CursDto;
import org.nazriaz.sb.dto.front.ValuteFrontDto;
import org.nazriaz.sb.service.CursService;
import org.nazriaz.sb.service.ValuteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/")
public class MainController {
    private final
    ValuteService valuteService;
    private final
    CursService cursService;

    public MainController(ValuteService valuteService,
                          CursService cursService) {
        this.valuteService = valuteService;
        this.cursService = cursService;
    }

    @GetMapping("/valutes")
    public Iterable<ValuteFrontDto> getAll() throws JAXBException, MalformedURLException {
        cursService.saveTodayCursesToDb();
        return valuteService.findAllValuteFrontDto();
    }

    @GetMapping("/curses/{id}")
    public CursDto getCurs(@PathVariable String id) throws JAXBException, MalformedURLException {
        cursService.saveTodayCursesToDb();
        return cursService.findCursDtoByValuteId(id);
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}
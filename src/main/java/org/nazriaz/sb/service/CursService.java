package org.nazriaz.sb.service;

import org.nazriaz.sb.converter.CursConverter;
import org.nazriaz.sb.dto.XmlDto;
import org.nazriaz.sb.dto.front.CursDto;
import org.nazriaz.sb.entity.Curs;
import org.nazriaz.sb.repository.CursRepo;
import org.nazriaz.sb.repository.ValuteRepo;
import org.nazriaz.sb.util.UtilXML;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CursService {
    private final
    CursRepo cursRepo;
    private final
    CursConverter cursConverter;
    private final
    ValuteRepo valuteRepo;
    private final
    UtilXML utilXML;
    private final
    XmlDtoService xmlDtoService;

    public CursService(CursRepo cursRepo, CursConverter cursConverter, ValuteRepo valuteRepo, UtilXML utilXML, XmlDtoService xmlDtoService) {
        this.cursRepo = cursRepo;
        this.cursConverter = cursConverter;
        this.valuteRepo = valuteRepo;
        this.utilXML = utilXML;
        this.xmlDtoService = xmlDtoService;
    }

    private Curs findCursByValuteId(String id) {
        return cursRepo.findFirstByValute(valuteRepo.findById(id).orElseThrow());
    }

    public CursDto findCursDtoByValuteId(String id) {
        return cursConverter.toCursDto(findCursByValuteId(id));
    }

    public void saveTodayCursesToDb() throws JAXBException, MalformedURLException {
        LocalDate now = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String stringDate = dtf.format(now);
        boolean exists = cursRepo.existsByDate(stringDate);
        if (!exists) {
            XmlDto xmlDto = utilXML.getData(stringDate);
            xmlDto.setDate(stringDate);//связанно с выходными
            try {
                xmlDtoService.save(xmlDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
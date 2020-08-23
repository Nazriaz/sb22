package org.nazriaz.sb.util;

import org.nazriaz.sb.dto.ValCursDto;
import org.nazriaz.sb.dto.ValuteDto;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;
@Component
public class UtilXML {
    public ValCursDto getData() throws JAXBException, MalformedURLException {
        URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp");
        return (ValCursDto) getUnmarshaller().unmarshal(url);
    }
    public ValCursDto getData(String date) throws JAXBException, MalformedURLException {
        URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp" + "?date_req=" + date);
        return (ValCursDto) getUnmarshaller().unmarshal(url);
    }
    Marshaller getMarshaller() throws JAXBException {
        return getJAXBContext().createMarshaller();
    }
    Unmarshaller getUnmarshaller() throws JAXBException {
        return getJAXBContext().createUnmarshaller();
    }
    JAXBContext getJAXBContext() throws JAXBException {
        return JAXBContext.newInstance(ValCursDto.class);
    }
}

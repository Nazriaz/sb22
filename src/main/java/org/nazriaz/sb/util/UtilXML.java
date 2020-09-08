package org.nazriaz.sb.util;

import org.nazriaz.sb.dto.XmlDto;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class UtilXML {
    public XmlDto getData() throws JAXBException, MalformedURLException {
        URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp");
        return (XmlDto) getUnmarshaller().unmarshal(url);
    }

    public XmlDto getData(String date) throws JAXBException, MalformedURLException {
        URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp" + "?date_req=" + date);
        return (XmlDto) getUnmarshaller().unmarshal(url);
    }

    private Marshaller getMarshaller() throws JAXBException {
        return getJAXBContext().createMarshaller();
    }

    private Unmarshaller getUnmarshaller() throws JAXBException {
        return getJAXBContext().createUnmarshaller();
    }

    private JAXBContext getJAXBContext() throws JAXBException {
        return JAXBContext.newInstance(XmlDto.class);
    }
}
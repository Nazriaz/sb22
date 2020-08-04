package org.nazriaz.sb.dto;

import lombok.Data;
import org.nazriaz.sb.util.ValuteDtoAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Data
@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValuteDto {
    @XmlAttribute(name = "ID")
    String id;
    @XmlElement(name = "NumCode")
    int numCode;
    @XmlElement(name = "CharCode")
    String charCode;
    @XmlElement(name = "Nominal")
    int nominal;
    @XmlElement(name= "Name")
    String name;
    @XmlJavaTypeAdapter(ValuteDtoAdapter.class)
    @XmlElement(name = "Value")
    Double value;

}

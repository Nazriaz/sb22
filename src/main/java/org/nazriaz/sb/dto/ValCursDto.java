package org.nazriaz.sb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValCursDto {
    @XmlAttribute(name = "Date")
    String date;
    @XmlAttribute(name = "name")
    String name;
    @XmlElement(name = "Valute")
    List<ValuteDto> valuteDtoList;
}

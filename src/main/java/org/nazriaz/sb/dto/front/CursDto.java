package org.nazriaz.sb.dto.front;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CursDto {
    Integer nominal;
    Double value;
    String date;
    String valuteId;
    String valuteName;
}
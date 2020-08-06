package org.nazriaz.sb.dto.front;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValuteFrontDto {
    String id;
    String name;
    String charCode;
    int nominal;

}

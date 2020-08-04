package org.nazriaz.sb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Valute {
    @Id
    String id;
    int numCode;
    String charCode;
    int nominal;
    String name;
    Double value;

}

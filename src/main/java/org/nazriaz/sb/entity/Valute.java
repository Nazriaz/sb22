package org.nazriaz.sb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Valute {
    @Id
    String id;
    int numCode;
    String charCode;
    int nominal;
    String name;
    @OneToMany(mappedBy = "valute",cascade= CascadeType.ALL)
    List<Curs> cursList;

    public Valute(String id, int numCode, String charCode, int nominal, String name) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
    }
}

package org.nazriaz.sb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Curs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    Double value;
    String date;
    @ManyToOne()
    Valute valute;

    public Curs(Double value, String date, Valute valute) {
        this.value = value;
        this.date = date;
        this.valute = valute;
    }
}
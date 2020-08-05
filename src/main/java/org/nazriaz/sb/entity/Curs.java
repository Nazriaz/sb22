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
    @ManyToOne(cascade = CascadeType.ALL)
    ValCursDate valCursDate;
    @ManyToOne(cascade = CascadeType.ALL)
    Valute valute;

    public Curs(Double value, ValCursDate valCursDate, Valute valute) {
        this.value = value;
        this.valCursDate = valCursDate;
        this.valute = valute;
    }
}

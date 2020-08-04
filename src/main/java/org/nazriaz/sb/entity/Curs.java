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
    @ManyToOne
    ValCursDate valCursDate;
    @ManyToOne
    Valut valut;

    public Curs(Double value, ValCursDate valCursDate, Valut valut) {
        this.value = value;
        this.valCursDate = valCursDate;
        this.valut = valut;
    }
}

package org.nazriaz.sb.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String valuteFrom;
    String valuteTo;
    String amountFrom;
    String amountTo;
    String date;
}
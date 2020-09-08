package org.nazriaz.sb.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ValuteDtoAdapter extends XmlAdapter<String, Double> {
    @Override
    public Double unmarshal(String s) throws Exception {
        return Double.valueOf(s.replace(',', '.'));
    }

    @Override
    public String marshal(Double aDouble) throws Exception {
        return aDouble.toString().replace('.', ',');
    }
}
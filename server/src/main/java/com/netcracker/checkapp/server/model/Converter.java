package com.netcracker.checkapp.server.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Converter {
    public static Check fromNalogRuCheckToCheck(NalogRuCheck nalogRuCheck) {
        Check check = new Check();
        List<Item> items = nalogRuCheck.getItems();

        check.setFiscalDocumentNumber(nalogRuCheck.getFiscalDocumentNumber());
        check.setFiscalDriveNumber(nalogRuCheck.getFiscalDocumentNumber());
        check.setFiscalSign(nalogRuCheck.getFiscalSign());
        check.setNds10(new BigDecimal(nalogRuCheck.getNds10()).divide(new BigDecimal(100)));
        check.setNds18(new BigDecimal(nalogRuCheck.getNds18()).divide(new BigDecimal(100)));
        check.setTotalSum(new BigDecimal(nalogRuCheck.getTotalSum()).divide(new BigDecimal(100)));
        check.setDateTime(LocalDateTime.parse(nalogRuCheck.getDateTime(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        items.forEach(item -> {
            item.setPrice(item.getPrice().divide(new BigDecimal(100)));
            item.setNds10(item.getNds10().divide(new BigDecimal(100)));
            item.setNdsSum(item.getNdsSum().equals(new BigDecimal(0))
                    ? item.getNds10() : item.getNdsSum().divide(new BigDecimal(100)));
        });
        check.setItems(items);
        check.setUser(new User() /*temporary*/);
        return check;
    }
}
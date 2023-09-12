package com.inss.calculo.configuration;

import jakarta.persistence.AttributeConverter;

import java.time.YearMonth;

public class YearMonthIntegerAttributeConverter implements AttributeConverter<YearMonth, Integer> {

@Override
public Integer convertToDatabaseColumn(
        YearMonth attribute) {
        if (attribute != null) {
        return (attribute.getYear() * 100) +
        attribute.getMonth().getValue();
        }
        return null;
        }

@Override
public YearMonth convertToEntityAttribute(
        Integer dbData) {
        if (dbData != null) {
      
        int year = dbData / 100;
        int month = dbData % 100;
        YearMonth anoMes = YearMonth.of(year, month);
        return anoMes;
        }
        return null;
        }
        }
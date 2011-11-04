package org.experimenter.repository.form;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class SimpleForm {

    private Object value;

    public SimpleForm(Object value) {
        this.value = value;
    }

    public Boolean getBoolean() {
        if (value instanceof Boolean)
            return (Boolean) value;
        throw new IllegalArgumentException("Value isn't Boolean");
    }

    public Short getShort() {
        if (value instanceof Short)
            return (Short) value;
        throw new IllegalArgumentException("Value isn't Short");
    }

    public Integer getInteger() {
        if (value instanceof Integer)
            return (Integer) value;
        throw new IllegalArgumentException("Value isn't Integer");
    }

    public Long getLong() {
        if (value instanceof Long)
            return (Long) value;
        throw new IllegalArgumentException("Value isn't Long");
    }

    public BigInteger getBigInteger() {
        if (value instanceof BigInteger)
            return (BigInteger) value;
        throw new IllegalArgumentException("Value isn't BigInteger");
    }

    public BigDecimal getBigDecimal() {
        if (value instanceof BigDecimal)
            return (BigDecimal) value;
        throw new IllegalArgumentException("Value isn't BigDecimal");
    }

    public Date getDate() {
        if (value instanceof Date)
            return (Date) value;
        throw new IllegalArgumentException("Value isn't Date");
    }

    public java.sql.Date getSqlDate() {
        if (value instanceof java.sql.Date)
            return (java.sql.Date) value;
        throw new IllegalArgumentException("Value isn't java.sql.Date");
    }

    public java.sql.Time getSqlTime() {
        if (value instanceof java.sql.Time)
            return (java.sql.Time) value;
        throw new IllegalArgumentException("Value isn't java.sql.Time");
    }

    public java.sql.Timestamp getSqlTimestamp() {
        if (value instanceof java.sql.Timestamp)
            return (java.sql.Timestamp) value;
        throw new IllegalArgumentException("Value isn't java.sql.Timestamp");
    }
}

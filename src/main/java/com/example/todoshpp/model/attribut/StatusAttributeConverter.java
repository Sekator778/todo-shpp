package com.example.todoshpp.model.attribut;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StatusAttributeConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status attribute) {
        if (attribute == null)
            return null;
        switch (attribute) {
            case PLANNED:
                return 1;
            case WORK_IN_PROGRESS:
                return 2;
            case DONE:
                return 3;
            case CANCELLED:
                return 4;
            default:
                throw new IllegalArgumentException(attribute + " not supported.");
        }
    }

    @Override
    public Status convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;
        switch (dbData) {
            case 1:
                return Status.PLANNED;
            case 2:
                return Status.WORK_IN_PROGRESS;
            case 3:
                return Status.DONE;
            case 4:
                return Status.CANCELLED;
            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }
}
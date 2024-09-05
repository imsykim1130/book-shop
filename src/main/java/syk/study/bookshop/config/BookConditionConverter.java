package syk.study.bookshop.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import syk.study.bookshop.entity.BookCondition;

@Converter(autoApply = true)
public class BookConditionConverter implements AttributeConverter<BookCondition, String> {
    @Override
    public String convertToDatabaseColumn(BookCondition bookCondition) {
        if(bookCondition == null) return null;
        return bookCondition.getCondition();
    }

    @Override
    public BookCondition convertToEntityAttribute(String s) {
        if(s.equals("상")) {
            return BookCondition.HIGH;
        } else if(s.equals("중")) {
            return BookCondition.MIDDLE;
        } else {
            return BookCondition.LOW;
        }
    }
}

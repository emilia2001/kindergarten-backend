package kindergarten.management.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.YearMonth;

@Converter
public class YearMonthAttributeConverter implements AttributeConverter<YearMonth, String> {

    @Override
    public String convertToDatabaseColumn(YearMonth yearMonth) {
        if (yearMonth == null) {
            return null;
        }
        return yearMonth.toString();
    }

    @Override
    public YearMonth convertToEntityAttribute(String string) {
        if (string == null) {
            return null;
        }
        return YearMonth.parse(string);
    }
}
package example.model.supports;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class Snippet {

    private static final String EXCEPTION_MESSAGE_BASE = "dateTime : %s & formatter : %s";

    @Getter
    private LocalDate publishedAt;

    public static Snippet of(String dateTime, String formatter) {
        try {
            return new Snippet(convertToLocalDate(dateTime, formatter));
        } catch (RuntimeException exception) {
            throw new IllegalArgumentException(exceptionMessage(dateTime, formatter), exception);
        }
    }

    private static LocalDate convertToLocalDate(@NonNull String dateTime, @NonNull String formatter) {
        return LocalDate.parse(dateTime, DateTimeFormatter.ofPattern(formatter));
    }

    private static String exceptionMessage(String dateTime, String formatter) {
        return String.format(EXCEPTION_MESSAGE_BASE, dateTime, formatter);
    }
}
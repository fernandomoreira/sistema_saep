
package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Datautil {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate formatarData(String dataTexto) {
        return LocalDate.parse(dataTexto, formatter);
    }
}
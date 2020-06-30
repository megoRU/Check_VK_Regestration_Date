import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

  private static final String URL_REGEX = "https?:\\/\\/?[\\dvk\\.]+.+";

  public static void main(String[] args) throws IOException {
    for (; ; ) {
      System.out.println("Вставьте ссылку например: https://vk.com/foaf.php?id=1");
      Scanner scanner = new Scanner(System.in);
      String urlScanner = scanner.nextLine();
      if (urlScanner.matches(URL_REGEX)) {
        Document doc = Jsoup.connect(urlScanner).get();
        String name = doc.select("foaf|name").text();
        System.out.println(name);
        String dateReg = doc.select("ya|created").toString().substring(21, 31).replaceAll("-", ".");
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatterText = new SimpleDateFormat("yyyy.MM.dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate startDate = LocalDate.parse(dateReg, formatter);
        LocalDate endDate = LocalDate.parse(formatterText.format(date), formatter);
        Period period = Period.between(startDate, endDate);
        Declination declination = new Declination();
        System.out.println(
            "Период пользования: " +
                period.getYears() + declination.getYearsAddition(period.getYears())
                + ", " + period.getMonths() + declination.getMonthAddition(period.getMonths())
                + " и " + period.getDays() + " " + declination.getDayAddition(period.getDays()));
      } break;
    }
  }
}

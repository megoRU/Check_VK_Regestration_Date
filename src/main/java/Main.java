import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

  public static void main(String[] args) throws IOException {
    final String URL = "https://vk.com/foaf.php?id=198740519";
    final Response response = Jsoup.connect(URL).execute();
    final Document docs = response.parse();
    final File f = new File("src/main/resources/filename.xml");
    FileUtils.writeStringToFile(f, docs.outerHtml(), "UTF-8");
    File fXmlFile = new File("src/main/resources/filename.xml");
    FileReader fr = new FileReader(fXmlFile);
    BufferedReader reader = new BufferedReader(fr);
    String line;
    List<String> lines = new ArrayList<>();
    while ((line = reader.readLine()) != null) {
      lines.add(line);
    }
    String dateReg = lines.get(23).substring(25, 35).replaceAll("-", ".");
    Date date = new Date(System.currentTimeMillis());
    SimpleDateFormat formatterText = new SimpleDateFormat("yyyy.MM.dd");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    LocalDate startDate = LocalDate.parse(dateReg, formatter);
    LocalDate endDate = LocalDate.parse(formatterText.format(date), formatter);
    Period period = Period.between(startDate, endDate);
    Declination declination = new Declination();
    System.out.println(
                period.getYears() + declination.getYearsAddition(period.getYears())
        + " " + period.getMonths() + declination.getMonthAddition(period.getMonths())
        + " " + period.getDays() + " " + declination.getDayAddition(period.getDays()));

  }
}

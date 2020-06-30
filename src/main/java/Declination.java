public class Declination {

  public String getYearsAddition(int num) {
    int preLastDigit = num % 100 / 10;
    if (preLastDigit == 1) {
      return " лет";
    }

    switch (num % 10) {
      case 1:
        return " год";
      case 2:
      case 3:
      case 4:
        return " года";
      default:
        return " лет";
    }
  }

  public String getMonthAddition(int num) {
    int preLastDigit = num % 100 / 10;
    if (preLastDigit == 1) {
      return " месяц";
    }

    switch (num % 10) {
      case 1:
        return "месяц";
      case 2:
      case 3:
      case 4:
        return " месяца";
      default:
        return " месяцев";
    }
  }

  public String getDayAddition(int num) {

    int preLastDigit = num % 100 / 10;

    if (preLastDigit == 1) {
      return "дней";
    }

    switch (num % 10) {
      case 1:
        return "день";
      case 2:
      case 3:
      case 4:
        return "дня";
      default:
        return "дней";
    }
  }
}

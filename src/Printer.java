import java.text.SimpleDateFormat;
import java.util.List;

public class Printer {
    String head = "+------+-----------------+--------------+--------+----------------------+" +
                  "\n|      |   НАПРАВЛЕНИЕ   |     ДАТА     |  ЦЕНА  |      НОМЕР БИЛЕТА    |" +
                  "\n+------+-----------------+--------------+--------+----------------------+";

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * метод вывода списка доступных билетов по заданным параметрам
     */
    public void print(List<Ticket> tickets) {
        System.out.println(head);
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println("|" + (i+1) + " ".repeat(6 - String.valueOf(i+1).length()) + "|" +
                    tickets.get(i).destination + " ".repeat(17 - tickets.get(i).destination.length()) + "|" +
                    format.format(tickets.get(i).date) + " ".repeat(14 - format.format(tickets.get(i).date).length()) + "|" +
                    tickets.get(i).price + " ".repeat(8 - tickets.get(i).price.toString().length()) +"|" +
                    tickets.get(i).rootNumber + " ".repeat(22 - String.valueOf(tickets.get(i).rootNumber).length()) + "|" +
                    "\n+------+-----------------+--------------+--------+----------------------+");
        }
    }
}

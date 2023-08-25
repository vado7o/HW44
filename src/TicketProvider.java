
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TicketProvider {
    public List<Ticket> database = new ArrayList<>();  // собственно датабаза билетов данного оператора

    /**
     * Метод, который в случае успешного приобритения билета на стороне клиента удаляет этот билет из базы данных оператора билетов
     */
    public void getTicket(long rootNumber) {
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).rootNumber == rootNumber) {
                database.remove(i);
                return;
            }
        }
        System.out.println("Произошла какая-то неизвестная ошибка на стороне сервера...");    // если по номеру билета он не найден в базе, то выдаётся сообщение
    }


    /**
     * Метод поиска билетов в базе данных. Возвращает список найденных по заданным параметрам билетов.
     */
    public List<Ticket> search(String destination, Date date, List<Ticket> database){
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < database.size(); i++){
            if (Objects.equals(database.get(i).destination, destination) && Objects.equals(database.get(i).date, date)) {
                tickets.add(database.get(i));
            }
        }
        return tickets;
    }


    public boolean updateTicketStatus(Ticket ticket){
        return true;
    }

}

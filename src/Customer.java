import java.util.*;

public class Customer {
    public int id;
    public String name;
    public List<Ticket> tickets;
    public CashProvider cashProvider;

    /**
     * Метод для приобритения выбранного пользователем билета. Перед покупкой проводит верификацию банковских данных на стороне банка.
     */
    public boolean buyTicket(long rootNum, List<Ticket> database, TicketProvider ticketProvider) {
        if (cashProvider.authorization(this)) {       // Проводим верификацию банковских данных пользователя
            for (int i = 0; i < database.size(); i++) {         // Если верификация пройдена успешно, то начинаем поиск билета в базе
                if (database.get(i).rootNumber == rootNum) {
                    double price = database.get(i).price;              // вытаскиваем цену нужного нам билета
                    if (this.cashProvider.getSum() >= price) {          // проверяем достаточность средств на счёте для покупки данного билета
                        this.cashProvider.buy(price);         // обновляем сумму денег на счету клиента после покупки билета через метод buy
                        tickets.add(database.get(i));                  // добавляем найденный билет в список приобретённых пользователем билетов
                        ticketProvider.getTicket(rootNum);              // запускаем функцию удаления из базы оператора купленного билета
                        return true;
                    } else {
                        System.out.println("Недостаточно средств на карте! Работайте больше!");
                        return false;
                    }
                }
            }
            return false;
        } else {
            System.out.println("Ошибка в верификации банковских данных! Повторите попытку позже!");
            return false;
        }
    }

    public Customer(int id, String name, List<Ticket> tickets, CashProvider cashProvider) {    // конструктор по умолчанию
        this.id = id;
        this.name = name;
        this.tickets = tickets;
        this.cashProvider = cashProvider;
    }
}


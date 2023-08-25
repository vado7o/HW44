import java.util.Date;

public class Ticket {
    public long rootNumber;
    public Double price;
    public String destination;
    public Date date;
    public boolean isValid;

    public Ticket(long rootNumber, Double price, String destination, Date date) {     // конструктор по умолчанию
        this.rootNumber = rootNumber;
        this.price = price;
        this.destination = destination;
        this.date = date;
    }
}

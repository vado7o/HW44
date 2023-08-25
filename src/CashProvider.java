public class CashProvider {
    public String name;    // название оператора билетов
    private long card;     // номер карты клиента
    private int cvcNum;    // cvc номер карты клиента
    private double sum;    // сумма средств на карте
    public boolean isAuthorized;

    public void buy(double price) {
        this.setSum(this.getSum() - price);
        System.out.println("Транзакция успешно проведена!!!");
    }


    /**
     * Метод верификации пользователя в его банковской системе. Поскольку тут для простоты мы не будем просить вводить реальный
     * номер банковской карты и номер CVC, то сделаем очень примитивную проверку просто для наглядности.
     */
    public boolean authorization(Customer customer){
        if (customer.cashProvider.getCard() == customer.cashProvider.getCard() &&         // выполняем примитивную проверку банковских данных
            customer.cashProvider.getCvcNum() == customer.cashProvider.getCvcNum()) {
            System.out.println("\nВерификация банковских данных пройдена успешно!");
            return true;
        } else {
            System.out.println("\nВерификация банковских данных НЕ пройдена!");
            return false;
        }
    }

    public CashProvider(String name, long card, int cvcNum, int sum, boolean isAuthorized) {    // конструктор по умолчанию
        this.name = name;
        this.card = card;
        this.cvcNum = cvcNum;
        this.sum = sum;
        this.isAuthorized = isAuthorized;
    }

    public int getCvcNum() {
        return cvcNum;
    }

    public double getSum() {
        return sum;
    }

    public long getCard() {
        return card;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

}

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException {

        /**
         * Сначала создаём все данные (массивы данных), для дальнейшей работы с ними (включая формат даты, сканнер считывания текста и т.д.).
         * В настоящем приложении данную функцию выполняла бы база данных. Но для импровизации нам достаточно этого.
         */
        TicketProvider aeroFlot = new TicketProvider();   // создаём новый образец класса TicketProvider. Это в дальнейшем будет наш оператор билетов.
        List<String> destinations = Arrays.asList("Москва", "Токио", "Лондон", "Варшава", "Берлин", "Тбилиси", "Минск");  // массив направлений
        List<String> dates = Arrays.asList("2023-08-28", "2023-08-29", "2023-08-30", "2023-08-31", "2023-09-01", "2023-09-02", "2023-09-03"); // масив дат
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");   // формат даты
        List<String> cashProviders = Arrays.asList("Газпромбанк", "Сельхозбанк", "Сбербанк", "ВТБ-банк");  // массив банковских операторов
        Scanner scanner = new Scanner(System.in);
        Random rnd = new Random();

        /**
         * Рандомно создаём 300 билетов при помощи конструктора класса Ticket (рандомно определяется его номер, цена в диапазоне от 100 до 400 долларов,
         * пункт назначения из готового массива городов, дата вылета из готового массива дат). Помещаем эти билеты в базу данных созданного нами ранее
         * оператора билетов aeroFlot.
         */
        for (int i = 0; i < 301; i++) {
            Ticket ticket = new Ticket(Math.abs(rnd.nextLong()), Math.round(rnd.nextDouble(100, 400) * 100) / 100.0d,
                    destinations.get(rnd.nextInt(0, 7)),
                    format.parse(dates.get(rnd.nextInt(0, 7))));
            aeroFlot.database.add(ticket);
        }

        /**
         * Формируем образец класса банковского оператора для присвоения пользователю. Все значения также присваеваем рандомно.
         */
        CashProvider cashProvider1 = new CashProvider(cashProviders.get(rnd.nextInt(0, 4)), Math.abs(rnd.nextLong()),
                rnd.nextInt(100, 1000), rnd.nextInt(400, 1500), true);



        /**
         * Начинаем диалог с пользователем. Считываем его имя.
         */
        System.out.println("\n\nЗдравствуйте! Вы находитесь в кассах аэрофлота и собираетесь приобрести билет.");
        System.out.println("\nКак Вас зовут? Напишите пожалуйста:");
        String name = scanner.nextLine();


        /**
         * Формируем образец класса пользователя для присвоения пользователю. Присваиваем ему также уже созданный ранее класс
         * банковского оператора.
         */
        Customer customer1 = new Customer(Math.abs(rnd.nextInt()), name, new ArrayList<>(), cashProvider1);

        while (true) {
            /**
             * Продолжаем диалог с пользователем. Просим выбрать направление.
             */
            System.out.println("\nВыберите направление, на которое ищите билет: ");
            System.out.println("Москва, Токио, Лондон, Варшава, Берлин, Тбилиси, Минск");
            String destination = scanner.nextLine();
            while (!destinations.contains(destination)) {
                System.out.println("Неправильно выбрано направление. Выберите направление, на которое ищите билет: ");
                System.out.println("Москва, Токио, Лондон, Варшава, Берлин, Тбилиси, Минск");
                destination = scanner.nextLine();
            }
            /**
             * Просим выбрать дату отправления.
             */
            System.out.println("\nВыберите дату, на которую ищите билет (напишите текстом любую из следующих): ");
            System.out.println("2023-08-28, 2023-08-29, 2023-08-30, 2023-08-31, 2023-09-01, 2023-09-02, 2023-09-03");
            String date = scanner.nextLine();
            while (!dates.contains(date)) {
                System.out.println("Неправильно выбрана дата. Выберите дату, на которую ищите билет: ");
                System.out.println("2023-08-28, 2023-08-29, 2023-08-30, 2023-08-31, 2023-09-01, 2023-09-02, 2023-09-03");
                date = scanner.nextLine();
            }

            /**
             * Ищем нужный билет через метод search класса оператора перелётов (TicketProvider). И печатаем перечень доступных билетов
             * посредством метода print из класса Printer
             */
            List<Ticket> tickets = aeroFlot.search(destination, format.parse(date), aeroFlot.database); // начинаем поиск билета по заданным ранее параметрам
            if (!tickets.isEmpty()) {
                System.out.println("\nВот список билетов, доступных по Вашему запросу: ");
                new Printer().print(tickets);  // печатаем список найденных билетов
            } else System.out.println("К сожалению билетов по нужным Вам параметрам не найдено...");

            /**
             * Предлагаем купить доступный билет по номеру из списка найденных билетов.
             * Сначала показываем пользователю баланс его средств на счету.
             * При получении номера из списка сохраняем в переменную rootNumber выбранного билета.
             */
            System.out.println("\nВаш текущий баланс: $" + customer1.cashProvider.getSum());
            Integer num = -1;
            while (true) {
                System.out.println("\nВыберите номер (1-" + tickets.size() + ") из списка выше для его покупки: ");
                try {
                    num = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Введено не число!!!");
                }
                if (num >= 1 && num <= tickets.size()) break;
                else System.out.println("Неправильно задан номер!");
            }
            long rootNum = tickets.get(num - 1).rootNumber;

            /**
             * Запускаем метод buyTicket для приобретения выбранного билета. Метод предварительно проведёт верификацию банковских данных на стороне банка.
             */
            customer1.buyTicket(rootNum, aeroFlot.database, aeroFlot);

            /**
             * Показываем пользователю баланс его карты после покупки и предлагаем продолжить поиск билетов
             */
            System.out.println("Ваш баланс: $" + customer1.cashProvider.getSum());

            /**
             * Предлагаем показать список приобретённых нами билетов
             */
            while (true) {
                System.out.println("\n\nХотите посмотреть список Ваших билетов? (Y - да, N - нет): ");
                String input = scanner.nextLine().toLowerCase();
                if (input.equals("y")) {
                    new Printer().print(customer1.tickets);
                    break;
                }
                if (input.equals("n")) {
                    break;
                }
            }

            /**
             * Предлагаем продолжить покупку билетов
             */
            while (true) {
                System.out.println("\n\nПродолжим выбор билетов? (Y - да, N - нет): ");
                String input = scanner.nextLine().toLowerCase();
                if (input.equals("y")) {
                    break;
                }
                if (input.equals("n")) {
                    System.exit(0);
                }
            }
        }
    }
}
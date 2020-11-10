import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    Scanner sc = new Scanner(System.in);
    List<Pizza> menu = new ArrayList<>();
    List<Order> orders = new ArrayList<>();

    public void runProgram() {
        menu = getMenu();
        int choice = 0;
        printMainMenu();
        while (choice != 9) {
            choice = sc.nextInt();
            switch (choice) {
                case 1:orderPizza();break;
                case 2:showActiveOrder();break;
                case 3:showOrderHistory();break;
                case 4:showMenu();break;
                case 0:exit();break;
                default: exit();
            }
        }
    }

    public List<Pizza> getMenu() {
        List<Pizza> menu = new ArrayList<>();
        Pizza pizza = null;
        File file = new File("resources/pizza.csv");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while((line = br.readLine()) != null) {
                String[] lineArr = line.split(",");
                pizza = new Pizza(Integer.parseInt(lineArr[0]),lineArr[1],Integer.parseInt(lineArr[2]),lineArr[3]);
                menu.add(pizza);
            }
        } catch (IOException e ) {
            e.printStackTrace();
        }
        return menu;
    }

    public Pizza getPizzaByNumber(int number) throws NoSuchPizzaException {
        Pizza pizza = null;
        try {
            pizza = getPizzaById(number);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchPizzaException ("This pizza doesn't exist...");
        }
        if (pizza == null) {
            throw new NoSuchPizzaException ("This pizza doesn't exist...");
        }
        return pizza;
    }
    public Pizza getPizzaById(int id) {
        for (Pizza pizza : menu) {
            if (pizza.getPizzaNumber() == id) {
                return pizza;
            }
        }
        return null;
    }

    private void printMainMenu() {
        System.out.println("1) Create New Order.");
        System.out.println("2) View Pending Orders.");
        System.out.println("3) View Order History.");
        System.out.println("4) View Menu.");
        System.out.println("0) Exit.");
    }

    //case 1
    private void orderPizza() {
        Order order = new Order();
        //List<Pizza> menu = new ArrayList<>();
        int phone = 0;
        int choice = 0;
        int pizzaNumber = 1;
        Pizza pizza = null;
        System.out.println("Enter customers phone-number: ");
        phone = sc.nextInt();
        order.setPhone(phone);
        while (pizzaNumber != 0) { //exit for 0
            System.out.println("Enter pizza number the customer would like to order: (Press 0 when done)");
            pizzaNumber = sc.nextInt();
            if (pizzaNumber != 0) {
                try {
                    pizza = getPizzaByNumber(pizzaNumber);
                    order.addPizza(pizza);
                } catch (NoSuchPizzaException e) {
                    e.printStackTrace();
                    choice = 0;
                }
            }
        }
        orders.add(order);
        printMainMenu();
    }

    //case 2
    private void showActiveOrder() {
        int choice = 0;
        int pizzaChoice = 0;
        System.out.println("Pick order number to mark as finished (Press 0 to go back to menu)");
        for (Order order: orders){
            System.out.println(order);
        }
        choice = sc.nextInt();
        if (choice != 0){
            try {
                Order order = getOrderByNumber(choice);
                order.setStatus("DONE/EXPEDITED");
                saveOrder(order);
                orders.remove(choice-1);
            }catch (IOException e) {
                e.printStackTrace();
            }catch (NoSuchOrderException e){
                e.getMessage();
            }
        }
        printMainMenu();
    }



    public Order getOrderByNumber(int id) throws NoSuchOrderException {
        Order retVal = null;
        for(Order order:orders) {
            if (order.getOrderID() == id) {
                return order;
            }
        }
        if (retVal == null) {
            throw new NoSuchOrderException("No order with ID: " + id);
        }
        return retVal;
    }


    public void saveOrder(Order order) throws IOException {
        File file = new File("resources/orderhistory.txt");
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(order.toString());
        bw.newLine();
        bw.close();
        fw.close();
    }

    //case 3
    private void showOrderHistory() {
        // Creates an array of character
        char[] array = new char[10000];

        try {
            // Creates a reader using the FileReader
            FileReader fr = new FileReader("resources/orderhistory.txt");

            // Reads characters
            fr.read(array);
            System.out.println(array);

            // Closes the reader
            fr.close();
        }
        catch(Exception e) {
            e.getStackTrace();
        }
    }

    //case 4
    private void showMenu() {
        for (Pizza pizza: menu) {
            System.out.println(pizza.toString());
        }
        System.out.println("\n");
        printMainMenu();
    }

    public void exit() {
        System.out.println("Exiting program...");
    }
}

// Måske lave en writer til at gemme ordrehistorik
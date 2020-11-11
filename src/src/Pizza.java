import java.util.ArrayList;

public class Pizza {
    private int pizzaNumber;
    private String pizzaName;
    private int price;
    private String ingredients;

    public Pizza(int pizzaNumber, String pizzaName, int price, String ingredients) {
        this.pizzaNumber = pizzaNumber;
        this.pizzaName = pizzaName;
        this.price = price;
        this.ingredients = ingredients;
    }

    ArrayList<Pizza> menu = new ArrayList<>();


    public int getPrice() {
        return price;
    }


    public int getPizzaNumber() {
        return pizzaNumber;
    }

    public static void addPizzaToMenu(ArrayList<Pizza> menu, Pizza pizza) {
        menu.add(pizza);
    }

    @Override
    public String toString() {
        return  pizzaNumber + ". " + pizzaName + " " + ingredients + getPrice() + ",-";
    }
}


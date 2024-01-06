package app;

import app.data.DataProvider;
import app.data.Product;
import app.services.DataService;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    private static final String CURRENCY = "\uD83D\uDCB2";

    public static void main(String[] args) {
        double priceSearch = getSearchValue();
        DataProvider provider = new DataProvider();
        List<Product> list = provider.getData();
        getOutputInitialData(list);
        DataService service = new DataService();
        int index = service.search(list, priceSearch);
        getOutputSearchData(list, priceSearch, index);
    }

    private static double getSearchValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("\n\uD83D\uDED2 Enter the product price value in %s to search (x.xx): ",
                CURRENCY);
        return Double.parseDouble(scanner.next());
    }

    private static void getOutputInitialData(List<Product> list) {
        System.out.print("\n\uD83D\uDCC3 Initial data:\n");
        AtomicInteger count = new AtomicInteger(1);
        for (Product product : list)
            System.out.println(count.getAndIncrement() + ") " +
                    product.getName() + ", " + CURRENCY + product.getPrice());
    }

    private static void getOutputSearchData(List<Product> list, double priceSearch, int index) {
        System.out.println("-".repeat(24));
        if (index == -1)
            System.out.print("No data.\n");
        else
            System.out.print("Product: " + list.get(index).getName() + ", " +
                    CURRENCY + priceSearch + "\n");
    }
}

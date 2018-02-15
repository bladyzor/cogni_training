package task1;

import java.util.Arrays;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        final List<Currency> currencies = Arrays.asList(
                new Currency("PLN", 4.15f, 1518733529532L),
                new Currency("GBP", 4.66f, 1411733529532L),
                new Currency("CHF", 3.58f, 1312733529532L),
                new Currency("EUR", 4.15f, 1216733529532L),
                new Currency("USD", 3.30f, 1115733529532L)
        );

        currencies.stream().filter(currency -> currency.getValue() > 4.0f);
        currencies.stream().filter(currency -> currency.getValue() > 4.0f).forEach(currency -> System.out.println(currency.toString()));
    }
}
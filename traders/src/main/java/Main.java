import model.Trader;
import model.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        List<Transaction> transactions = getTransactions();

        System.out.println("Find all transactions in the year 2011 and sort them by value (small to high):");
        transactions.stream().filter(t -> t.year() == 2011).sorted(Comparator.comparing(Transaction::value)).forEach(System.out::println);
        System.out.println();

        System.out.println("What are all the unique cities where the traders work?");
        transactions.stream()
                .map(Transaction::trader)
                .map(Trader::city)
                .distinct()
                .forEach(System.out::println);
        System.out.println();

        System.out.println("Find all traders from Cambridge and sort them by name.");
        transactions.stream()
                .map(Transaction::trader)
                .filter(trader -> trader.city().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::name))
                .forEach(System.out::println);
        System.out.println();

        System.out.println("Return a string of all traders’ names sorted alphabetically.");
        String traderNames = transactions.stream().map(Transaction::trader)
                .map(Trader::name)
                .distinct()
                .sorted(Comparator.naturalOrder())
                .reduce("", (result, name) -> result + name + ", ");
        System.out.println(traderNames);
        System.out.println();

        System.out.println("What are all the unique cities where the traders work?");
        System.out.println();

        boolean isAnyMilanBasedTrader = transactions.stream().map(Transaction::trader).map(Trader::city).anyMatch(Predicate.isEqual("Milan"));
        System.out.println("Are any traders based in Milan?: " + (isAnyMilanBasedTrader ? "yes" : "no"));
        System.out.println();

        System.out.println("Print the values of all transactions from the traders living in Cambridge.:");
        transactions.stream().filter(t -> t.trader().city().equals("Cambridge")).map(Transaction::value).forEach(System.out::println);
        System.out.println();

        int theHighestTransactionValue = transactions.stream().map(Transaction::value).reduce(Integer::max).get();
        System.out.println("What’s the highest value of all the transactions?: " + theHighestTransactionValue );
        System.out.println();

        int theSmallestTransactionValue = transactions.stream().map(Transaction::value).reduce(Integer::min).get();
        System.out.println("Find the transaction with the smallest value: ");
        transactions.stream().filter(t->t.value() == theSmallestTransactionValue).forEach(System.out::println);
        System.out.println();
    }

    private static List<Transaction> getTransactions() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        return Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
                , new Transaction(raoul, 2010, 300)

        );
    }
}

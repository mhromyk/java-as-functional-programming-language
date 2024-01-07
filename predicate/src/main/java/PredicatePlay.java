import model.Apple;
import model.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class PredicatePlay {
    static Predicate<Apple> redApple = a -> Color.RED.equals(a.color());

    public static void prettyPrintApple(List<Apple> apples, AppleFormatter formatter) {
        for (Apple apple : apples) {
            System.out.println(formatter.apply(apple));
        }
    }

    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();
//        appleList.add(new Apple(160, Color.RED));
//        appleList.add(new Apple(130, Color.RED));
        appleList.add(new Apple(200, Color.GREEN));
        appleList.add(new Apple(160, Color.YELLOW));

        appleList.stream().filter(a -> Color.RED.equals(a.color()))
                .findAny()
                .ifPresentOrElse(System.out::println, () -> System.out.println("not found"));

        System.out.println("step 1:");
        prettyPrintApple(appleList.stream().filter(redApple.negate().and(a -> a.weight() > 160)).sorted(Comparator.comparing(Apple::weight)).toList(), new DescribingAppleFormatter());
        System.out.println("step 1 ended");

        prettyPrintApple(appleList, new WeightAppleFormatter());
        System.out.println();
        prettyPrintApple(appleList, (apple) -> "A " + (apple.weight() > 150 ? "heavy" : "light") + " " + apple.color() + " apple");
        System.out.println();
        prettyPrintApple(appleList, new DescribingAppleFormatter());
        System.out.println("                  and now anonymous");
        prettyPrintApple(appleList, new AppleFormatter() {
            public String apply(Apple apple) {
                return "A " + (apple.weight() > 150 ? "dummy" : "under dummy ;)") + " " + apple.color() + " apple";
            }
        });
        System.out.println("                  and now lambda: ");
        prettyPrintApple(appleList, apple -> "A " + (apple.weight() > 150 ? "dummy" : "under dummy ;)") + " " + apple.color() + " apple");

        System.out.println(appleList.stream().map(a->1).reduce(0, Integer::sum));

        System.out.println("=============");
        List<Integer> integers = Arrays.asList(1,2,3,4,5,7);
        System.out.println(integers.stream().filter(e->e % 2 == 0).reduce(0, Integer::sum));
        System.out.println(integers.stream().reduce(Integer::min));
        System.out.println(integers.stream().reduce(Integer::max));
    }

    private static class WeightAppleFormatter implements AppleFormatter {

        @Override
        public String apply(Apple apple) {
            return "A " + (apple.weight() > 150 ? "heavy" : "light") + " " + apple.color() + " apple";
        }
    }

    private static class DescribingAppleFormatter implements AppleFormatter {
        @Override
        public String apply(Apple apple) {
            return "A " + apple.weight() + " gram " + apple.color() + " apple";
        }
    }
}

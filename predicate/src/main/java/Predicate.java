import model.Apple;
import model.Color;

import java.util.ArrayList;
import java.util.List;

public class Predicate {

    public static void prettyPrintApple(List<Apple> apples, AppleFormatter formatter) {
        for (Apple apple : apples) {
            System.out.println(formatter.apply(apple));
        }
    }

    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple(160, Color.RED));
        appleList.add(new Apple(130, Color.RED));
        appleList.add(new Apple(200, Color.GREEN));
        appleList.add(new Apple(160, Color.YELLOW));


        prettyPrintApple(appleList, new WeightAppleFormatter());
        System.out.println();
        prettyPrintApple(appleList, (apple) -> "A " + (apple.weight() > 150 ? "heavy" : "light") + " " + apple.color() + " apple");
        System.out.println();
        prettyPrintApple(appleList, new DescribingAppleFormatter());
        System.out.println("                  and now anonymous");
        prettyPrintApple(appleList, new AppleFormatter(){
            public String apply(Apple apple) {
                return "A " + (apple.weight() > 150 ? "dummy" : "under dummy ;)") + " " + apple.color() + " apple";
            }
        });
        System.out.println("                  and now lambda: ");
        prettyPrintApple(appleList, apple -> "A " + (apple.weight() > 150 ? "dummy" : "under dummy ;)") + " " + apple.color() + " apple");
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

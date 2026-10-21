package Companies;
import java.util.*;
/**
 *
 * Alex owns Dine Out, a prominent franchise restaurant that exclusively serves Chinese, Italian, Japanese, and Mexican cuisines. Design the FoodFactory and other necessary classes to simulate the serving process. The class should implement the following methods:
 *
 * FoodFactory getFactory() to return the instance of the FoodFactory class.
 * void registerCuisine(String cuisineKey, Cuisine cuisine) to register the cuisine so that the food factory would be able to serve it. Here Cuisine is the abstract class provided in the locked stub code.
 * Cuisine serveCuisine(String cuisineKey, String dish) to serve a dish for the given cuisine. If the requested cuisine is not registered with the food factory, then the exception UnservableCuisineRequestException should be thrown with the message "Unservable cuisine {cuisineKey} for dish {dish}". The implementation of UnservableCuisineRequestException class is provided in the locked stub code.
 *
 *
 * The locked stub code in the editor validates the correctness of the FoodFactory class implementation by processing some cuisine requests.
 *
 *
 *
 * Constraints
 *
 * 1 ≤ totalNumberOfOrders ≤ 100
 *
 *
 * Input Format For Custom Testing
 * Input from stdin will be processed as follows and passed to the function.
 *
 *
 *
 * The first line contains the value of totalNumberOfOrders describing the total number of orders placed.
 *
 * The next totalNumberOfOrders lines contain the cuisine key and dish name separated by space for each order.
 *
 * Sample Case 0
 * Sample Input For Custom Testing
 *
 * 3
 * Italian Lasagne
 * Japanese Kamameshi
 * Polish Marjoram
 * Sample Output
 *
 * Serving Lasagne(Italian)
 * Serving Kamameshi(Japanese)
 * Unservable cuisine Polish for dish Marjoram
 * Explanation
 *
 * Consider the following cuisine orders:
 *
 * Italian Lasagne: The dish Lasagne is an Italian dish.  Italian cuisine is served by the restaurant, so the locked stub code prints "Serving Lasagne(Italian)".
 * Japanese Kamameshi: The dish Kamameshi is Japanese cuisine which is served by the restaurant, so the locked stub code prints "Serving Kamameshi(Japanese)".
 * Polish Marjoram: The dish Marjoram is Polish cuisine which is not served by the restaurant, so the UnservableCuisineRequestException exception is thrown, so the locked stub code prints "Unservable cuisine Polish for dish Marjoram".
 *
 */
public class UKGCuisine {

    /**
     * 之前给的
     */
    static abstract class Cuisine {
        public abstract Cuisine serveFood(String dish);
    }

    static class UnservableCuisineRequestException extends Exception {
        public UnservableCuisineRequestException(String message) {
            super(message);
        }
    }
    /**
     * 从这里开始写
     */
    static class Chinese extends Cuisine {
        private String dish;

        @Override
        public Cuisine serveFood(String dish) {
            this.dish = dish;
            return this;
        }

        public String getDish() {
            return this.dish;
        }
    }
    static class Italian extends Cuisine {
        private String dish;

        @Override
        public Cuisine serveFood(String dish) {
            this.dish = dish;
            return this;
        }

        public String getDish() {
            return this.dish;
        }
    }
    static class Japanese extends Cuisine {
        private String dish;

        @Override
        public Cuisine serveFood(String dish) {
            this.dish = dish;
            return this;
        }

        public String getDish() {
            return this.dish;
        }
    }

    static class Mexican extends Cuisine {
        private String dish;

        @Override
        public Cuisine serveFood(String dish) {
            this.dish = dish;
            return this;
        }

        public String getDish() {
            return this.dish;
        }
    }

    /*
     * Create the FoodFactory class and other required classes here.
     */
    static class FoodFactory {
        private static final FoodFactory INSTANCE = new FoodFactory();

        private HashMap<String, Object> cuisineMap = new HashMap<>();

        public static FoodFactory getFactory() {
            return INSTANCE;
        }

        public void registerCuisine(String country, Cuisine cuisine ) {
            if (country == null || country.trim().length() == 0) {
                return;
            }
            cuisineMap.put(country, cuisine);

        }
        public Cuisine serveCuisine(String cuisineName, String dish ) throws UnservableCuisineRequestException {
            if (!cuisineMap.containsKey(cuisineName)) {
                throw new UnservableCuisineRequestException(String.format("Unservable cuisine %s for dish %s", cuisineName, dish));
            }
            Cuisine currCuisine = (Cuisine) cuisineMap.get(cuisineName);

            return currCuisine.serveFood(dish);
        }
    }

    /**
     * 这是测试代码
     */
    public class Solution {
        private static final Scanner INPUT_READER = new Scanner(System.in);
        private static final FoodFactory FOOD_FACTORY = FoodFactory.getFactory();

        static {
            FoodFactory.getFactory().registerCuisine("Chinese", new Chinese());
            FoodFactory.getFactory().registerCuisine("Italian", new Italian());
            FoodFactory.getFactory().registerCuisine("Japanese", new Japanese());
            FoodFactory.getFactory().registerCuisine("Mexican", new Mexican());
        }

        public static void main(String[] args) {
            int totalNumberOfOrders = Integer.parseInt(INPUT_READER.nextLine());
            while (totalNumberOfOrders-- > 0) {
                String[] food = INPUT_READER.nextLine().split(" ");
                String cuisine = food[0];
                String dish = food[1];

                try {
                    if (FOOD_FACTORY.equals(FoodFactory.getFactory())) {
                        Cuisine servedFood = FOOD_FACTORY.serveCuisine(cuisine, dish);

                        switch (cuisine) {
                            case "Chinese":
                                Chinese chineseDish = (Chinese) servedFood;
                                System.out.println("Serving " + chineseDish.getDish() + "(Chinese)");
                                break;
                            case "Italian":
                                Italian italianDish = (Italian) servedFood;
                                System.out.println("Serving " + italianDish.getDish() + "(Italian)");
                                break;
                            case "Japanese":
                                Japanese japaneseDish = (Japanese) servedFood;
                                System.out.println("Serving " + japaneseDish.getDish() + "(Japanese)");
                                break;
                            case "Mexican":
                                Mexican mexicanDish = (Mexican) servedFood;
                                System.out.println("Serving " + mexicanDish.getDish() + "(Mexican)");
                                break;
                            default:
                                break;
                        }
                    }
                } catch (UnservableCuisineRequestException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}




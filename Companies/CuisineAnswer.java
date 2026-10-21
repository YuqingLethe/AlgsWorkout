package Companies;

import java.util.HashMap;
import java.util.Scanner;

public class CuisineAnswer {

    /**
     * 题目给的
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
        // 1. 先定义静态实例 static instance
        private static final FoodFactory INSTANCE = new FoodFactory();

        // 这里用Map线程更安全 private Map<String, Cuisine> cuisineMap = new ConcurrentHashMap<>();
        // 参数类型直接用Cuisine会更好public void registerCuisine(String country, Cuisine cuisine)
        private HashMap<String, Object> cuisineMap = new HashMap<>(); //。用ConcurrentHashMap更好


        // 2. 防御要写在private builder中
        private FoodFactory() {
            // 第一次执行 new FoodFactory() 时，INSTANCE 还在赋值中（此时为 null），所以能顺利通过。
            // 如果后续有人通过反射强行再次调用这个私有构造函数，此时 INSTANCE 已经不为 null 了，就会瞬间报错拦截！
            if (INSTANCE != null) {
                throw new IllegalStateException("Already initialized.");
            }
        }
        public static FoodFactory getFactory() {
            return INSTANCE;
        }

        public void registerCuisine(String country, Cuisine cuisine ) {
            if (country == null || country.trim().isEmpty()) { // 用isEMpty更好
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

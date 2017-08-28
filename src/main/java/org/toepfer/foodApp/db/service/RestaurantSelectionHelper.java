package org.toepfer.foodApp.db.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by toepfer on 8/25/2017.
 */
public class RestaurantSelectionHelper {

    public static final String RESTAURANT_PATH = "src/main/resources/static/restaurants.txt";

    public static List<String> selectRestaurantOptions(int selections){

        List<String> allRestaurants = readAllRestaurants();

        //todo:handle out of bounds problems
        Collections.shuffle(allRestaurants);
        return allRestaurants.subList(0, selections);

    }


    private static List<String> readAllRestaurants() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(RESTAURANT_PATH);
        } catch (FileNotFoundException e) {
            //todo:handle this for real
            e.printStackTrace();
            return null;
        }

        Scanner scanner = new Scanner(fileInputStream);
        List<String> restaurants = new ArrayList<>();

        while(scanner.hasNextLine()){
            restaurants.add(scanner.nextLine());
        }

        scanner.close();

        return restaurants;
    }
}

package org.toepfer.foodApp.db.repository;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by toepfer on 8/25/2017.
 */
@Component
public class RestaurantSelectorRepository {

    public static final String RESTAURANT_PATH = "src/main/resources/static/restaurants.txt";

    public List<String> selectRestaurantOptions(int selections){

        List<String> allRestaurants = readRestaurants();

        return selectRandom(selections, allRestaurants);
    }

    private <E> List<E> selectRandom(int selections, List<E> allRestaurants) {
        if(selections > allRestaurants.size()){
            throw new IllegalStateException("restaurants.txt does not have required number of restaurants");
        }
        Collections.shuffle(allRestaurants);
        return allRestaurants.subList(0, selections);
    }

    private List<String> readRestaurants() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(RESTAURANT_PATH);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("restaurants.txt does not exist or cannot be read");
        }
        return readRestaurantsFromFile(fileInputStream);
    }

    private List<String> readRestaurantsFromFile(FileInputStream fileInputStream) {
        Scanner scanner = new Scanner(fileInputStream);
        List<String> restaurants = new ArrayList<>();

        while(scanner.hasNextLine()){
            restaurants.add(scanner.nextLine());
        }
        scanner.close();

        return restaurants;
    }
}

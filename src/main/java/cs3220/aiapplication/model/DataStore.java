package cs3220.aiapplication.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataStore {
    private List<User> users;
    private Map<Integer, List<Ingredient>> userIngredients;
    private int index = 1;

    public DataStore() {
        users = new ArrayList<>();
    users.add(new User(1, "root", "1234"));
    users.add(new User(2, "guest", "1234"));
    userIngredients = new HashMap<>();

    for(User u : users){
        userIngredients.put(u.getId(), new ArrayList<>());
    }
    }


    public User getUser(int id){
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public User getUserByCredentials(String username, String password) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public List<Ingredient> getIngredient(int userId) {
        return userIngredients.getOrDefault(userId, new ArrayList<>());
    }


    public void setIngredients(int userId, List<Ingredient> ingredients) {
        userIngredients.put(userId, ingredients);
    }

    public void addIngredient(int userId, Ingredient ingredient){
        List<Ingredient> ingredients = userIngredients.computeIfAbsent(userId, k -> new ArrayList<>());
        if (ingredients.isEmpty()) {
            index = 1;
        }

        ingredient.setId(index++);
        ingredients.add(ingredient);
    }

    public void deleteIngredient(int userId, int ingredientId){
        List<Ingredient> ingredients = getIngredient(userId);
        if(ingredients !=null){
            ingredients.removeIf(i -> i.getId() == ingredientId);
        }
    }




}

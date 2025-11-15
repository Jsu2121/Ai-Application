package cs3220.aiapplication.model;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataStore {
    private final List<User> users;
    private final Map<Integer, List<Ingredient>> userIngredients;
    private final Map<Integer, List<String>> exchangeHistory = new HashMap<>();
    private final Map<Integer, List<Recipe>> userRecipes = new HashMap<>();

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private int index = 1;

    public DataStore() {
        users = new ArrayList<>();
        users.add(new User(1, "root@example.com", "1234"));
        users.add(new User(2, "guest@example.com", "1234"));
        userIngredients = new HashMap<>();

        for(User u : users){
            userIngredients.put(u.getId(), new ArrayList<>());
        }

        // start the root with some ingredients
        addIngredient(1, new Ingredient(1, "Milk", "2 Gallons"));
        addIngredient(1, new Ingredient(2, "Sugar", "4 lbs"));
        addIngredient(1, new Ingredient(3, "Butter", "4 Sticks"));
        addIngredient(1, new Ingredient(4, "Vanilla Extract", "10 Tablespoons"));
        addIngredient(1, new Ingredient(5, "Flour", "2 lbs"));
        addIngredient(1, new Ingredient(6, "Salt", "1 lbs"));
        addIngredient(1, new Ingredient(7, "Eggs", "12"));
        addIngredient(1, new Ingredient(8, "Baking Powder", "10 Tablespoons"));
        addIngredient(1, new Ingredient(9, "Strawberries", "20"));
        addIngredient(1, new Ingredient(10, "Vegetable Oil", "48 Fl Oz"));
    }


    public User createUser(String email, String password){
        int newId = users.size() + 1;
        User newUser = new User(newId, email, password);
        users.add(newUser);

        userIngredients.put(newId, new ArrayList<>());
        userRecipes.put(newId, new ArrayList<>());

        return newUser;
    }

    // check if a user exists in system using their email
    public boolean userExists(String email){
        return users.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }


    public User getUser(int id){
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public User getUserByCredentials(String username, String password) {
        return users.stream()
                .filter(u -> u.getEmail().equals(username) && u.getPassword().equals(password))
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

    public void saveExchange(int userId, String exchange){
        List<String> exchanges = exchangeHistory.computeIfAbsent(userId, k -> new ArrayList<>());
        exchanges.add(exchange);
    }

    public List<String> getExchanges(int userId){
        List<String> exchanges = exchangeHistory.get(userId);
        if(exchanges == null){
            return Collections.emptyList();
        }
        return new ArrayList<>(exchanges);
    }

    public int getNextRecipeId(){
        return index++;
    }

    public void addRecipe(int userId, Recipe recipe){
        userRecipes.computeIfAbsent(userId, k -> new ArrayList<>()).add(recipe);

    }

    public List<Recipe> getRecipes(int userId){
        return userRecipes.getOrDefault(userId, new ArrayList<>());

    }

    public void deleteRecipe(int userId, int recipeId){
        userRecipes.getOrDefault(userId, new ArrayList<>()).removeIf(r -> r.getId() == recipeId);

    }

    public void toggleFavorite(int userId, int recipeId){
        Recipe recipe = getRecipeById(userId, recipeId);
        if(recipe !=null){
            recipe.setFavorite(!recipe.isFavorite());
        }
    }

    public List<Recipe> getFavorites(int userId){
        return getRecipes(userId).stream().filter(Recipe::isFavorite).toList();
    }

    public Recipe getRecipeById(int userId, int recipeId) {
        return getRecipes(userId).stream()
                .filter(r -> r.getId() == recipeId)
                .findFirst()
                .orElse(null);
    }


}

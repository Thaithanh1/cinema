package movie.payload.converter;

import movie.entity.Food;
import movie.payload.dto.FoodDTO;
import movie.payload.request.FoodRequest;

public class FoodConverter {
    public FoodDTO entityToFood(Food food) {
        FoodDTO dto = new FoodDTO();
        dto.setPrice(food.getPrice());
        dto.setDescription(food.getDescription());
        dto.setImage(food.getImage());
        dto.setNameOfFood(food.getNameOfFood());
        dto.setIsActive(food.isActive());
        return dto;
    }

    public Food addFood(FoodRequest request) {
        Food food = new Food();
        food.setPrice(request.getPrice());
        food.setDescription(request.getDescription());
        food.setImage(request.getImage());
        food.setNameOfFood(request.getNameOfFood());
        food.setIsActive(request.isActive());
        return food;
    }

    public Food editFood(Food food, FoodRequest request) {
        food.setPrice(request.getPrice());
        food.setDescription(request.getDescription());
        food.setImage(request.getImage());
        food.setNameOfFood(request.getNameOfFood());
        food.setIsActive(request.isActive());
        return food;
    }
}

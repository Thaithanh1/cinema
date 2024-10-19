package movie.payload.dto;

public class FoodSalesDTO {
    private String foodName;
    private int totalQuantity;

    public FoodSalesDTO(String foodName, int totalQuantity) {
        this.foodName = foodName;
        this.totalQuantity = totalQuantity;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}

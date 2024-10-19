package movie.service.interfaceservice;

import movie.payload.dto.FoodDTO;
import movie.payload.dto.FoodSalesDTO;
import movie.payload.request.FoodRequest;
import movie.payload.response.ResponseObject;

import java.util.List;

public interface iFoodService {
    public ResponseObject<FoodDTO> addFood(FoodRequest request);
    public ResponseObject<FoodDTO> editFood(int foodID, FoodRequest request);
    public ResponseObject<FoodDTO> deleteFood(int foodID);
    public List<Object[]> getTopFoodInLast7Days();
}

package movie.service;

import movie.entity.Food;
import movie.payload.converter.FoodConverter;
import movie.payload.dto.FoodDTO;
import movie.payload.dto.FoodSalesDTO;
import movie.payload.request.FoodRequest;
import movie.payload.response.ResponseObject;
import movie.repository.FoodRepository;
import movie.service.interfaceservice.iFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.List;

@Service
public class FoodService implements iFoodService {
    @Autowired
    private FoodRepository foodRepository;
    private FoodConverter _converter;
    private ResponseObject<FoodDTO> _foodResponseObject;

    public FoodService() {
        _converter = new FoodConverter();
        _foodResponseObject = new ResponseObject<FoodDTO>();
    }

    public ResponseObject<FoodDTO> addFood(FoodRequest request) {
        Food fd = _converter.addFood(request);
        foodRepository.save(fd);
        return _foodResponseObject.responseSuccess("Them moi Food thanh cong", _converter.entityToFood(fd));
    }

    public ResponseObject<FoodDTO> editFood(int foodID, FoodRequest request) {
        var checkFood = foodRepository.findById(foodID);
        if (checkFood.isEmpty()) {
            return _foodResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Food khong ton tai", null);
        } else {
            Food fd = _converter.editFood(checkFood.get(), request);
            foodRepository.save(fd);
            return _foodResponseObject.responseSuccess("Cap nhat Food thanh cong", _converter.entityToFood(fd));
        }
    }

    public ResponseObject<FoodDTO> deleteFood(int foodID) {
        var checkFood = foodRepository.findById(foodID);
        if (checkFood.isEmpty()) {
            return _foodResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Food khong ton tai", null);
        } else {
            foodRepository.delete(checkFood.get());
            return _foodResponseObject.responseSuccess("Xoa food thanh cong", null);
        }
    }

    public List<Object[]> getTopFoodInLast7Days() {
        List<Object[]> topFoods = foodRepository.findTopFoodByQuantityLastWeek();
        return topFoods;
    }
}

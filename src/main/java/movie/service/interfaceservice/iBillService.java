package movie.service.interfaceservice;

import movie.entity.Bill;
import movie.entity.User;

import java.util.Date;
import java.util.Optional;

public interface iBillService {
    public void addBill(int customerID, String name, double totalMoney, String tradingCode);
    public void sendMail(Optional<User> user, Bill bill);
    public Bill findByTradingCode(String tradingCode);
    public void updateBillActive(String tradingCode);
    public void addBillFood(int billId, int foodId);
}

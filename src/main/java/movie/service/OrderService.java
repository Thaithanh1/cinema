package movie.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import movie.entity.Bill;
import movie.entity.BillFood;
import movie.entity.BillTicket;
import movie.payload.request.OrderRequest;
import movie.repository.BillFoodRepository;
import movie.repository.BillRepository;
import movie.repository.BillTicketRepository;
import movie.repository.FoodRepository;
import movie.service.impl.BillServiceImp;
import movie.service.interfaceservice.iOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class OrderService implements BillServiceImp, iOrderService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillFoodRepository billFoodRepository;
    @Autowired
    private BillTicketRepository billTicketRepository;

    public List<Object[]> calculateSalesStatisticsByCinemaAndTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return billRepository.calculateSalesStatisticsByCinemaAndTimeRange(startTime, endTime);
    }

    @Override
    public void addBill(Bill bill) {
        double allPriceFood = 0;
        double allPriceTicket = 0;
        billRepository.save(bill);
        for (BillTicket billTicket : bill.getBillTickets()) {
            billTicket.setBill(bill);
            billTicketRepository.save(billTicket);
            allPriceTicket += billTicket.getTicket().getPriceTicket() * billTicket.getQuantity();
        }

        for (BillFood billFood : bill.getBillFoods()) {
            billFood.setBill(bill);
            billFoodRepository.save(billFood);
            allPriceFood += billFood.getFood().getPrice() * billFood.getQuantity();
        }

        double totalMoney = ((allPriceTicket + allPriceFood) * (100 - bill.getPromotion().getPercent())) / 100;
        bill.setTotalMoney(totalMoney);
        bill.setCreateTime(new Date());
        billRepository.save(bill);
    }
}

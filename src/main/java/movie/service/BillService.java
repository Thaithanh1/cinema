package movie.service;

import movie.entity.Bill;
import movie.entity.BillFood;
import movie.entity.BillTicket;
import movie.entity.User;
import movie.repository.BillFoodRepository;
import movie.repository.BillRepository;
import movie.repository.BillTicketRepository;
import movie.service.interfaceservice.iBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class BillService implements iBillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillFoodRepository billFoodRepository;
    @Autowired
    private BillTicketRepository billTicketRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    public void addBill(int customerID, String name, double totalMoney, String tradingCode) {
        Bill bl = new Bill();
        bl.setBillStatusID(1);
        bl.setCreateTime(new Date());
        bl.setUpdateTime(new Date());
        bl.setCustomerID(customerID);
        bl.setIsActive(false);
        bl.setName(name);
        bl.setPromotionID(1);
        bl.setTotalMoney(totalMoney);
        bl.setTradingCode(tradingCode);
        billRepository.save(bl);
    }

    public void sendMail(Optional<User> user, Bill bill) {
        String email = user.get().getEmail();
        String userName = user.get().getUserName();
        // Gửi email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("ĐẶT VÉ THÀNH CÔNG");
        message.setText("Xin chào " + userName + ",\n"
                + "Chúc mừng bạn đã đặt vé thành công :\n"
                + "Đây là mã code vé xem phim của bạn: " + bill.getTradingCode());
        javaMailSender.send(message);
    }

    public Bill findByTradingCode(String tradingCode) {
        return billRepository.findByTradingCode(tradingCode);
    }

    public void updateBillActive(String tradingCode) {
        Bill bl = billRepository.findByTradingCode(tradingCode);
        bl.setIsActive(true);
    }

    public void addBillFood(int billId, int foodId) {
        BillFood bf = new BillFood();
        bf.setQuantity(1);
        bf.setFoodID(foodId);
        bf.setBillID(billId);
        billFoodRepository.save(bf);
    }

    public void addBillTicket(int billId, int ticketId) {
        BillTicket bt = new BillTicket();
        bt.setTicketID(ticketId);
        bt.setBillID(billId);
        bt.setQuantity(1);
        billTicketRepository.save(bt);
    }
}

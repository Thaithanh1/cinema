package movie.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import movie.entity.Bill;
import movie.entity.User;
import movie.model.UserCustomDetail;
import movie.payload.request.BillRequest;
import movie.repository.BillRepository;
import movie.service.BillService;
import movie.service.TicketService;
import movie.service.UserService;
import movie.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import movie.config.Config;

import static movie.config.Config.hmacSHA256;
import static movie.config.Config.hmacSHA512;

@RestController
@SessionAttributes({"foodId", "ticketId"})
public class PaymentController {
    @Autowired
    JwtService jwtService;
    @Autowired
    BillService billService;
    @Autowired
    TicketService ticketService;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserService userService;;

    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserCustomDetail) {
            UserCustomDetail userDetails = (UserCustomDetail) authentication.getPrincipal();
            return userDetails.getUserId(); // Giả định bạn có phương thức getUserId()
        }
        return null;
    }

    @GetMapping("/pay")
    public String getPay(@RequestHeader("Authorization") String authorizationHeader, HttpSession session,
                         @RequestBody BillRequest billRequest, HttpServletRequest request) throws UnsupportedEncodingException{
        // Giả sử bạn đã lấy token JWT từ session hoặc từ một nơi nào đó
        String jwtToken = (String) session.getAttribute("JWT_TOKEN");
        String token = authorizationHeader.substring(7);
        // Lấy thông tin người dùng từ SecurityContextHolder
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Lưu foodId vào session
        session.setAttribute("foodId", billRequest.getFoodID());

        // Lưu ticketId vào session
        session.setAttribute("ticketId", billRequest.getTicketID());

        UserCustomDetail currentUser = null;
        if (principal instanceof UserCustomDetail) {
            currentUser = (UserCustomDetail) principal; // Nếu là UserDetails
        } 
        // Lưu thông tin người dùng vào session
        session.setAttribute("user", currentUser);

        String userId = getCurrentUserId();
        double totalMoney = billRepository.findTicketPrice(billRequest.getMovieID(), billRequest.getCinemaID(),
                billRequest.getRoomID(), billRequest.getTicketID()) + billRepository.findFoodPrice(billRequest.getFoodID());

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = (long) totalMoney*100;
        String bankCode = "NCB";

        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";

        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", "http://localhost:8080/vnpay_return?token=" + token);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.UTF_8.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

        String billName = billRepository.findMovieNameById(billRequest.getMovieID());
        billService.addBill(Integer.parseInt(userId), billName, amount, vnp_TxnRef);

        return paymentUrl;
    }

    // Sau khi hoàn tất thanh toán, VNPAY sẽ chuyển hướng trình duyệt về URL này
    @GetMapping("/vnpay_return")
    public String handleVNPayReturn(@SessionAttribute("foodId") int foodId,
                                    @SessionAttribute("ticketId") int ticketId,
                                    HttpServletRequest request, @RequestParam(required = false) String token,
                                    HttpSession session) {
        // Lấy các tham số từ VNPay
        String vnp_TxnRef = request.getParameter("vnp_TxnRef");
        String vnp_Amount = request.getParameter("vnp_Amount");
        String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
        String vnp_TransactionNo = request.getParameter("vnp_TransactionNo");
        String vnp_BankCode = request.getParameter("vnp_BankCode");
        String vnp_SecureHash = request.getParameter("vnp_SecureHash");

        if (token != null) {
            // Thực hiện xác thực JWT
            // Giả sử bạn có một hàm verifyToken() để xác thực và lấy thông tin người dùng từ token
            UserCustomDetail userDetails = jwtService.verifyToken(token);
            if (userDetails != null) {
                Map fields = new HashMap();

                for (Enumeration params = request.getParameterNames(); params.hasMoreElements(); ) {
                    String fieldName = null;
                    String fieldValue = null;
                    try {
                        fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                        // Kiểm tra nếu tham số là token thì bỏ qua
                        if ("token".equalsIgnoreCase(fieldName)) {
                            continue; // Bỏ qua tham số token
                        }
                        fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    if ((fieldValue != null) && (fieldValue.length() > 0)) {
                        fields.put(fieldName, fieldValue);
                    }
                }

                if (fields.containsKey("vnp_SecureHashType")) {
                    fields.remove("vnp_SecureHashType");
                }
                if (fields.containsKey("vnp_SecureHash")) {
                    fields.remove("vnp_SecureHash");
                }
                String signValue = Config.hashAllFields(fields);

                String username = jwtService.getUsername(token);
                // Lấy thông tin người dùng
                Optional<User> user = userService.findByUsername(username);
                if (user == null) {
                    return "USER_NOT_FOUND"; // Xử lý trường hợp không tìm thấy người dùng
                }

                // Kiểm tra và xác nhận kết quả thanh toán từ VNPAY
                String transactionId = (String) fields.get("vnp_TxnRef"); // Mã giao dịch
                Bill bill = billService.findByTradingCode(vnp_TxnRef);
                // So sánh secureHash nhận được từ VNPay với secureHash tính toán
                if (signValue.equals(vnp_SecureHash)) {
                    if ("00".equals(vnp_ResponseCode)) {
                        // Lấy foodId từ session
//                        Integer foodId = (Integer) session.getAttribute("foodId");
                        System.out.println("foodId from session: " + foodId);
                        // Lấy ticketId từ session
//                        Integer ticketId = (Integer) session.getAttribute("ticketId");

                        // Giao dịch thành công
                        billService.sendMail(user, bill);
                        // Update Bill là active
                        billService.updateBillActive(transactionId);
                        // Add billfood
                        billService.addBillFood(bill.getBillID(), foodId);
                        // Add bill ticket
                        billService.addBillTicket(bill.getBillID(), ticketId);
                        // Update ticket after payment successful
                        ticketService.editTicket(ticketId);

                        return "Thanh toán thành công! Mã giao dịch: " + vnp_TxnRef;
                    } else {
                        // Giao dịch không thành công
                        return "Giao dịch thất bại với mã lỗi: " + vnp_ResponseCode;
                    }
                } else {
                    // Chữ ký không hợp lệ
                    return "Chữ ký không hợp lệ!";
                }
            } else {
                return "Vui lòng đăng nhập lại";
            }
        } else {
            return "Vui lòng đăng nhập lại";
        }

    }
}

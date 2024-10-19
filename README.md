**CHỨC NĂNG DỰ ÁN VÉ XEM PHIM**  
Link tham khảo: https://betacinemas.vn/home.htm

1.  Chức năng **đăng ký,** gửi mã hoặc link xác nhận về email, xác thực đăng ký tài khoản
2.  Chức năng **đăng nhập**, sử dụng phương thức xác thực là JwtBearer
3.  Chức năng **đổi mật khẩu**: người đổi mật khẩu phải là người đang trong phiên đăng nhập, chứ không được truyền vào userid trong controller
4.  Chức năng **quên mật khẩu** - gửi mã xác nhận về email, chức năng tạo mật khẩu mới
5.  Hiển thị các bộ **phim nổi bật**(sắp xếp theo số lượng đặt vé)
6.  Thêm, sửa, xóa **Cinema**(Admin)
7.  Thêm, sửa, xóa **Room**(Admin)
8.  Thêm, sửa, xóa **Seat**(Admin)
9.  Thêm, sửa, xóa **Food**(Admin)
10. Hiển thị phim theo rạp, phòng, trạng thái ghế trong phòng
11. Thêm, sửa, xóa **Movie**
12. Xử lý luồng **thanh toán VNPay** sau:

- "Chọn phim => Chọn rạp => Chọn phòng => Chọn suất chiếu, đồ ăn => Tạo hóa đơn => thanh toán VNPay (dựa vào Promotion để tính giá tiền sau cùng)"
- Sau khi thanh toán thành công thì thông báo được gửi về email của người dùng đồng thời add và update các bàng liên quan như bill, billticket, ticket

13. "CRUD **Schedule** - kiểm tra và đảm bảo là trong một phòng không chiếu đồng thời 2 phim trong một khoảng thời gian giao nhau" (Admin + Manager)
14. **Thống kê doanh số của từng rạp** theo khoảng thời gian(Admin)
15. **Thống kê đồ ăn bán chạy** trong 7 ngày gần nhất(Admin)
16. Quản lý **thông tin của người dùng** Thêm, Sửa, Xóa: (Admin)

* * *

**DEMO CHỨC NĂNG ĐẶT VÉ VNPAY**

* * *
![image](https://github.com/user-attachments/assets/db226713-5036-4c69-a748-559f22ab6242)
![image](https://github.com/user-attachments/assets/f44478f3-d369-4d39-ae61-6ad59902f9c6)
![image](https://github.com/user-attachments/assets/0e4664fa-96c1-471b-81c1-a84e2b0397dc)
![image](https://github.com/user-attachments/assets/7098eee8-20be-4f5b-a9cb-73be9af5a541)
![image](https://github.com/user-attachments/assets/dafdb0a3-4077-41a4-9403-a9735a96198d)
![image](https://github.com/user-attachments/assets/59f051f7-2efa-49b8-a642-4114bbb24611)


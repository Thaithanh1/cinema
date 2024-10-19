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

  
![97ab9843fcbee40aecc0f9460d8bafaf.png](:/90378c654c744f918e695c050f36c419)  
![28beeba744c79d31d62744d5fd2995bb.png](:/47d3de00fb18458cb7451805069e5e6d)  
![d3f83ce2837c7f1c7b5436d12c6067a2.png](:/df1b0946356748f89cfb49af356fb939)  
![d0d96c07a35a4ce5a8b8565e8232e834.png](:/4f5661dc7a0947929bd2e9fba73894bd)  
![ab81951364f36a1a63126cc2b611f0be.png](:/3167f68a3ac745cab3921a3a94c165eb)  
![9ba11e034c0fd6a107fb2ccdf8c0bea7.png](:/41b8babfd65e4d8883a1d3da6c191f43)

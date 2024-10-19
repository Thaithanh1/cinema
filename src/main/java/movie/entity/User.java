package movie.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userID;

    @Column(name = "point")
    private int point;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "isactive", columnDefinition = "bit")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "rankcustomerid", insertable = false, updatable = false)
    @JsonIgnore
    private RankCustomer rankCustomer;
    @Column(name = "rankcustomerid")
    private int rankCustomerID;

    @ManyToOne
    @JoinColumn(name = "userstatusid", insertable = false, updatable = false)
    @JsonIgnore
    private UserStatus userStatus;
    @Column(name = "userstatusid")
    private int userStatusID;

    @ManyToOne
    @JoinColumn(name = "roleid", insertable = false, updatable = false)
    @JsonIgnore
    private Role role;
    @Column(name = "roleid")
    private int roleID;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<RefreshToken> refreshTokens;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ConfirmEmail> confirmEmails;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Bill> bills;

    // Giả sử bạn có một phương thức để lấy danh sách quyền của người dùng
    @Transient // Đánh dấu trường không được ánh xạ đến cơ sở dữ liệu
    private List<String> authorities;

    // Getters and Setters

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(authority -> (GrantedAuthority) () -> authority)
                .toList(); // Chuyển đổi danh sách String thành danh sách GrantedAuthority
    }



    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public RankCustomer getRankCustomer() {
        return rankCustomer;
    }

    public void setRankCustomer(RankCustomer rankCustomer) {
        this.rankCustomer = rankCustomer;
    }

    public int getRankCustomerID() {
        return rankCustomerID;
    }

    public void setRankCustomerID(int rankCustomerID) {
        this.rankCustomerID = rankCustomerID;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public int getUserStatusID() {
        return userStatusID;
    }

    public void setUserStatusID(int userStatusID) {
        this.userStatusID = userStatusID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public List<RefreshToken> getRefreshTokens() {
        return refreshTokens;
    }

    public void setRefreshTokens(List<RefreshToken> refreshTokens) {
        this.refreshTokens = refreshTokens;
    }

    public List<ConfirmEmail> getConfirmEmails() {
        return confirmEmails;
    }

    public void setConfirmEmails(List<ConfirmEmail> confirmEmails) {
        this.confirmEmails = confirmEmails;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

}

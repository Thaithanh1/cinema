package movie.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import movie.enums.RoleEnums;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int roleID;

    @Column(name = "code")
    private String code;

    @Column(name = "rolename")
    private RoleEnums roleEnums;

    @OneToMany(mappedBy = "role")
    @JsonManagedReference
    private List<User> users;

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RoleEnums getRoleEnums() {
        return roleEnums;
    }

    public void setRoleEnums(RoleEnums roleEnums) {
        this.roleEnums = roleEnums;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

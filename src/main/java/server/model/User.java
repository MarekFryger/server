package server.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Generated;
import server.dto.UserToAdminListDTO;
import server.generic.GenericEntity;


@NamedNativeQuery(name = "User.getListAll", query = "select u.id, u.username, u.email, u.active from public.users as u  " ,resultSetMapping = "getListAllMapping")
@SqlResultSetMapping(name = "getListAllMapping",classes = {
    @ConstructorResult(
        targetClass = UserToAdminListDTO.class , columns = {
            @ColumnResult(name = "id", type = String.class),
            @ColumnResult(name = "username", type = String.class),
            @ColumnResult(name = "email",type = String.class ),
            @ColumnResult(name = "active",type = Boolean.class)
        }
    )
})
@Data
@Audited
@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")
    
},
indexes = {
    @Index(name = "idx_username",columnList ="username"),
    @Index(name = "idx_active", columnList ="active")
})
public class User implements GenericEntity {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;
    
    private Boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
  
    public User() {
    }
    @Generated
    public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
            @NotBlank @Size(max = 120) String password, Boolean active) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
    } 
    
    @Generated
    public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
            @NotBlank @Size(max = 120) String password) {
        this.username = username;
        this.email = email;
        this.password = password; 
      
    }
    @Generated
    public User(String id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
            Boolean active, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.active = active;
        this.roles = roles;
    }
}

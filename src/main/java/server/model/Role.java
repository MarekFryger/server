package server.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import server.generic.GenericEntity;

@Audited
@Data
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GenericEntity {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum name;
    private Boolean active;

    public Role() {
      // this constructor is empty
    }
}

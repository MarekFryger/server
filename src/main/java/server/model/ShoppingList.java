package server.model;

import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import server.generic.GenericEntity;
@Data
@Entity
@Table(name = "shopping_list")
public class ShoppingList implements GenericEntity {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;
    private Boolean active;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Set<Item> items;

    public ShoppingList() {
        // this constructor is empty
    }


}

package server.model;


import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import server.generic.GenericEntity;

@Data
@Entity
@Table(name = "products")
public class Product implements GenericEntity{

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;
    private Boolean active;
    private String productName;
    private String quantity;
   
   
    
    
    public Product() {
         // this constructor is empty
    }

}

package javaEE.springboot.springbootdemo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "t_sold")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private ShopItems item;

    @Column(name = "buyDate")
    private Date buyDate;

    @Column(name = "amount")
    private int amount;

    @Column(name = "owner")
    private String owner;

}

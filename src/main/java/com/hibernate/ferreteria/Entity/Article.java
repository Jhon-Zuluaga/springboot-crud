package com.hibernate.ferreteria.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generar automaticamente los geters y seters
@NoArgsConstructor // Generar un constructor vacio obligatorio para Jpa
@AllArgsConstructor // Generar constructor rapidamente para guardar datos
@Entity
@Table(name="article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // OPCIONAL
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "stock")
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}

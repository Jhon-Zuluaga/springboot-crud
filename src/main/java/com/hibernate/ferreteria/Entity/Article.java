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

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "stock")
    private Integer stock;
}

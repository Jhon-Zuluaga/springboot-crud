package com.hibernate.ferreteria.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="client")
    private String client;

    @Column(name ="total")
    private Double total;

    @Column(name ="date")
    private String date;

    @ManyToMany
    @JoinTable(
            name="sale_article",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns =  @JoinColumn(name = "article_id")
    )
    private List<Article> articles;
}

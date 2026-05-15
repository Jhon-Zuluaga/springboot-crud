package com.hibernate.ferreteria.mapper;

import com.hibernate.ferreteria.DTOs.ArticleDTO;
import com.hibernate.ferreteria.Entity.Article;
import com.hibernate.ferreteria.Entity.Store;
import com.hibernate.ferreteria.Entity.Supplier;

// El mapper se encarga de transformar datos entre la clase Article entity y ArticleDTO
// Convertir datos

public class ArticleMapper {

    public static ArticleDTO toDTO(Article article){
        return new ArticleDTO(
                article.getId(),
                article.getName(),
                article.getPrice(),
                article.getStock(),
                article.getSupplier() != null ? article.getSupplier().getId() : null,
                article.getStore() != null ? article.getStore().getId() : null
        );
    }

    public static Article toEntity(ArticleDTO dto, Supplier supplier, Store store) {
        Article article = new Article();
        article.setName(dto.getName());
        article.setPrice(dto.getPrice());
        article.setStock(dto.getStock());
        article.setSupplier(supplier);
        article.setStore(store);
        return article;
    }
}

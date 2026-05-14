package com.hibernate.ferreteria.mapper;

import com.hibernate.ferreteria.DTOs.ArticleDTO;
import com.hibernate.ferreteria.Entity.Article;

// El mapper se encarga de transformar datos entre la clase Article entity y ArticleDTO
// Convertir datos

public class ArticleMapper {

    public static ArticleDTO toDTO(Article article){
        return new ArticleDTO(
                article.getId(),
                article.getNombre(),
                article.getPrecio(),
                article.getStock()
        );
    }

    public static Article toEntity(ArticleDTO dto){
        Article article = new Article();
        article.setId((int)dto.getId());
        article.setNombre(dto.getNombre());
        article.setPrecio(dto.getPrecio());
        article.setStock(dto.getStock());
        return article;
    }
}

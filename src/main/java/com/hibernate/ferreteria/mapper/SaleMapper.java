package com.hibernate.ferreteria.mapper;

import com.hibernate.ferreteria.DTOs.SaleDTO;
import com.hibernate.ferreteria.Entity.Article;
import com.hibernate.ferreteria.Entity.Sale;

import java.util.List;
import java.util.stream.Collectors;

public class SaleMapper {
    public static SaleDTO toDTO(Sale sale){
        List<Integer> ids = sale.getArticles()
                .stream().map(Article::getId)
                .collect(Collectors.toList());
        return new SaleDTO(
                sale.getId(),
                sale.getClient(),
                sale.getTotal(),
                sale.getDate(),
                ids
        );
    }

    public static Sale toEntity(SaleDTO dto, List<Article> articles){
        Sale sale = new Sale();
        sale.setClient(dto.getClient());
        sale.setTotal(dto.getTotal());
        sale.setDate(dto.getDate());
        sale.setArticles(articles);
        return sale;
    }
}

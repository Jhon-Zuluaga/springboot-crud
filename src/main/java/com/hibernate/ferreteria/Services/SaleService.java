package com.hibernate.ferreteria.Services;

import com.hibernate.ferreteria.DTOs.SaleDTO;
import com.hibernate.ferreteria.Entity.Article;
import com.hibernate.ferreteria.Entity.Sale;
import com.hibernate.ferreteria.Repository.ArticleRepository;
import com.hibernate.ferreteria.Repository.SaleRepository;
import com.hibernate.ferreteria.mapper.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<SaleDTO> getAll(){
        return saleRepository.findAll().stream()
                .map(SaleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SaleDTO getById(Long id){
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found " + id));
        return SaleMapper.toDTO(sale);
    }

    public SaleDTO Create(SaleDTO dto){
        if(dto.getClient() == null || dto.getClient().isBlank())
            throw new RuntimeException("Client is required");
        if(dto.getArticleIds() == null || dto.getArticleIds().isEmpty())
            throw new RuntimeException("Sale must have at least one article");

        List<Article> articles = articleRepository.findAllById(
                dto.getArticleIds().stream().map(Long::valueOf)
                        .collect(Collectors.toList())
        );

        if(articles.isEmpty())
            throw new RuntimeException("No valid articles found");
        double total = articles.stream()
                .mapToDouble(Article::getPrice)
                .sum();
                dto.setTotal(total); // Calcula el total automaticamente

        Sale saved = saleRepository.save(SaleMapper.toEntity(dto, articles));
        return SaleMapper.toDTO(saved);
    }

    public String Delete(Long id){
        if(!saleRepository.existsById(id))
            throw new RuntimeException("Sale not found: " + id);
        saleRepository.deleteById(id);
        return "Sale deleted successfully";
    }
}

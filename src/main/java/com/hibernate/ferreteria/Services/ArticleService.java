package com.hibernate.ferreteria.Services;

import com.hibernate.ferreteria.DTOs.ArticleDTO;
import com.hibernate.ferreteria.Entity.Article;
import com.hibernate.ferreteria.Repository.ArticleRepository;
import com.hibernate.ferreteria.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Logica de negocio

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository _repository;

    public ArticleDTO Create(ArticleDTO dto){
        Article article = ArticleMapper.toEntity(dto);
        Article save = _repository.save(article);
        return ArticleMapper.toDTO(save);
    }

    public ArticleDTO Update(Long id, ArticleDTO dto){
        Optional<Article> exists = _repository.findById(id);
        if(exists.isPresent()) {
            Article article = exists.get();
            article.setNombre(dto.getNombre());
            article.setPrecio(dto.getPrecio());
            article.setStock(dto.getStock());

            Article update = _repository.save(article);
            return ArticleMapper.toDTO(update);
        }else{
            throw new RuntimeException("Articulo no encontrado" + id);
        }
    }

    public String Delete(Long id){
        if(_repository.existsById(id)){
            _repository.deleteById(id);
            return "Articulo eliminado";
        }else{
            return "No se encontró articulo con ese id ";
        }
    }

    public List<ArticleDTO> Consulta(){
        return _repository.findAll().stream().map(ArticleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ArticleDTO GetById(Long id){
        Optional<Article> exists = _repository.findById(id);
        if(exists.isPresent()){
            return ArticleMapper.toDTO(exists.get());
        }else{
            throw new RuntimeException("Articulo no encontrado: " + id);
        }
    }
}

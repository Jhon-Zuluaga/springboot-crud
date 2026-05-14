package com.hibernate.ferreteria.Controller;

import com.hibernate.ferreteria.DTOs.ArticleDTO;
import com.hibernate.ferreteria.Entity.Article;
import com.hibernate.ferreteria.Repository.ArticleRepository;
import com.hibernate.ferreteria.Services.ArticleService;
import com.hibernate.ferreteria.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private ArticleService service;

    @GetMapping
    public List<ArticleDTO> list(){
        return service.Consulta();
    }

    @PostMapping
    public ArticleDTO create(@RequestBody ArticleDTO dto){
        return service.Create(dto);
    }

    @PutMapping("/{id}")
    public ArticleDTO update(@PathVariable Long id, @RequestBody ArticleDTO dto){
        return service.Update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return service.Delete(id);
    }

    @GetMapping("/{id}")
    public ArticleDTO getById(@PathVariable Long id){
        return service.GetById(id);
    }

//    private ArticleRepository _repository;
//    // Devolver datos mediante ArticleDTO / ArticleMapper
//    @GetMapping
//    public List<ArticleDTO> Consulta(){
//        return _repository.findAll().stream().map(ArticleMapper::toDTO)
//                .collect(Collectors.toList());
//    }

//    @GetMapping("all")
//    public List<Article> Consulta(){
//       return (List<Article>) _repository.findAll();
//    }
}

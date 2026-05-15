package com.hibernate.ferreteria.Services;

import com.hibernate.ferreteria.DTOs.ArticleDTO;
import com.hibernate.ferreteria.Entity.Article;
import com.hibernate.ferreteria.Entity.Store;
import com.hibernate.ferreteria.Entity.Supplier;
import com.hibernate.ferreteria.Repository.ArticleRepository;
import com.hibernate.ferreteria.Repository.StoreRepository;
import com.hibernate.ferreteria.Repository.SupplierRepository;
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

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private StoreRepository storeRepository;

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

    public ArticleDTO Create(ArticleDTO dto){
        Supplier supplier = supplierRepository.findById(Long.valueOf(dto.getSupplierId()))
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        Store store = storeRepository.findById(Long.valueOf(dto.getStoreId()))
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Article article = ArticleMapper.toEntity(dto, supplier, store);
        return ArticleMapper.toDTO(_repository.save(article));
    }

    public ArticleDTO Update(Long id, ArticleDTO dto){
        Article article = _repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Articulo no encontrado: " + id));

        Supplier supplier = supplierRepository.findById(Long.valueOf(dto.getSupplierId()))
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        Store store = storeRepository.findById(Long.valueOf(dto.getStoreId()))
                .orElseThrow(() -> new RuntimeException("Store not found"));

        article.setName(dto.getName());
        article.setPrice(dto.getPrice());
        article.setStock(dto.getStock());
        article.setSupplier(supplier);
        article.setStore(store);

        return ArticleMapper.toDTO(_repository.save(article));
    }

    public String Delete(Long id){
        if(_repository.existsById(id)){
            _repository.deleteById(id);
            return "Articulo eliminado";
        }else{
            return "No se encontró articulo con ese id ";
        }
    }
}

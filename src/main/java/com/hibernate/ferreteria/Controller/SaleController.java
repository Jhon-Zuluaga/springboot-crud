package com.hibernate.ferreteria.Controller;

import com.hibernate.ferreteria.DTOs.SaleDTO;
import com.hibernate.ferreteria.Services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping
    public List<SaleDTO> all(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SaleDTO getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public SaleDTO create(@RequestBody SaleDTO dto){
        return service.Create(dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return service.Delete(id);
    }

}

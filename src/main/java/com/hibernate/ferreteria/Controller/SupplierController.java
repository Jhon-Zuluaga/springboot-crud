package com.hibernate.ferreteria.Controller;


import com.hibernate.ferreteria.DTOs.SupplierDTO;
import com.hibernate.ferreteria.Services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService service;

    @GetMapping
    public List<SupplierDTO> all() {
        return service.All();
    }

    @GetMapping("/{id}")
    public SupplierDTO getById(@PathVariable Long id){
        return  service.GetById(id);
    }

    @PostMapping
    public SupplierDTO create(@RequestBody SupplierDTO dto){
        return service.Create(dto);
    }

    @PutMapping("/{id}")
    public SupplierDTO update(@PathVariable Long id, @RequestBody SupplierDTO dto){
        return service.Update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return service.Delete(id);
    }

}


package com.hibernate.ferreteria.Controller;

import com.hibernate.ferreteria.DTOs.StoreDTO;
import com.hibernate.ferreteria.Services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreService service;

    @GetMapping
    public List<StoreDTO> all(){
        return service.All();
    }

    @GetMapping("/{id}")
    public StoreDTO getById(@PathVariable Long id){
        return service.GetById(id);
    }

    @PostMapping
    public StoreDTO create(@RequestBody StoreDTO dto){
        return service.Create(dto);
    }

    @PutMapping("/{id}")
    public StoreDTO  update(@PathVariable Long id, @RequestBody StoreDTO dto){
        return service.Update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return service.Delete(id);
    }
}

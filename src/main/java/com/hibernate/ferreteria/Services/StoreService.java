package com.hibernate.ferreteria.Services;

import com.hibernate.ferreteria.DTOs.StoreDTO;
import com.hibernate.ferreteria.Entity.Store;
import com.hibernate.ferreteria.Repository.StoreRepository;
import com.hibernate.ferreteria.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService {

    @Autowired
    private StoreRepository _repository;

    public List<StoreDTO> All(){
        return _repository.findAll().stream().map(StoreMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StoreDTO GetById(Long id){
        Optional<Store> exists = _repository.findById(id);
        if(exists.isPresent()){
            return StoreMapper.toDTO(exists.get());
        }else{
            throw new RuntimeException("Articulo no encontrado " + id);
        }
    }

    public StoreDTO Create(StoreDTO dto){
        if(dto.getName() == null || dto.getName().isBlank())
            throw new RuntimeException("Name is required");
        if(dto.getCapacity() == null || dto.getCapacity() <= 0)
            throw new RuntimeException("Capacity must be greater than 0");
        Store saved = _repository.save(StoreMapper.toEntity(dto));
        return StoreMapper.toDTO(saved);
    }

    public StoreDTO Update(Long id, StoreDTO dto){
        Store store = _repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found: " + id));

        if(dto.getName() == null || dto.getName().isBlank())
            throw new RuntimeException("Name is required");
        if(dto.getCapacity() == null || dto.getCapacity() <= 0)
            throw new RuntimeException("Capacity must be greater than 0");

        store.setName(dto.getName());
        store.setLocation(dto.getLocation());
        store.setCapacity(dto.getCapacity());
        return StoreMapper.toDTO(_repository.save(store));
    }

    public String Delete(Long id){
        if(!_repository.existsById(id))
            throw new RuntimeException("Store not found " + id);
        _repository.deleteById(id);
        return "Store deleted successfully";
    }
}

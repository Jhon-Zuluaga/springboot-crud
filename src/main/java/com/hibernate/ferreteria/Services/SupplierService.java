package com.hibernate.ferreteria.Services;

import com.hibernate.ferreteria.DTOs.SupplierDTO;
import com.hibernate.ferreteria.Entity.Supplier;
import com.hibernate.ferreteria.Repository.SupplierRepository;
import com.hibernate.ferreteria.mapper.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository _repository;


    public List<SupplierDTO> All(){
        return _repository.findAll().stream().map(SupplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SupplierDTO GetById(Long id){
        Optional<Supplier> exists = _repository.findById(id);
        if(exists.isPresent()){
            return SupplierMapper.toDTO(exists.get());
        }else{
            throw new RuntimeException("Articulo no encontrado: " + id);
        }
    }

    public SupplierDTO Create(SupplierDTO dto){

        if(dto.getName() == null || dto.getName().isBlank())
            throw new RuntimeException("Name is required");
        if(dto.getEmail() == null || dto.getEmail().isBlank())
            throw new RuntimeException("Email is required");
        if(dto.getPhone() == null || dto.getPhone().isBlank())
            throw new RuntimeException("Phone is required");

        Supplier supplier = SupplierMapper.toEntity(dto);
        Supplier save = _repository.save(supplier);
        return SupplierMapper.toDTO(save);
    }

    public SupplierDTO Update(Long id, SupplierDTO dto){

        if(dto.getName() == null || dto.getName().isBlank())
            throw new RuntimeException("Name is required");

        Optional<Supplier> exists = _repository.findById(id);
        if(exists.isPresent()){
            Supplier supplier = exists.get();
            supplier.setName(dto.getName());
            supplier.setAddresss(dto.getAddress());
            supplier.setEmail(dto.getEmail());
            supplier.setPhone(dto.getPhone());

            Supplier update = _repository.save(supplier);
            return SupplierMapper.toDTO(update);
        }else{
            throw new RuntimeException("Proveedor no encontrado " + id);
        }
    }

    public String Delete(Long id){
        if(_repository.existsById(id)){
            _repository.deleteById(id);
            return "Proveedor eliminado";
        } else{
            return "No se encontró proveedor con ese id ";
        }
    }
}

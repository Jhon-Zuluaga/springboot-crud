package com.hibernate.ferreteria.mapper;

import com.hibernate.ferreteria.DTOs.SupplierDTO;
import com.hibernate.ferreteria.Entity.Supplier;

public class SupplierMapper {
    public static SupplierDTO toDTO(Supplier supplier){
        return new SupplierDTO(
                supplier.getId(),
                supplier.getName(),
                supplier.getPhone(),
                supplier.getEmail(),
                supplier.getAddresss()
        );
    }

    public static Supplier toEntity(SupplierDTO dto){
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setPhone(dto.getPhone());
        supplier.setEmail(dto.getEmail());
        supplier.setAddresss(dto.getAddress());
        return supplier;
    }
}

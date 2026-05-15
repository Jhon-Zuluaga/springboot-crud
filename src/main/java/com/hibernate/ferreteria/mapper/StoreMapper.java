package com.hibernate.ferreteria.mapper;

import com.hibernate.ferreteria.DTOs.StoreDTO;
import com.hibernate.ferreteria.Entity.Store;

public class StoreMapper {
    public static StoreDTO toDTO(Store store){
        return new StoreDTO(
                store.getId(),
                store.getName(),
                store.getLocation(),
                store.getCapacity()
        );
    }

    public static Store toEntity(StoreDTO dto){
        Store store = new Store();
        store.setName(dto.getName());
        store.setLocation(dto.getLocation());
        store.setCapacity(dto.getCapacity());
        return store;
    }
}

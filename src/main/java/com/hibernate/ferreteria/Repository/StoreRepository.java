package com.hibernate.ferreteria.Repository;

import com.hibernate.ferreteria.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}

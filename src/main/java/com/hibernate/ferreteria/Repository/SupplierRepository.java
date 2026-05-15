package com.hibernate.ferreteria.Repository;

import com.hibernate.ferreteria.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}

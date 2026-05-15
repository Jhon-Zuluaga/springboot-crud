package com.hibernate.ferreteria.Repository;

import com.hibernate.ferreteria.Entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}

package com.hibernate.ferreteria.Repository;

import com.hibernate.ferreteria.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// Interfaz para Spring JPA -> proporcinar capa de acceso a datos
// Sin escribir datos repetitivos

public interface ArticleRepository extends JpaRepository<Article, Long> {
}

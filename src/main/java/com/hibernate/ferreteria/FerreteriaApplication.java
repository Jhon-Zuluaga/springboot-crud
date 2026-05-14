package com.hibernate.ferreteria;

import com.hibernate.ferreteria.Entity.Article;
import com.hibernate.ferreteria.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FerreteriaApplication implements CommandLineRunner {

	@Autowired
	private ArticleRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(FerreteriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicación iniciada correctamente");
		List<Article> articles = repository.findAll();
		articles.stream().forEach(System.out::println);
	}
}

#  Ferretería  — Spring Boot 

>  **Proyecto de práctica** — Este proyecto es un ejercicio personal para retomar y reforzar conocimientos en Spring Boot, JPA/Hibernate, y desarrollo de APIs REST. 

---

##  Descripción

Sistema de control de inventario para una ferretería ficticia llamada **Antillana**. Permite gestionar productos, proveedores, almacenes y ventas a través de una API REST conectada a una base de datos MySQL, con un frontend sencillo en HTML, CSS y JavaScript vanilla.

---

##  Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.4.6**
- **Spring Data JPA / Hibernate**
- **Spring Security** (básico)
- **MySQL**
- **Lombok**
- **HTML + CSS + JavaScript + jQuery + SweetAlert2** (frontend)

---

##  Estructura del proyecto

```
src/
├── main/
│   ├── java/com/hibernate/ferreteria/
│   │   ├── Controller/       # Controladores REST
│   │   ├── DTOs/             # Objetos de transferencia de datos
│   │   ├── Entity/           # Entidades JPA
│   │   ├── mapper/           # Conversión Entity ↔ DTO
│   │   ├── Repository/       # Interfaces JpaRepository
│   │   └── Services/         # Lógica de negocio
│   └── resources/
│       └── static/
│           ├── index.html
│           ├── pages/        # Vistas por entidad
│           ├── css/          # Estilos compartidos
│           └── js/           # Lógica JS por entidad
```

---

## 🗄️ Entidades y relaciones

```
Supplier ←── Article ──→ Store
               ↑
             Sale (ManyToMany → tabla intermedia sale_article)
```

| Entidad | Tabla | Descripción |
|---|---|---|
| Article | article | Productos del inventario |
| Supplier | supplier | Proveedores de los productos |
| Store | store | Almacenes donde se guardan productos |
| Sale | sale | Ventas realizadas |
| User | user | Usuarios del sistema (Spring Security) |

---

##  Endpoints disponibles

### Article `/api/article`
| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/article` | Listar todos |
| GET | `/api/article/{id}` | Buscar por ID |
| POST | `/api/article` | Crear |
| PUT | `/api/article/{id}` | Actualizar |
| DELETE | `/api/article/{id}` | Eliminar |

### Supplier `/api/supplier`
| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/supplier` | Listar todos |
| GET | `/api/supplier/{id}` | Buscar por ID |
| POST | `/api/supplier` | Crear |
| PUT | `/api/supplier/{id}` | Actualizar |
| DELETE | `/api/supplier/{id}` | Eliminar |

### Store `/api/store`
| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/store` | Listar todos |
| GET | `/api/store/{id}` | Buscar por ID |
| POST | `/api/store` | Crear |
| PUT | `/api/store/{id}` | Actualizar |
| DELETE | `/api/store/{id}` | Eliminar |

### Sale `/api/sale`
| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/sale` | Listar todas |
| GET | `/api/sale/{id}` | Buscar por ID |
| POST | `/api/sale` | Crear |
| DELETE | `/api/sale/{id}` | Eliminar |

---

##  Configuración

### `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_ferreteria
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
```

### Base de datos
Crear la base de datos en MySQL antes de correr el proyecto:
```sql
CREATE DATABASE db_ferreteria;
```

---

##  Cómo correr el proyecto

1. Clona el repositorio
```bash
git clone https://github.com/tu-usuario/ferreteria-antillana.git
```

2. Configura las credenciales de MySQL en `application.properties`

3. Crea la base de datos `db_ferreteria` en MySQL

4. Corre el proyecto desde IntelliJ o con Maven:
```bash
./mvnw spring-boot:run
```

5. Abre en el navegador:
```
http://localhost:8080/index.html
```

---

Proyecto personal de práctica — retomando Spring Boot desde cero. 

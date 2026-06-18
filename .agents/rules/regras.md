---
trigger: always_on
---

Este projeto deve seguir obrigatoriamente:

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven

Arquitetura:

Controller
Service
Repository
Model

Fluxo:

Controller → Service → Repository → Banco

Padrões:

- Utilizar Programação Orientada a Objetos
- Encapsulamento
- Classes e Objetos
- Atributos privados
- Métodos públicos
- Injeção de dependência via @Autowired ou construtor
- JpaRepository para acesso aos dados
- Entidades anotadas com @Entity
- Chave primária utilizando @Id e @GeneratedValue
- Código limpo e organizado
- Nomes em português para entidades de negócio
- Seguir o padrão já existente da entidade Produto

Nunca gerar código fora da arquitetura definida.
Nunca misturar regra de negócio dentro do Controller.
Nunca acessar Repository diretamente pelo Controller.
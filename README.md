# Sistema de Ordem de Serviço — Climatizar AC

## Descrição

Este projeto foi desenvolvido como trabalho acadêmico da disciplina de Programação Orientada a Objetos, com o objetivo de aplicar na prática os conceitos fundamentais da linguagem Java em um cenário real de micro-empresa.

O sistema simula o gerenciamento de ordens de serviço de uma empresa especializada em **instalação, manutenção e limpeza de ar-condicionado**, permitindo registrar clientes, técnicos, equipamentos e materiais utilizados em cada atendimento, além de calcular o valor total do serviço prestado.

---

## Funcionalidades

- Cadastro de **clientes** com dados pessoais e endereço
- Cadastro de **técnicos** com especialidade e registro profissional
- Registro de **equipamentos** atendidos com marca, modelo e defeito relatado
- Controle de **materiais** utilizados com preço unitário
- Criação e exibição de **ordens de serviço** com status, descrição e cálculo automático do valor total

---

## Conceitos de POO aplicados

| Conceito | Aplicação no projeto |
|---|---|
| **Classes e Objetos** | `Cliente`, `Tecnico`, `Equipamento`, `Material`, `OrdemDeServico` |
| **Encapsulamento** | Atributos `private`/`protected` com métodos getters |
| **Herança** | `Cliente` e `Tecnico` herdam da classe abstrata `Pessoa` |
| **Abstração** | Classe abstrata `Pessoa` com método abstrato `exibirDados()` |
| **Polimorfismo** | `exibirDados()` implementado de forma diferente em cada subclasse |
| **Interface** | `ICalculavel` implementada por `OrdemDeServico` para calcular o total |
| **Coleções** | `List<Equipamento>` e `List<Material>` para múltiplos itens por OS |

---

## Estrutura do projeto

```
src/
└── main/
    └── java/
        └── com/
            └── ordemservico/
                ├── Pessoa.java          # Classe abstrata base
                ├── Cliente.java         # Herda de Pessoa
                ├── Tecnico.java         # Herda de Pessoa
                ├── Equipamento.java     # Equipamento atendido
                ├── Material.java        # Material utilizado no serviço
                ├── OrdemDeServico.java  # Ordem de serviço completa
                ├── ICalculavel.java     # Interface de cálculo
                └── Main.java            # Classe principal (demonstração)
```

---

## Tecnologias utilizadas

- **Java 17**
- **Maven** (gerenciamento de dependências)
- **IntelliJ IDEA** (ambiente de desenvolvimento)

---

## Como executar

1. Clone ou extraia o projeto
2. Abra na IDE de sua preferência (IntelliJ IDEA recomendado)
3. Certifique-se de que a pasta `src/main/java` está marcada como **Sources Root**
4. Execute a classe `Main.java`

---

## Autores
Gabriel Bortoloti 
João Victor da Silva
Lucas Furco Righetto

Desenvolvido por alunos do curso de Ciência da Computação — UNI-Facef / Franca-SP
Disciplina: Programação Orientada a Objetos  
Ano: 2026

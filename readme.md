# Sistema de Biblioteca — TDD Project

## Descrição do projeto

Este projeto implementa o **domínio de um sistema de biblioteca** utilizando **Test-Driven Development (TDD)** em Java com Maven e JUnit 5.

O contexto escolhido simula o ciclo de vida de empréstimos de livros em uma biblioteca, com regras de negócio como:

- Controle de estoque e disponibilidade de exemplares (`Livro`)
- Limite de empréstimos simultâneos e bloqueio por inadimplência (`Usuario`)
- Prazo de devolução e geração de multas por atraso (`Emprestimo`)
- Cálculo e quitação de penalidades financeiras (`Multa`)

O desenvolvimento seguiu o ciclo **Red → Green → Refactor**: cada regra foi especificada primeiro em testes automatizados e, em seguida, implementada nas entidades de domínio.

---

## Como executar os testes

Pré-requisitos: **Java 24** e **Maven** instalados e disponíveis no `PATH`.

Na raiz do projeto, execute:

```bash
mvn test
```

## Estrutura de pastas

```
tdd-project/
└── src/
    ├── main/java/org/example/
    │   ├── Main.java                # Classe principal (bootstrap da aplicação)
    │   │
    │   ├── entities/                # Entidades de domínio — regras de negócio
    │   │   ├── Livro.java           # Exemplar físico/digital e controle de estoque
    │   │   ├── Usuario.java         # Leitor, limites de empréstimo e inadimplência
    │   │   ├── Emprestimo.java      # Transação entre usuário e livro (prazo e devolução)
    │   │   └── Multa.java           # Penalidade financeira por atraso na devolução
    │   │
    │   ├── enums/                   # Estados possíveis das entidades
    │   │   ├── StatusLivro.java     # DISPONIVEL, INDISPONIVEL, DANIFICADO
    │   │   ├── StatusUsuario.java   # ATIVO, INADIMPLENTE
    │   │   └── StatusMulta.java     # PENDENTE, PAGA
    │   │
    │   └── exceptions/              # Exceções de domínio
    │       ├── LivroIndisponivelException.java
    │       ├── UsuarioInadimplenteException.java
    │       └── LimiteEmprestimosExcedidoException.java
    │
    └── test/java/org/example/entities/
        ├── LivroTest.java           # 4 testes — empréstimo e devolução de cópias
        ├── UsuarioTest.java         # 5 testes — elegibilidade e registro de empréstimos
        ├── EmprestimoTest.java      # 3 testes — prazo, devolução no prazo e com atraso
        └── MultaTest.java           # 3 testes — cálculo, pagamento e inadimplência
```

---

## Resumo dos resultados

| Métrica | Resultado |
|---------|-----------|
| **Total de testes** | 15 |
| **Testes aprovados** | 15 |
| **Testes com falha** | 0 |
| **Classes de teste** | 4 (`LivroTest`, `UsuarioTest`, `EmprestimoTest`, `MultaTest`) |

### Detalhamento por entidade

| Classe de teste | Cenários | Foco |
|-----------------|----------|------|
| `LivroTest` | 4 | Empréstimo de cópias, indisponibilidade e devolução |
| `UsuarioTest` | 5 | Elegibilidade, limite de 3 empréstimos, registro e devolução |
| `EmprestimoTest` | 3 | Prazo de 14 dias, devolução no prazo e multa por atraso |
| `MultaTest` | 3 | Valor total (R$ 2,00/dia), pagamento integral e inadimplência |

### Cobertura

A cobertura está concentrada nas **quatro entidades de domínio** e nas regras de negócio implementadas via TDD. Todos os métodos públicos centrais de `Livro`, `Usuario`, `Emprestimo` e `Multa` são exercitados pelos testes.

| Área | Cobertura |
|------|-----------|
| Entidades de domínio | Alta — métodos principais testados |
| Enums e exceções | Indireta — utilizados nos fluxos testados |
| `Main.java` | Não testada (fora do escopo do domínio) |
| `Usuario.pagarMulta()` | Não testada (fora dos cenários atuais) |

> Para gerar relatório numérico de cobertura, adicione o plugin JaCoCo ao `pom.xml` e execute `mvn test jacoco:report`. O relatório HTML será gerado em `target/site/jacoco/index.html`.

---

### Integrantes

Felipe Aguiar
Larissa Vitória
Guilherme Henrique
Mateus Lemes
Waleria Assis
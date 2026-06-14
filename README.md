# 🐾 Sistema de Adoção de Pets

Sistema de gerenciamento para um abrigo de animais, desenvolvido em Java como atividade acadêmica. Permite o controle de pets, adoções, colaboradores, produtos, fornecedores, serviços veterinários, banho e tosa, doações financeiras e avaliações de clientes.

---

## 📋 Funcionalidades

- Cadastro e gerenciamento de pets (cachorros e gatos)
- Registro de adoções e histórico por adotante
- Gerenciamento de colaboradores e adotantes
- Controle de estoque de produtos (ração, brinquedos, remédios)
- Cadastro de fornecedores por categoria
- Agendamento de banho e tosa
- Registro de consultas e vacinas
- Doações financeiras com confirmação de recebimento
- Avaliações e feedbacks de clientes com média de estrelas
- Log automático de todas as operações do sistema

---

## 🏗️ Estrutura do Projeto

O projeto segue o padrão arquitetural **MVC (Model-View-Controller)**:

```
src/
├── controller/   # Regras de negócio e persistência de dados
├── model/        # Classes de domínio (entidades)
├── view/         # Interface com o usuário via terminal
├── interfaces/   # Interface genérica ICrud<T, ID>
├── util/         # Logger do sistema
└── Main.java     # Ponto de entrada
```

---

## 🔗 Classes e Relações

### Herança
- `Animal` (abstrata) ← `Cachorro`, `Gato`
- `Pessoa` (abstrata) ← `Adotante`, `Colaborador`, `Veterinario`, `Fornecedor`

### Associação
- `Adocao` referencia `Animal` (por ID) e `Adotante` (por CPF)
- `ConsultaVacina` referencia `Animal` (por ID)
- `BanhoTosa` referencia `Animal` (por nome)

### Interface
- Todos os controllers implementam `ICrud<T, ID>`, garantindo os métodos `cadastrar`, `listar`, `atualizar` e `deletar`

### Persistência
- Cada controller serializa sua lista em um arquivo `.dat` usando `ObjectOutputStream` / `ObjectInputStream`
- O `Logger` registra todas as operações em `log_sistema.txt`

---

## 👥 Divisão do Projeto

| Integrante | Responsabilidade |
|---|---|
| Pedro Henrique Toniolo Serra | MVC Pet, Funcionário (Colaborador) e Cliente (Adotante) |
| Afonso Guimarães de Camargo | MVC Produto e Fornecedor |
| Lucas Gustavo Busch | MVC Doação Financeira e Avaliação |
| Fabricio Milano K. | MVC Banho e Tosa e Veterinário |
| Eduardo Ventz Xavier | MVC Adoção e Consulta/Vacina |
| Todos | `ViewGeral` — menu principal do sistema |

---

## ▶️ Como Executar

**Pré-requisitos:** Java 17 ou superior instalado.

**Via IntelliJ IDEA:**
1. Clone o repositório
2. Abra o projeto no IntelliJ IDEA
3. Aguarde a indexação e execute a classe `Main.java`

**Via terminal:**
```bash
# Compile todos os arquivos
javac -d out src/**/*.java src/Main.java

# Execute
java -cp out Main
```

> Os arquivos `.dat` de dados e o `log_sistema.txt` serão criados automaticamente na raiz do projeto na primeira execução.

---

## 🤖 Uso de Inteligência Artificial

| IA | Como foi utilizada |
|---|---|
| **Gemini** | Auxiliou na estruturação inicial do sistema, sugerindo organização de classes e fluxos |
| **Claude** | Utilizado para conferência e revisão de erros no código, sugerindo correções e melhorias de boas práticas |

---

## 📚 Referências

- [Documentação oficial do Java](https://docs.oracle.com/en/java/)
- [Serialização em Java](https://docs.oracle.com/javase/tutorial/jndi/objects/serial.html)
- Conteúdo e materiais fornecidos em aula

# BookTree - Sistema de Biblioteca Digital

## Descrição
BookTree é um sistema de biblioteca digital desenvolvido para a disciplina de Linguagem de Programação II. O sistema integra estruturas de dados avançadas, algoritmos de ordenação e conceitos de POO avançada.

## Como Compilar e Executar

### Pré-requisitos
- Java JDK 8 ou superior
- PowerShell (Windows) ou terminal compatível

### Compilação

1. Abra o PowerShell na pasta raiz do projeto
2. Compile o projeto:

```powershell
javac -d out -sourcepath src src/br/com/booktree/app/Main.java
```

### Execução

```powershell
java -cp out br.com.booktree.app.Main
```

### Alternativa usando VS Code
Se estiver usando VS Code com extensões Java:
1. Abra o projeto no VS Code
2. Execute a classe `Main.java` diretamente pelo editor

## Características do Projeto

### Estruturas de Dados
- **Árvore AVL**: Para armazenar e recuperar publicações ordenadas por título
- **HashMap**: Para busca rápida por chave (repositório)
- **ArrayList**: Para manipulação de listas e ordenação

### Algoritmos
- **MergeSort**: Implementação genérica para ordenação por diferentes critérios
- **Balanceamento AVL**: Rotações automáticas para manter a árvore balanceada

### POO Avançada
- **Herança + Polimorfismo**: Classe base `Publicacao` com subclasses `Livro` e `Revista`
- **Interfaces Genéricas**: `Repositorio<T>`, `Ordenacao<T>`, `Arvore<K,V>`
- **Classes Genéricas**: `RepositorioHash<T>`, `ArvoreAVL<K,V>`, `MergeSort<T>`
- **Exceções Customizadas**: `NegocioException` para regras de negócio
- **Annotations + Reflection**: `@InfoAutor` lida em tempo de execução

### Regras de Negócio
- Ano de publicação deve ser ≥ 1500
- Título não pode ser vazio
- Autor não pode ser vazio
- ISBN de livro não pode ser vazio
- Edição de revista deve ser > 0

## Estrutura do Projeto

```
src/br/com/booktree/
├── anotacoes/
│   ├── InfoAutor.java          # Annotation personalizada
│   └── LeitorAnotacoes.java    # Utilitário para reflection
├── app/
│   └── Main.java               # Demonstração completa
├── arvore/
│   ├── Arvore.java            # Interface genérica
│   ├── ArvoreAVL.java         # Implementação AVL
│   └── NoAVL.java             # Nó da árvore
├── dominio/
│   ├── Publicacao.java        # Classe base (abstract)
│   ├── Livro.java             # Subclasse concreta
│   └── Revista.java           # Subclasse concreta
├── ordenacao/
│   ├── Ordenacao.java         # Interface genérica
│   ├── MergeSort.java         # Implementação MergeSort
│   └── Comparadores.java      # Comparadores prontos
└── repositorio/
    ├── Repositorio.java       # Interface genérica
    ├── RepositorioHash.java   # Implementação com HashMap
    └── NegocioException.java  # Exceção customizada
```

## Funcionalidades Demonstradas

### 1. Adição de Publicações
- Inserção de livros e revistas no repositório e árvore
- Demonstração de polimorfismo (classe base + 2 subclasses)
- Uso de interfaces e classes genéricas

### 2. Busca no Repositório
- Busca por título usando HashMap
- Acesso eficiente O(1) em média

### 3. Árvore AVL
- Inserção balanceada por título
- Travessia em-ordem (títulos alfabeticamente ordenados)
- Balanceamento automático

### 4. Algoritmo de Ordenação
- MergeSort genérico com Comparator
- Ordenação por ano de publicação
- Comparadores personalizados

### 5. Operação de Remoção
- Remoção do repositório e árvore
- Verificação de sucesso da operação

### 6. Listagem após Remoção
- Exibição das publicações restantes
- Verificação da integridade dos dados

### 7. Reflection
- Leitura de annotations @InfoAutor em tempo de execução
- Descoberta automática de classes anotadas
- Informações do desenvolvedor

### 8. Tratamento de Exceções
- Teste de regras de negócio
- Captura e tratamento de exceções customizadas
- Validações: título vazio, ano inválido, edição inválida

## Exemplo de Saída

```
=== BOOKTREE - BIBLIOTECA DIGITAL ===

1. ADICIONANDO PUBLICACOES:
  Adicionado: [Livro] Dom Casmurro - Machado de Assis (1899)
  Adicionado: [Livro] O Cortico - Aluisio Azevedo (1890)
  Adicionado: [Revista] Veja - Editora Abril (2023)
  Adicionado: [Revista] Epoca - Editora Globo (2022)

2. BUSCA NO REPOSITORIO:
Buscando publicacao com titulo: 'Dom Casmurro'
Resultado: [Livro] Dom Casmurro - Machado de Assis (1899)

3. ARVORE AVL - TRAVESSIA EM ORDEM:
Publicacoes ordenadas alfabeticamente por titulo:
  [Livro] Dom Casmurro - Machado de Assis (1899)
  [Revista] Epoca - Editora Globo (2022)
  [Livro] O Cortico - Aluisio Azevedo (1890)
  [Revista] Veja - Editora Abril (2023)

4. ALGORITMO MERGESORT:
Ordenando publicacoes por ano de publicacao:
  [Livro] O Cortico - Aluisio Azevedo (1890)
  [Livro] Dom Casmurro - Machado de Assis (1899)
  [Revista] Epoca - Editora Globo (2022)
  [Revista] Veja - Editora Abril (2023)

7. REFLECTION - LEITURA DE ANOTACOES:
Classe: Livro
  Autor: Fernanda Nascimento
  Data: 02/12/2025

Classe: RepositorioHash
  Autor: Fernanda Nascimento
  Data: 02/12/2025

8. TRATAMENTO DE EXCECOES DE NEGOCIO:
  Testando titulo vazio...
  ✓ Excecao capturada: Titulo nao pode ser vazio.
  Testando ano invalido (1400)...
  ✓ Excecao capturada: Ano deve ser maior ou igual a 1500.
```

## Informações Acadêmicas

**Autor:** Fernanda Nascimento - 02/12/2025  
**Disciplina:** Linguagem de Programação II  
**Professor:** Alan de Oliveira Santana  
**Instituição:** Universidade Federal do Rio Grande do Norte  
**Curso:** Bacharelado em Tecnologia da Informação

---

*Projeto desenvolvido seguindo todos os requisitos obrigatórios e demonstrando integração completa entre estruturas de dados, algoritmos e POO avançada.*
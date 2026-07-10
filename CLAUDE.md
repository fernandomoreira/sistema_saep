# CLAUDE.md — Memória Técnica do Projeto SAEP

## Contexto Geral

Este projeto é o sistema de avaliação prática **SAEP (Sistema de Avaliação de Estudantes na Prática)** do **SENAI**, aplicado à turma **G96229** do curso **Técnico em Desenvolvimento de Sistemas**.

- **Avaliador:** Linaldo da Exaltacao Santos
- **Data da prova:** 30/06/2026
- **Total de alunos:** 29
- **Prova aplicada:** `prova.pdf` — Sistema de Almoxarifado (Warehouse Management)

---

## Estrutura de Arquivos

```
Prova_saep/
├── CLAUDE.md                        ← este arquivo
├── alunos.md                        ← lista dos 29 alunos (ordem alfabética)
├── prova.pdf                        ← enunciado da prova
├── avaliacoes_saep.json             ← banco de dados das avaliações (todos os 29 alunos)
├── SAEP01-Anderson Luciano...pdf    ← modelo do formulário Lista de Verificação
├── SAEP02-Anderson Luciano...pdf    ← modelo do formulário Lista de Verificação por Atividade
├── formulario-saep/
│   ├── init-dados.html              ← ABRIR NO NAVEGADOR PRIMEIRO para carregar os dados
│   ├── index.html                   ← página inicial dos formulários
│   ├── saep01/
│   │   ├── index.html
│   │   ├── script.js
│   │   └── style.css
│   └── saep02/
│       ├── index.html
│       ├── script.js
│       └── style.css
└── <pasta de cada aluno>/           ← arquivos submetidos por cada aluno
    └── _extraido_*/                 ← conteúdo extraído dos ZIPs
```

---

## Como Usar os Formulários HTML

1. Abrir `formulario-saep/init-dados.html` no navegador
2. Clicar em **"Inicializar dados"** → carrega todos os 29 alunos no localStorage (SAEP01 + SAEP02)
3. Abrir `formulario-saep/saep01/index.html` ou `saep02/index.html`
4. Selecionar o aluno no dropdown → respostas aparecem preenchidas
5. Usar os botões da barra de ações (disponível no **topo** e no **rodapé** da página):
   - **Salvar avaliação** — persiste no localStorage
   - **Limpar respostas do aluno** — apaga as respostas na tela
   - **Imprimir / Exportar PDF** — imprime o formulário completo (usa nome do aluno como nome do arquivo)
   - **Imprimir Relatório Simplificado** *(SAEP01 apenas)* — abre janela limpa só com os itens relevantes e justificativas legíveis

> Os formulários usam **localStorage** do navegador. Os dados persistem enquanto não forem limpos.
> Se precisar recarregar, basta abrir `init-dados.html` novamente e clicar no botão.

### Chaves do localStorage
- `saep_alunos` → array `[{nome, cpf}]` dos 29 alunos (compartilhado entre SAEP01 e SAEP02)
- `saep_respostas_<cpf>` → respostas SAEP01 de cada aluno
- `saep02_respostas_<cpf>` → respostas SAEP02 de cada aluno

### Relatório Simplificado (SAEP01)
Abre uma nova aba com layout limpo (sem cores, sem tabelas) mostrando:
- `✓ SIM` — o item que o aluno atingiu em cada atividade
- `✗ NÃO ↳ justificativa` — apenas os itens acima do nível atingido, com a justificativa real
- Omite: itens S6/S7, "Nível inferior ao alcançado", "Procedimento não localizado"
- Nome do aluno é usado como título do PDF

---

## Estrutura do Banco de Dados (`avaliacoes_saep.json`)

```json
{
  "avaliador": "Linaldo da Exaltacao Santos",
  "turma": "G96229",
  "curso": "TÉCNICO EM DESENVOLVIMENTO DE SISTEMAS",
  "data_avaliacao": "30/06/2026",
  "alunos": [
    {
      "nome": "Nome do Aluno",
      "cpf": "",
      "A01": {"nivel": 4, "justificativa": "..."},
      "A02": {"nivel": "S6", "justificativa": "..."},
      ...
      "A11": {"nivel": 3, "justificativa": "..."}
    }
  ]
}
```

### Escala de Níveis
| Nível | Significado |
|-------|-------------|
| `"S6"` | Não realizou (por dificuldade ou desistência) |
| `"S7"` | Tempo esgotado antes de iniciar |
| `0`    | Entregou mas sem funcionalidade válida |
| `1`    | Implementação com erros estruturais graves |
| `2`    | Implementação parcial, sem validações/boas práticas |
| `3`    | Implementação funcional mas incompleta (faltam detalhes) |
| `4`    | Implementação completa conforme todos os critérios |

---

## Atividades Avaliadas (A01–A11)

| Código | Atividade | Tecnologia avaliada |
|--------|-----------|---------------------|
| A01 | Script SQL de criação do banco + dados | SQL DDL + DML |
| A02 | View `vw_estoque` (quantidade × valor_unitário = valor_total) | SQL CREATE VIEW |
| A03 | Diagrama Entidade-Relacionamento (DER) | brModelo / PNG |
| A04 | Listar valor total por categoria | Backend (Python/Java) |
| A05 | Listar todos os produtos cadastrados (6 campos) | Backend |
| A06 | Cadastrar novo produto (com validações) | Backend |
| A07 | Listar saídas em ordem decrescente de data (DD-MM-YYYY) | Backend |
| A08 | Registrar entrada no estoque (atualizar saldo, data DD-MM-YYYY) | Backend |
| A09 | Relatório de entrada e saída por período (7 campos) | Backend |
| A10 | Produto com maior volume de saída (3 campos, ordenado DESC) | Backend |
| A11 | Produtos em nível mínimo/máximo de estoque (4 campos + percentual) | Backend |

### Critérios-chave do sistema avaliado (Almoxarifado)
- **Entidades mínimas:** Categoria, Produto, Entrada/Movimentação, (Usuário opcional)
- **Produto deve ter:** nome, categoria, quantidade, valor_unitário, unidade_medida, limite_min (0), limite_max (100)
- **View vw_estoque:** mínimo 5 colunas incluindo id, nome, valor_unitário, quantidade, valor_total
- **A09 exige 7 campos:** nome_produto, unidade_medida, total_entradas, total_saidas, saldo_periodo, valor_financeiro_entradas, valor_financeiro_saidas
- **A10 exige 3 campos:** nome_produto, quantidade_total_saida, valor_total_financeiro_saidas
- **A11 exige 4 campos + percentual:** nome_produto, quantidade, limite_min, limite_max, percentual_nivel_atingido
- **Formato de data obrigatório:** `DD-MM-YYYY` (ex: 25-12-2025)

---

## Mapeamento nível → item do checklist (SAEP01 e SAEP02)

### Regra comum a ambos os formulários
Os itens S6 ("O aluno optou pela não realização") e S7 ("O tempo finalizou antes de começar") são **sempre marcados como Não, sem justificativa**, independente do nível do aluno.

### SAEP02 — índices por atividade (0–6)

| Índice | Descrição | Comportamento |
|--------|-----------|---------------|
| 0 | S6 — não realizou | Sempre **Não**, sem justificativa |
| 1 | S7 — tempo esgotado | Sempre **Não**, sem justificativa |
| 2 | Nível 0 — sem funcionalidade | Sim/Não conforme nível do aluno |
| 3 | Nível 1 — erros estruturais | Sim/Não conforme nível do aluno |
| 4 | Nível 2 — implementação parcial | Sim/Não conforme nível do aluno |
| 5 | Nível 3 — funcional incompleto | Sim/Não conforme nível do aluno |
| 6 | Nível 4 — implementação completa | Sim/Não conforme nível do aluno |

**Padrão de justificativas SAEP02 (igual ao SAEP01):**
- Aluno S6/S7 → itens 2–6: **Não** com `"Procedimento não localizado"`
- Aluno nível N → itens abaixo de N: **Não** com `"Nível inferior ao alcançado pelo aluno"`
- Aluno nível N → itens acima de N: **Não** com a justificativa real do aluno
- Aluno nível N → item exato N: **Sim**, sem justificativa

### SAEP01 — lógica de justificativas nos itens "Não"

| Situação | Justificativa |
|----------|---------------|
| Item S6 ou S7 (qualquer aluno) | Sempre **Não**, sem justificativa |
| Aluno S6/S7 → itens de nível (0–4) | **Não** — `"Procedimento não localizado"` |
| Aluno nível N → itens abaixo de N | **Não** — `"Nível inferior ao alcançado pelo aluno"` |
| Aluno nível N → itens acima de N | **Não** — justificativa real do aluno (por que não atingiu) |

---

## Lista dos 29 Alunos e Resumo das Avaliações

| # | Nome | A01 | A02 | A03 | A04 | A05 | A06 | A07 | A08 | A09 | A10 | A11 |
|---|------|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|
| 1 | Anderson Luciano Dos Santos Junior | S6 | S6 | S6 | 2 | 3 | 3 | 3 | 3 | 4 | 4 | 2 |
| 2 | Cauã Costa Sena | 4 | 4 | 3 | S6 | 2 | 3 | S6 | 3 | S6 | S6 | S6 |
| 3 | Cauã Santos Silva | 3 | S6 | 3 | S6 | 2 | 3 | S6 | S6 | S6 | S6 | S6 |
| 4 | Davi Asafe Falcão Guimarães | 3 | S6 | S6 | 2 | 2 | 3 | 3 | 3 | S6 | S6 | S6 |
| 5 | David Oliveira dos Santos Caldas | 4 | 4 | 3 | 4 | 4 | 4 | 3 | 3 | 4 | 4 | 4 |
| 6 | Eric Felix de Almaço Parente | 3 | S6 | 3 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 |
| 7 | Filipe Cauã da Silva Souza | 0 | S6 | 3 | S6 | 3 | 2 | 2 | 3 | S6 | S6 | S6 |
| 8 | Gabriel Almeida Lima Macedo | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 |
| 9 | Gabriel Araújo Bitencourt | 3 | 4 | 3 | 3 | S6 | S6 | S6 | 2 | 3 | 4 | S6 |
| 10 | Gabriel Santiago Moura | 0 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 |
| 11 | Gabriel Santos Santana | 3 | S6 | 3 | S6 | 3 | 2 | 3 | 3 | S6 | S6 | S6 |
| 12 | Giselle Mota Lima | 2 | S6 | 2 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 |
| 13 | Isaac Newton Rodrigues Santana | 3 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 |
| 14 | Isaac Salomão Carmo | 4 | 4 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 |
| 15 | Ítalo Brito dos Santos | 4 | 3 | 4 | S6 | 2 | S6 | 2 | S6 | S6 | S6 | S6 |
| 16 | João Victor Souza Alves dos Santos | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 |
| 17 | João Vitor Pereira Da Rocha | 4 | 3 | S6 | 3 | 3 | 4 | 3 | 3 | 4 | 4 | 2 |
| 18 | Juliana Costa Silva | 2 | 4 | S6 | S6 | 3 | 2 | 1 | 3 | S6 | S6 | S6 |
| 19 | Kauã Batista Neves | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 |
| 20 | Kauan Gabriel Souza Dias dos Santos | 4 | 4 | S6 | 4 | 4 | 4 | 3 | 3 | 4 | 4 | 4 |
| 21 | Levi Ferreira de Melo | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 |
| 22 | Luis Felipe da Silva Paixão | 3 | S6 | S6 | 4 | 3 | 4 | 3 | 3 | 1 | 3 | 3 |
| 23 | Marcos Paulo Oliveira Silva | 4 | 3 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 |
| 24 | Mário Augusto Mutti Hohenfeld | S6 | S6 | 2 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 |
| 25 | Miguel Estrela dos Santos | 4 | 4 | S6 | 0 | 2 | 1 | S6 | 1 | S6 | S6 | S6 |
| 26 | Pablo Guimarães Santos Sousa | 4 | 4 | 4 | 4 | 3 | 4 | 3 | 3 | 4 | 4 | 4 |
| 27 | Rodrigo Gentili Habib | 1 | S6 | 1 | S6 | S6 | S6 | S6 | S6 | S6 | S6 | S6 |
| 28 | Samara dos Santos Cerqueira | 3 | S6 | S6 | 3 | 3 | 3 | 3 | 2 | 2 | 3 | 4 |
| 29 | Vener Gomes da Conceição Santos | 4 | 4 | S6 | 3 | 4 | 3 | 3 | 3 | 4 | 4 | 4 |

---

## CPFs Usados no Formulário

Anderson tem CPF real (`086.320.535-66`). Os demais usam CPFs fictícios sequenciais:
- Aluno #2 a #29: `000.000.00X-XX` onde X = índice (02 a 29)

---

## Observações Técnicas por Aluno

### Alunos que não submeteram nada
- **Gabriel Almeida Lima Macedo** — pasta vazia
- **João Victor Souza Alves dos Santos** — pasta vazia
- **Kauã Batista Neves** — pasta vazia
- **Levi Ferreira de Melo** — pasta vazia

### Problemas de extração de arquivos
- **Marcos Paulo Oliveira Silva** — ZIP do backend corrompido (path too long no Windows). Apenas `script.sql` avaliado.
- **Gabriel Santiago Moura** — ZIP extraído mas `banco.sql` continha apenas `SELECT * FROM almoxarifado.vw_estoque;`

### Tecnologias utilizadas pelos alunos
- **Python + SQLite/MySQL (console):** Anderson, Davi, Filipe, Gabriel Santiago, Kauan, Luis Felipe, Vener
- **Java Spring Boot (REST API):** Cauã Costa, Cauã Santos, David, Gabriel Araújo, Gabriel Santos, Isaac Newton, João Vitor, Juliana, Marcos, Miguel, Pablo, Samara
- **Java console (in-memory):** Isaac Newton (Swing/NetBeans templates), Luis Felipe
- **Apenas SQL (sem backend):** Eric, Isaac Salomão, Ítalo
- **Apenas DER + planilha:** Mário Augusto

### Arquivos `.brM3`
Arquivos do software **brModelo** — formato binário, não legível como texto. Quando presentes sem imagem exportada (PNG), o DER foi inferido pela estrutura SQL. Ítalo Brito submeteu PNGs legíveis do DER e recebeu nível 4 em A03.

### Formato de data
A maioria dos alunos perdeu nota em A07 e A08 por usar `yyyy-MM-dd` (ISO padrão Java/Python) em vez do exigido `DD-MM-YYYY`. Nenhum aluno atingiu nível 4 em A07 ou A08.

### View `vw_estoque` — nomes alternativos encontrados
- **Juliana:** `v_relatorio_estoque` (nome diferente, mas funcionalidade equivalente) → nível 4 mantido
- **Isaac Salomão:** view correta criada em arquivos separados (5.sql e 6.sql)

---

## Notas para Futuras Sessões

- O `avaliacoes_saep.json` é a fonte de verdade de todas as avaliações
- O `init-dados.html` inicializa **ambos** os formulários (SAEP01 e SAEP02) de uma só vez — precisa ser reaberto se o localStorage for limpo
- Para adicionar/corrigir a avaliação de um aluno, editar diretamente o `avaliacoes_saep.json` **e** a constante `ALUNOS` dentro de `init-dados.html` (as duas fontes precisam estar em sincronia)
- Ambos os formulários possuem barra de ações no **topo e no rodapé** da página (SAEP01) — salvar, limpar e imprimir acessíveis sem rolar a página
- O botão **"Imprimir Relatório Simplificado"** existe apenas no SAEP01 — para SAEP02 usar o botão padrão de impressão
- O **nome do aluno selecionado** é usado como título do documento ao exportar PDF (em ambos os formulários)
- O padrão de justificativas é **idêntico** nos dois formulários: S6/S7 sempre Não sem texto; abaixo do nível → `"Nível inferior ao alcançado pelo aluno"`; acima do nível → justificativa real; S6/S7 do aluno → `"Procedimento não localizado"`

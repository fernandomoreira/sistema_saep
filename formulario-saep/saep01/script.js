// ---------------------------------------------------------------------------
// Dados do checklist (extraídos do formulário SAEP01 - Lista de Verificação)
// ---------------------------------------------------------------------------
const CHECKLIST = [
  {
    unidade: "UNIDADE 1 - PROGRAMAR SISTEMAS COMPUTACIONAIS, ATENDENDO NORMAS E PADRÃO DE QUALIDADE, USABILIDADE, ROBUSTEZ, INTEGRIDADE E SEGURANÇA.",
    elemento: "ELEMENTO 1.1 - REALIZAR INTERAÇÃO COM BANCO DE DADOS",
    grupos: [
      {
        titulo: "1.1.2 - Seguindo procedimento de modelagem de dados",
        itens: [
          { cod: "1.1.2.1", texto: "A03_00_C4+S3_0_A modelagem apresenta registros de classes e atributos incompatíveis com os requisitos funcionais e a regra de negócio." },
          { cod: "1.1.2.2", texto: "A03_01_C4+S3_1_O Diagrama Entidade-Relacionamento (DER) contempla 1 elementos obrigatório: entidades, atributos, identificador com auto-incremento, chaves primárias, chaves estrangeiras e cardinalidade dos relacionamentos." },
          { cod: "1.1.2.3", texto: "A03_02_C4+S3_2_O Diagrama Entidade-Relacionamento (DER) contempla, no mínimo, 2 dos 6 elementos obrigatórios: entidades, atributos, identificador com auto-incremento, chaves primárias, chaves estrangeiras e cardinalidade dos relacionamentos." },
          { cod: "1.1.2.4", texto: "A03_03_C4+S3_3_O Diagrama Entidade-Relacionamento (DER) contempla, no mínimo, 4 dos 6 elementos obrigatórios: entidades, atributos, identificador com auto-incremento, chaves primárias, chaves estrangeiras e cardinalidade dos relacionamentos, com representação da estrutura principal dos dados do sistema." },
          { cod: "1.1.2.5", texto: "A03__0_S6_0_O aluno apresentou dificuldade para realizar a prova e/ou optou pela não realização da atividade." },
          { cod: "1.1.2.6", texto: "A03__0_S7_0_O tempo de prova finalizou antes de o aluno começar a atividade." },
          { cod: "1.1.2.7", texto: "A03_04_C4+S3_4_O Diagrama Entidade-Relacionamento (DER) contempla os 6 elementos obrigatórios: entidades, atributos, identificador com auto-incremento, chaves primárias, chaves estrangeiras e cardinalidade dos relacionamentos, estruturando os dados conforme as regras de negócio previstas para o sistema, incluindo registro de movimentações e vínculo do responsável pela operação." }
        ]
      },
      {
        titulo: "1.1.3 - Seguindo procedimentos de normalização e padronização de dados",
        itens: [
          { cod: "1.1.3.1", texto: "A02_00_C4+S4_0_A view vw_estoque implementada não apresenta qualquer estrutura que permita a apuração do valor total por produto." },
          { cod: "1.1.3.2", texto: "A02_01_C4+S4_1_A view vw_estoque foi implementada apresentando erros de cálculo que inviabilizam a apuração do valor total por produto." },
          { cod: "1.1.3.3", texto: "A02_02_C4+S4_2_A view vw_estoque foi implementada, realizando o cálculo do valor total por item de produto, com base na quantidade registrada e no valor unitário, e/ou retornando uma tabela com apenas duas colunas: com o valor total sendo obrigatório, e as colunas opcionais: Identificador, valor unitário, quantidade e denominação (nome)." },
          { cod: "1.1.3.4", texto: "A02_03_C4+S4_3_A view vw_estoque foi implementada, realizando o cálculo do valor total por item de produto, com base na quantidade registrada e no valor unitário, retornando uma tabela com 3 ou 4 colunas: com o valor total, valor unitário e denominação (nome) sendo obrigatório, e a coluna quantidade fica opcional." },
          { cod: "1.1.3.5", texto: "A02__0_S6_0_O aluno apresentou dificuldade para realizar a prova e optou pela não realização." },
          { cod: "1.1.3.6", texto: "A02__0_S7_0_O tempo de prova finalizou antes de o aluno começar a atividade." },
          { cod: "1.1.3.7", texto: "A02_04_C4+S4_4_A view vw_estoque foi implementada, realizando o cálculo do valor total por item de produto, com base na quantidade registrada e no valor unitário, retornando uma tabela com, no mínimo, 5 colunas: Identificador, valor unitário, valor total, quantidade e denominação (nome)." }
        ]
      },
      {
        titulo: "1.1.5 - Utilizando linguagem de definição e manipulação de dados de acordo com as especificações técnicas",
        itens: [
          { cod: "1.1.5.1", texto: "A01_00_C4+S3+S6_0_O script criado apresenta código que inviabiliza a compilação da linguagem." },
          { cod: "1.1.5.2", texto: "A01_01_C4+S3+S6_1_O script do banco de dados foi compilado, porém apresenta erros estruturais, como inconsistências em tipos de dados, chaves primárias ou estrangeiras, que inviabilizam o funcionamento do banco e o atendimento a todas as regras de negócio." },
          { cod: "1.1.5.3", texto: "A01_02_C4+S3+S6_2_O script do banco de dados foi implementado de acordo com as regras de negócio, respeitando os tipos de dados, porém sem implementar as chaves primárias e estrangeiras considerando com as boas práticas." },
          { cod: "1.1.5.4", texto: "A01_03_C4+S3+S6_3_O script do banco de dados foi implementado de acordo com as regras de negócio, respeitando os tipos de dados, chaves primárias e estrangeiras, porém sem implementação e inserção dos três registros mínimos." },
          { cod: "1.1.5.5", texto: "A01_04_C4+S3+S6_4_O script do banco de dados foi implementado de acordo com as regras de negócio, contendo todas as tabelas com, no mínimo, três registros cada, respeitando os tipos de dados, chaves primárias e estrangeiras." },
          { cod: "1.1.5.6", texto: "A01__0_S6_0_O aluno apresentou dificuldade para realizar a prova e optou pela não realização." },
          { cod: "1.1.5.7", texto: "A01__0_S7_0_O tempo de prova finalizou antes de o aluno começar a atividade." }
        ]
      }
    ]
  },
  {
    unidade: "UNIDADE 1 - PROGRAMAR SISTEMAS COMPUTACIONAIS, ATENDENDO NORMAS E PADRÃO DE QUALIDADE, USABILIDADE, ROBUSTEZ, INTEGRIDADE E SEGURANÇA.",
    elemento: "ELEMENTO 1.2 - CODIFICAR PROGRAMAS",
    grupos: [
      {
        titulo: "1.2.2 - Utilizando linguagens de programação (lógica de programação)",
        itens: [
          { cod: "1.2.2.1", texto: "A04_00_C3+S3_0_A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade." },
          { cod: "1.2.2.2", texto: "A04_01_C3+S3_1_A aplicação apresenta implementação da funcionalidade, porém com erros de lógica e estrutura que impedem a execução do cálculo e da listagem do valor total por item de produto." },
          { cod: "1.2.2.3", texto: "A04_02_C3+S3_2_A aplicação apresenta uma função que realiza o cálculo do valor total por item de produto, utilizando comandos SQL e lógica diretamente no código, sem o uso de view, ou utiliza a view para realizar o cálculo retornando um resultado errado." },
          { cod: "1.2.2.4", texto: "A04_03_C3+S3_3_A aplicação apresenta uma função que lista o valor total por item de produto, utilizando consulta a view. No entanto, faltam informações do produto, há inconsistências no código da função." },
          { cod: "1.2.2.5", texto: "A05_00_C3+S4_0_A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade." },
          { cod: "1.2.2.5", texto: "A06_00_C3+S3_0_A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade." },
          { cod: "1.2.2.6", texto: "A06_01_C3+S3_1_A aplicação apresenta implementação da função de cadastro, porém com falhas de lógica e estrutura que impedem a inserção do produto no banco de dados." },
          { cod: "1.2.2.6", texto: "A05_01_C3+S4_1_A aplicação apresenta tentativa de implementação da função de cadastro de produtos, porém com erros de lógica e estrutura que impedem a execução da operação em desacordo com as regras definidas." },
          { cod: "1.2.2.7", texto: "A06_02_C3+S3_2_A função de cadastro de novo produto está implementada conforme as regras de negócio, porém a função está sem os critérios de validação (valor unitário, quantidade e denominação de entrada e saída, duplicidade) e/ou tratamento de erros." },
          { cod: "1.2.2.7", texto: "A05_02_C3+S4_2_A função permite a listagem de todos os produtos cadastrado, contemplando, no mínimo 3 campos previstos que são considerados principais: nome do produto, quantidade em estoque e valor unitário, porém apresenta falhas relacionadas às validações, ao tratamento de erros e à padronização do código." },
          { cod: "1.2.2.8", texto: "A06_03_C3+S3_3_A função de cadastro está implementada conforme as regras de negócio, possui as validações (valor unitário, quantidade e denominação) de entrada e saída, porém sem o tratamento de erros." },
          { cod: "1.2.2.8", texto: "A05_03_C3+S4_3_A função realiza a listagem de todos os produtos cadastrados de forma funcional, contemplando, no mínimo 4 campos previstos que são considerados principais: nome do produto, unidade de medida, quantidade em estoque e valor unitário, porém apresenta falhas relacionadas às validações, ao tratamento de erros e à padronização do código." },
          { cod: "1.2.2.9", texto: "A07_00_C3+S3_0_A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade." },
          { cod: "1.2.2.10", texto: "A07_01_C3+S3_1_A aplicação apresenta a função de listagem de saídas de produtos implementada com falhas de lógica e estrutura que inviabilizam o retorno dos registros em ordem decrescente por data, comprometendo a consulta das informações previstas nas regras de negócio." },
          { cod: "1.2.2.11", texto: "A07_02_C3+S3_2_A função retorna todas as saídas de produtos, contemplando, no mínimo 2 dos 6 campos (nome do produto, unidade de medida, quantidade em estoque, valor unitário, limite máximo e mínimo), porém não está em ordem decrescente por data e/ou a formatação não está de acordo com o padrão: exemplo da formatação: 25-12-2025 16:50." },
          { cod: "1.2.2.12", texto: "A07_03_C3+S3_3_A função retorna todas as saídas de produtos, em ordem decrescente por data, contemplando, no mínimo 4 campos: nome do produto, unidade de medida, quantidade em estoque e valor unitário, porém a formatação não está de acordo com o padrão: exemplo da formatação: 25-12-2025." },
          { cod: "1.2.2.13", texto: "A08_00_C3+S3_0_A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade." },
          { cod: "1.2.2.14", texto: "A08_01_C3+S3_1_A aplicação apresenta função de entrada de itens no estoque, porém a implementação contém erros de lógica e estrutura que impedem o registro da entrada conforme as regras de negócio definidas." },
          { cod: "1.2.2.15", texto: "A08_02_C3+S3_2_A função realiza o registro de entrada e atualiza o saldo em estoque, porém apresenta erro na fórmula do cálculo dessa atualização. Além disso, efetua a integração das informações com o banco de dados, mas a data não está padronizada no formato 25-12-2025." },
          { cod: "1.2.2.16", texto: "A08_03_C3+S3_3_A função realiza o registro de entrada e atualiza automaticamente as quantidades dos itens com base no saldo atual em estoque, efetuando a integração das informações com o banco de dados, porém a data não está padronizada no formato 25-12-2025." },
          { cod: "1.2.2.17", texto: "A09_00_C3+S4_0_A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade." },
          { cod: "1.2.2.18", texto: "A09_01_C3+S4_1_A aplicação apresenta função de relatório por período, porém a implementação contém erros de lógica que impedem o retorno dos dados de entrada e saída no intervalo informado e erro da fórmula de cálculo do valor total financeiro de entrada e/ou saída." },
          { cod: "1.2.2.19", texto: "A09_02_C3+S4_2_A função emite o relatório de entradas e saídas no período informado, com cálculos precisos, contendo até 3 campos obrigatórios, conforme sequência: nome do produto, unidade de medidas e total de entradas." },
          { cod: "1.2.2.20", texto: "A09_03_C3+S4_3_A função emite o relatório de entradas e saídas no período informado, com cálculos precisos, contendo de 4 a 6 campos obrigatórios, conforme sequência: nome do produto, unidade de medidas, total de entradas, total de saídas, saldo no período e valor total financeiro das entradas." },
          { cod: "1.2.2.21", texto: "A11_00_C3+S4_0_A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade." },
          { cod: "1.2.2.22", texto: "A11_01_C3+S4_1_A aplicação apresenta função de verificação de níveis de estoque, porém a implementação contém erros de lógica que impedem a identificação de produtos nos níveis mínimo ou máximo de estoque." },
          { cod: "1.2.2.23", texto: "A11_02_C3+S4_2_A função retorna produtos com informação de estoque, apresentando os 3 campos de referência, sendo obrigatório o campo nome do produto, e o limite mínimo e limite máximo de estoque." },
          { cod: "1.2.2.24", texto: "A11_03_C3+S4_3_A função retorna produtos com informação de estoque, apresentando os 4 campos de referência, sendo obrigatório o campo nome do produto, quantidade em estoque, limite mínimo e limite máximo de estoque." },
          { cod: "1.2.2.25", texto: "A10_00_C3+S4_0_A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade." },
          { cod: "1.2.2.26", texto: "A10_01_C3+S4_1_A aplicação apresenta função para identificar produtos com maior quantidade de saída no período, porém a implementação contém erros de lógica que impedem o retorno dos produtos, das quantidades no intervalo informado e/ou ordenação." },
          { cod: "1.2.2.27", texto: "A10_02_C3+S4_2_A função retorna os produtos com maior quantidade de saída no período informado, apresentando, com agrupamento por produto e ordenação decrescente pela quantidade total de saída, 1 campo com fórmula de cálculo preciso dos 3 campos obrigatórios, e na sequência: nome do produto, quantidade total de saída e valor total financeiro das saídas." },
          { cod: "1.2.2.28", texto: "A10_03_C3+S4_3_A função retorna os produtos com maior quantidade de saída no período informado, apresentando, com agrupamento por produto e ordenação decrescente pela quantidade total de saída, 2 campos com fórmula de cálculo preciso dos 3 campos obrigatórios, e na sequência: nome do produto, quantidade total de saída e valor total financeiro das saídas." },
          { cod: "1.2.2.33", texto: "A04__0_S6_0_O aluno apresentou dificuldade para realizar a prova e optou pela não realização." },
          { cod: "1.2.2.34", texto: "A04__0_S7_0_O tempo de prova finalizou antes de o aluno começar a atividade." },
          { cod: "1.2.2.35", texto: "A06__0_S6_0_O aluno apresentou dificuldade para realizar a prova e optou pela não realização." },
          { cod: "1.2.2.36", texto: "A06__0_S7_0_O tempo de prova finalizou antes de o aluno começar a atividade." },
          { cod: "1.2.2.37", texto: "A05__0_S6_0_O aluno apresentou dificuldade para realizar a prova e optou pela não realização." },
          { cod: "1.2.2.38", texto: "A05__0_S7_0_O tempo de prova finalizou antes de o aluno começar a atividade." },
          { cod: "1.2.2.39", texto: "A07__0_S6_0_O aluno apresentou dificuldade para realizar a prova e optou pela não realização." },
          { cod: "1.2.2.40", texto: "A07__0_S7_0_O tempo de prova finalizou antes de o aluno começar a atividade." },
          { cod: "1.2.2.41", texto: "A08__0_S6_0_O aluno apresentou dificuldade para realizar a prova e optou pela não realização." },
          { cod: "1.2.2.42", texto: "A08__0_S7_0_O tempo de prova finalizou antes de o aluno começar a atividade." },
          { cod: "1.2.2.43", texto: "A09__0_S6_0_O aluno apresentou dificuldade para realizar a prova e optou pela não realização." },
          { cod: "1.2.2.44", texto: "A09__0_S7_0_O tempo de prova finalizou antes de o aluno começar a atividade." },
          { cod: "1.2.2.45", texto: "A11__0_S6_0_O aluno apresentou dificuldade para realizar a prova e optou pela não realização." },
          { cod: "1.2.2.46", texto: "A11__0_S7_0_O tempo de prova finalizou antes de o aluno começar a atividade." },
          { cod: "1.2.2.47", texto: "A10__0_S6_0_O aluno apresentou dificuldade para realizar a prova e optou pela não realização." },
          { cod: "1.2.2.48", texto: "A10__0_S7_0_O tempo de prova finalizou antes de o aluno começar a atividade." },
          { cod: "1.2.2.49", texto: "A04_03_C3+S3_3_A aplicação apresenta uma função que lista o valor total por item de produto, utilizando consulta a view. No entanto, faltam informações do produto, há inconsistências no código da função." },
          { cod: "1.2.2.50", texto: "A05_04_C3+S4_4_A função implementa a listagem de todos os produtos, contemplando todos os 6 campos previstos e suas validações (nome do produto, unidade de medida, quantidade em estoque e valor unitário, limite máxima e mínimo), os dados, integração com o banco de dados e código padronizado, atendendo as regras de negócio." },
          { cod: "1.2.2.51", texto: "A06_04_C3+S3_4_A função de cadastro está implementada conforme as regras de negócio, possui as validações (valor unitário, quantidade e denominação) de entrada e saída, verificação contra duplicidade e tratamento de erros." },
          { cod: "1.2.2.52", texto: "A07_04_C3+S3_4_A função retorna todas as saídas de produtos, padronizadas em ordem decrescente por data, com todas as informações dos 6 campos (nome do produto, unidade de medida, quantidade em estoque, valor unitário, limite máxima e mínimo). (exemplo da formatação: 25-12-2025)." },
          { cod: "1.2.2.53", texto: "A08_04_C3+S3_4_A função realiza a entrada e atualiza as quantidades de itens de acordo com o saldo em estoque atual, emitindo a data padronizada conforme a formatação: 25-12-2025 e realiza a integração com o banco de dados." },
          { cod: "1.2.2.54", texto: "A09_04_C3+S4_4_A função emite o relatório de entradas e saídas no período informado, contendo 7 campos: nome do produto, unidade de medidas, total de entradas, total de saídas, saldo no período, valor total financeiro das entradas e valor total financeiro de saídas." },
          { cod: "1.2.2.55", texto: "A11_04_C3+S4_4_A função retorna produtos com informação de estoque, apresentando os 4 campos de referência, sendo obrigatório o campo nome do produto, quantidade em estoque, limite mínimo e limite máximo de estoque, com a identificação do percentual de nível atingido." },
          { cod: "1.2.2.56", texto: "A10_04_C3+S4_4_A função retorna os produtos com maior quantidade de saída no período informado, apresentando, com agrupamento por produto e ordenação decrescente pela quantidade total de saída, os 3 campos obrigatórios com fórmula de cálculo preciso e na sequência: nome do produto, quantidade total de saída e valor total financeiro das saídas." }
        ]
      }
    ]
  }
];

// ---------------------------------------------------------------------------
// Persistência (localStorage)
// ---------------------------------------------------------------------------
const LS_ALUNOS = "saep_alunos";
const LS_RESPOSTAS_PREFIX = "saep_respostas_";

const ALUNO_PADRAO = {
  nome: "Anderson Luciano Dos Santos Junior",
  cpf: "086.320.535-66"
};

function carregarAlunos() {
  const raw = localStorage.getItem(LS_ALUNOS);
  if (raw) {
    try {
      const lista = JSON.parse(raw);
      if (Array.isArray(lista) && lista.length) return lista;
    } catch (e) { /* ignora e recria */ }
  }
  const inicial = [ALUNO_PADRAO];
  localStorage.setItem(LS_ALUNOS, JSON.stringify(inicial));
  return inicial;
}

function salvarAlunos(lista) {
  localStorage.setItem(LS_ALUNOS, JSON.stringify(lista));
}

function chaveRespostas(cpf) {
  return LS_RESPOSTAS_PREFIX + cpf;
}

function carregarRespostas(cpf) {
  const raw = localStorage.getItem(chaveRespostas(cpf));
  if (!raw) return null;
  try { return JSON.parse(raw); } catch (e) { return null; }
}

function salvarRespostas(cpf, dados) {
  localStorage.setItem(chaveRespostas(cpf), JSON.stringify(dados));
}

// ---------------------------------------------------------------------------
// Estado
// ---------------------------------------------------------------------------
let alunos = carregarAlunos();

// ---------------------------------------------------------------------------
// Elementos do DOM
// ---------------------------------------------------------------------------
const alunoSelect = document.getElementById("alunoSelect");
const alunoCpf = document.getElementById("alunoCpf");
const novoAlunoNome = document.getElementById("novoAlunoNome");
const novoAlunoCpf = document.getElementById("novoAlunoCpf");
const btnAddAluno = document.getElementById("btnAddAluno");
const btnRemoveAluno = document.getElementById("btnRemoveAluno");
const avaliadorInput = document.getElementById("avaliador");
const cursoInput = document.getElementById("curso");
const dataInput = document.getElementById("dataAvaliacao");
const horaInput = document.getElementById("horaAvaliacao");
const edicaoInput = document.getElementById("edicao");
const checklistContainer = document.getElementById("checklistContainer");
const btnSalvar = document.getElementById("btnSalvar");
const btnLimpar = document.getElementById("btnLimpar");
const btnImprimir = document.getElementById("btnImprimir");
const btnImprimirSimples = document.getElementById("btnImprimirSimples");
const statusSalvo = document.getElementById("statusSalvo");
const statusSalvoTopo = document.getElementById("statusSalvoTopo");

// ---------------------------------------------------------------------------
// Renderização da lista de alunos
// ---------------------------------------------------------------------------
function renderAlunoSelect(cpfSelecionado) {
  alunoSelect.innerHTML = "";
  alunos.forEach(a => {
    const opt = document.createElement("option");
    opt.value = a.cpf;
    opt.textContent = a.nome;
    alunoSelect.appendChild(opt);
  });
  if (cpfSelecionado && alunos.some(a => a.cpf === cpfSelecionado)) {
    alunoSelect.value = cpfSelecionado;
  }
  atualizarCpfExibido();
}

function atualizarCpfExibido() {
  const aluno = alunos.find(a => a.cpf === alunoSelect.value);
  alunoCpf.value = aluno ? aluno.cpf : "";
}

// ---------------------------------------------------------------------------
// Renderização do checklist (estrutura fixa, gerada uma única vez)
// ---------------------------------------------------------------------------
function idItem(unidadeIdx, grupoIdx, itemIdx) {
  return `u${unidadeIdx}_g${grupoIdx}_i${itemIdx}`;
}

function renderChecklist() {
  checklistContainer.innerHTML = "";

  CHECKLIST.forEach((bloco, unidadeIdx) => {
    const unidadeDiv = document.createElement("div");
    unidadeDiv.className = "unidade-bloco";

    const tituloUnidade = document.createElement("div");
    tituloUnidade.className = "unidade-titulo";
    tituloUnidade.textContent = bloco.unidade;
    unidadeDiv.appendChild(tituloUnidade);

    const tituloElemento = document.createElement("div");
    tituloElemento.className = "elemento-titulo";
    tituloElemento.textContent = bloco.elemento;
    unidadeDiv.appendChild(tituloElemento);

    bloco.grupos.forEach((grupo, grupoIdx) => {
      const grupoDiv = document.createElement("div");
      grupoDiv.className = "grupo";

      const header = document.createElement("div");
      header.className = "grupo-header";
      header.textContent = grupo.titulo;
      grupoDiv.appendChild(header);

      const table = document.createElement("table");
      table.className = "itens";
      table.innerHTML = `
        <thead>
          <tr>
            <th>Evidência observável</th>
            <th class="col-escala">Sim</th>
            <th class="col-escala">Não</th>
            <th>Justificativa do Não</th>
          </tr>
        </thead>
        <tbody></tbody>
      `;
      const tbody = table.querySelector("tbody");

      grupo.itens.forEach((item, itemIdx) => {
        const id = idItem(unidadeIdx, grupoIdx, itemIdx);
        const tr = document.createElement("tr");
        tr.innerHTML = `
          <td class="col-evidencia"><span class="codigo-item">${item.cod}</span>${escapeHtml(item.texto)}</td>
          <td class="col-escala" data-role="cel-sim">
            <div class="checkbox-wrap">
              <input type="checkbox" data-item="${id}" data-tipo="sim">
            </div>
          </td>
          <td class="col-escala" data-role="cel-nao">
            <div class="checkbox-wrap">
              <input type="checkbox" data-item="${id}" data-tipo="nao">
            </div>
          </td>
          <td>
            <input type="text" class="justificativa-input" data-item="${id}" data-tipo="justificativa" placeholder="Justificativa..." disabled>
          </td>
        `;
        tbody.appendChild(tr);
      });

      grupoDiv.appendChild(table);
      unidadeDiv.appendChild(grupoDiv);
    });

    checklistContainer.appendChild(unidadeDiv);
  });

  // liga os eventos de marcação Sim/Não (mutuamente exclusivos)
  checklistContainer.querySelectorAll('input[type="checkbox"]').forEach(cb => {
    cb.addEventListener("change", onEscalaChange);
  });
}

function escapeHtml(str) {
  const div = document.createElement("div");
  div.textContent = str;
  return div.innerHTML;
}

function onEscalaChange(e) {
  const cb = e.target;
  const itemId = cb.dataset.item;
  const tipo = cb.dataset.tipo;
  const tr = cb.closest("tr");
  const simCb = tr.querySelector(`input[data-tipo="sim"]`);
  const naoCb = tr.querySelector(`input[data-tipo="nao"]`);
  const justificativa = tr.querySelector(`input[data-tipo="justificativa"]`);
  const celSim = tr.querySelector('[data-role="cel-sim"]');
  const celNao = tr.querySelector('[data-role="cel-nao"]');

  if (cb.checked) {
    if (tipo === "sim") {
      naoCb.checked = false;
      justificativa.disabled = true;
      justificativa.value = "";
    } else {
      simCb.checked = false;
      justificativa.disabled = false;
    }
  } else {
    justificativa.disabled = !naoCb.checked;
  }

  celSim.classList.toggle("marcado-sim", simCb.checked);
  celNao.classList.toggle("marcado-nao", naoCb.checked);
}

// ---------------------------------------------------------------------------
// Coleta / preenchimento de respostas por aluno
// ---------------------------------------------------------------------------
function coletarRespostasAtuais() {
  const respostas = {};
  checklistContainer.querySelectorAll("tbody tr").forEach(tr => {
    const simCb = tr.querySelector('input[data-tipo="sim"]');
    const naoCb = tr.querySelector('input[data-tipo="nao"]');
    const justificativa = tr.querySelector('input[data-tipo="justificativa"]');
    const itemId = simCb.dataset.item;
    respostas[itemId] = {
      sim: simCb.checked,
      nao: naoCb.checked,
      justificativa: justificativa.value || ""
    };
  });
  return respostas;
}

function preencherRespostas(respostas) {
  checklistContainer.querySelectorAll("tbody tr").forEach(tr => {
    const simCb = tr.querySelector('input[data-tipo="sim"]');
    const naoCb = tr.querySelector('input[data-tipo="nao"]');
    const justificativa = tr.querySelector('input[data-tipo="justificativa"]');
    const celSim = tr.querySelector('[data-role="cel-sim"]');
    const celNao = tr.querySelector('[data-role="cel-nao"]');
    const itemId = simCb.dataset.item;
    const r = respostas ? respostas[itemId] : null;

    simCb.checked = !!(r && r.sim);
    naoCb.checked = !!(r && r.nao);
    justificativa.value = r ? r.justificativa || "" : "";
    justificativa.disabled = !naoCb.checked;

    celSim.classList.toggle("marcado-sim", simCb.checked);
    celNao.classList.toggle("marcado-nao", naoCb.checked);
  });
}

function limparRespostasNaTela() {
  preencherRespostas(null);
}

// ---------------------------------------------------------------------------
// Carregar/gravar avaliação completa do aluno selecionado
// ---------------------------------------------------------------------------
function carregarAvaliacaoDoAluno() {
  const cpf = alunoSelect.value;
  const dados = carregarRespostas(cpf);

  if (dados) {
    avaliadorInput.value = dados.avaliador || "";
    cursoInput.value = dados.curso || "TÉCNICO EM DESENVOLVIMENTO DE SISTEMAS";
    dataInput.value = dados.data || "";
    horaInput.value = dados.hora || "";
    edicaoInput.value = dados.edicao || "2021";
    preencherRespostas(dados.respostas);
  } else {
    avaliadorInput.value = "";
    dataInput.value = "";
    horaInput.value = "";
    limparRespostasNaTela();
  }
  statusSalvo.textContent = "";
}

function salvarAvaliacaoAtual() {
  const cpf = alunoSelect.value;
  if (!cpf) {
    alert("Selecione um aluno antes de salvar.");
    return;
  }
  const dados = {
    avaliador: avaliadorInput.value,
    curso: cursoInput.value,
    data: dataInput.value,
    hora: horaInput.value,
    edicao: edicaoInput.value,
    respostas: coletarRespostasAtuais()
  };
  salvarRespostas(cpf, dados);
  statusSalvo.textContent = "Avaliação salva com sucesso.";
  statusSalvoTopo.textContent = "Avaliação salva com sucesso.";
  setTimeout(() => {
    statusSalvo.textContent = "";
    statusSalvoTopo.textContent = "";
  }, 3000);
}

// ---------------------------------------------------------------------------
// Eventos
// ---------------------------------------------------------------------------
alunoSelect.addEventListener("change", () => {
  atualizarCpfExibido();
  carregarAvaliacaoDoAluno();
});

btnAddAluno.addEventListener("click", () => {
  const nome = novoAlunoNome.value.trim();
  const cpf = novoAlunoCpf.value.trim();

  if (!nome || !cpf) {
    alert("Informe nome e CPF do aluno.");
    return;
  }
  if (alunos.some(a => a.cpf === cpf)) {
    alert("Já existe um aluno cadastrado com este CPF.");
    return;
  }

  alunos.push({ nome, cpf });
  salvarAlunos(alunos);
  renderAlunoSelect(cpf);
  carregarAvaliacaoDoAluno();

  novoAlunoNome.value = "";
  novoAlunoCpf.value = "";
});

btnRemoveAluno.addEventListener("click", () => {
  const cpf = alunoSelect.value;
  if (!cpf) return;
  const aluno = alunos.find(a => a.cpf === cpf);
  if (!aluno) return;
  if (!confirm(`Remover o aluno "${aluno.nome}" e todas as respostas salvas dele?`)) return;

  alunos = alunos.filter(a => a.cpf !== cpf);
  localStorage.removeItem(chaveRespostas(cpf));

  if (alunos.length === 0) {
    alunos = [ALUNO_PADRAO];
  }
  salvarAlunos(alunos);
  renderAlunoSelect(alunos[0].cpf);
  carregarAvaliacaoDoAluno();
});

btnSalvar.addEventListener("click", salvarAvaliacaoAtual);

btnLimpar.addEventListener("click", () => {
  if (!confirm("Limpar todas as respostas preenchidas na tela para este aluno?")) return;
  limparRespostasNaTela();
  avaliadorInput.value = "";
  dataInput.value = "";
  horaInput.value = "";
});

btnImprimir.addEventListener("click", () => {
  const aluno = alunos.find(a => a.cpf === alunoSelect.value);
  const tituloOriginal = document.title;
  if (aluno) document.title = aluno.nome;
  window.print();
  document.title = tituloOriginal;
});

// ---------------------------------------------------------------------------
// Relatório Simplificado
// ---------------------------------------------------------------------------
function imprimirSimplificado() {
  const aluno = alunos.find(a => a.cpf === alunoSelect.value);
  const respostas = coletarRespostasAtuais();

  const GENERICAS = new Set([
    "Nível inferior ao alcançado pelo aluno",
    "Procedimento não localizado",
    "Atividade realizada pelo aluno"
  ]);

  function esc(s) {
    return String(s)
      .replace(/&/g, "&amp;")
      .replace(/</g, "&lt;")
      .replace(/>/g, "&gt;");
  }

  function limparTexto(t) {
    // Remove prefixo tipo "A03_00_C4+S3_0_" deixando só a descrição
    return t.replace(/^A\d+_\d+_[^_]+_\d+_/, "").trim();
  }

  function getAtividade(t) {
    const m = t.match(/^(A\d+)_/);
    return m ? m[1] : null;
  }

  function renderItem(item, r) {
    if (!r) return "";
    if (/_S[67]_/.test(item.texto)) return ""; // sempre omite itens S6/S7
    const txt = esc(limparTexto(item.texto));
    if (r.sim) {
      return `<p class="sim"><span class="label-sim">✓ SIM</span> ${txt}</p>`;
    }
    if (r.nao && r.justificativa && !GENERICAS.has(r.justificativa)) {
      return `<p class="nao"><span class="label-nao">✗ NÃO</span> ${txt}<span class="just">↳ ${esc(r.justificativa)}</span></p>`;
    }
    return "";
  }

  let corpo = "";

  CHECKLIST.forEach((bloco, ui) => {
    corpo += `<h2>${esc(bloco.elemento)}</h2>`;

    bloco.grupos.forEach((grupo, gi) => {
      corpo += `<h3>${esc(grupo.titulo)}</h3>`;

      // Agrupa itens por atividade (relevante para u1g0 que mistura A04–A11)
      const porAtiv = new Map();
      grupo.itens.forEach((item, ii) => {
        const act = getAtividade(item.texto) || "?";
        if (!porAtiv.has(act)) porAtiv.set(act, []);
        porAtiv.get(act).push({ item, ii });
      });

      porAtiv.forEach((entries, act) => {
        let blk = "";
        entries.forEach(({ item, ii }) => {
          blk += renderItem(item, respostas[`u${ui}_g${gi}_i${ii}`]);
        });
        if (!blk) return;
        if (porAtiv.size > 1) corpo += `<div class="ativ">${act}</div>`;
        corpo += blk;
      });
    });
  });

  const nomeAluno = aluno ? aluno.nome : "Relatório";
  const dataFmt = dataInput.value
    ? new Date(dataInput.value + "T12:00:00").toLocaleDateString("pt-BR")
    : "—";

  const html = `<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>${esc(nomeAluno)}</title>
<style>
  body { font-family: Arial, sans-serif; font-size: 11pt; color: #000; margin: 2cm; }
  h1 { font-size: 14pt; text-align: center; border-bottom: 2px solid #000; padding-bottom: 6px; margin-bottom: 14px; }
  .cab { font-size: 10.5pt; line-height: 1.8; margin-bottom: 18px; border-bottom: 1px solid #ccc; padding-bottom: 10px; }
  h2 { font-size: 11pt; font-weight: bold; border-bottom: 1px solid #000; margin: 18px 0 6px; }
  h3 { font-size: 10.5pt; font-weight: bold; margin: 10px 0 4px; }
  .ativ { font-size: 10pt; font-weight: bold; text-transform: uppercase; letter-spacing: 0.4px; margin: 10px 0 2px 4px; border-left: 3px solid #555; padding-left: 6px; }
  p.sim, p.nao { margin: 3px 0 5px 12px; font-size: 10.5pt; line-height: 1.45; }
  .label-sim { font-weight: bold; margin-right: 4px; }
  .label-nao { font-weight: bold; margin-right: 4px; }
  .just { display: block; margin: 3px 0 2px 18px; font-style: italic; font-size: 10pt; color: #333; }
  @media print { body { margin: 10mm; } }
</style>
</head>
<body>
<h1>Lista de Verificação — Relatório Simplificado</h1>
<div class="cab">
  <strong>Aluno:</strong> ${esc(aluno?.nome || "—")}&emsp;<strong>CPF:</strong> ${esc(aluno?.cpf || "—")}<br>
  <strong>Avaliador:</strong> ${esc(avaliadorInput.value || "—")}&emsp;<strong>Data:</strong> ${dataFmt}&emsp;<strong>Edição:</strong> ${esc(edicaoInput.value || "—")}<br>
  <strong>Curso:</strong> ${esc(cursoInput.value || "—")}
</div>
${corpo}
</body>
</html>`;

  const win = window.open("", "_blank");
  win.document.write(html);
  win.document.close();
  win.focus();
  setTimeout(() => win.print(), 400);
}

btnImprimirSimples.addEventListener("click", imprimirSimplificado);

document.getElementById("btnSalvarTopo").addEventListener("click", salvarAvaliacaoAtual);
document.getElementById("btnLimparTopo").addEventListener("click", () => btnLimpar.click());
document.getElementById("btnImprimirTopo").addEventListener("click", () => btnImprimir.click());
document.getElementById("btnImprimirSimplesTopo").addEventListener("click", imprimirSimplificado);

// ---------------------------------------------------------------------------
// Inicialização
// ---------------------------------------------------------------------------
renderChecklist();
renderAlunoSelect(alunos[0].cpf);
carregarAvaliacaoDoAluno();

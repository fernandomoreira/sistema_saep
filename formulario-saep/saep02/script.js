// ---------------------------------------------------------------------------
// Dados do checklist (extraídos do formulário SAEP02 - Lista de Verificação por Atividade)
// ---------------------------------------------------------------------------
const CHECKLIST = [
  {
    atividade: "ATIVIDADE 01. SCRIPT POPULAÇÃO BANCO DE DADOS",
    itens: [
      { cod: "A01__0_S6_0", texto: "O aluno apresentou dificuldade para realizar a prova e optou pela não realização.", cap: "C4", peso: 1 },
      { cod: "A01__0_S7_0", texto: "O tempo de prova finalizou antes de o aluno começar a atividade.", cap: "C4", peso: 1 },
      { cod: "A01_00_C4+S3+S6_0", texto: "O script criado apresenta código que inviabiliza a compilação da linguagem.", cap: "C4", peso: 1 },
      { cod: "A01_01_C4+S3+S6_1", texto: "O script do banco de dados foi compilado, porém apresenta erros estruturais, como inconsistências em tipos de dados, chaves primárias ou estrangeiras, que inviabilizam o funcionamento do banco e o atendimento a todas as regras de negócio.", cap: "C4", peso: 1 },
      { cod: "A01_02_C4+S3+S6_2", texto: "O script do banco de dados foi implementado de acordo com as regras de negócio, respeitando os tipos de dados, porém sem implementar as chaves primárias e estrangeiras considerando com as boas práticas.", cap: "C4", peso: 2 },
      { cod: "A01_03_C4+S3+S6_3", texto: "O script do banco de dados foi implementado de acordo com as regras de negócio, respeitando os tipos de dados, chaves primárias e estrangeiras, porém sem implementação e inserção dos três registros mínimos.", cap: "C4", peso: 2 },
      { cod: "A01_04_C4+S3+S6_4", texto: "O script do banco de dados foi implementado de acordo com as regras de negócio, contendo todas as tabelas com, no mínimo, três registros cada, respeitando os tipos de dados, chaves primárias e estrangeiras.", cap: "C4", peso: 3 }
    ]
  },
  {
    atividade: "ATIVIDADE 02. CRIAÇÃO DA VIEW VW_ESTOQUE",
    itens: [
      { cod: "A02__0_S6_0", texto: "O aluno apresentou dificuldade para realizar a prova e optou pela não realização.", cap: "C4", peso: 1 },
      { cod: "A02__0_S7_0", texto: "O tempo de prova finalizou antes de o aluno começar a atividade.", cap: "C4", peso: 1 },
      { cod: "A02_00_C4+S4_0", texto: "A view vw_estoque implementada não apresenta qualquer estrutura que permita a apuração do valor total por produto.", cap: "C4", peso: 1 },
      { cod: "A02_01_C4+S4_1", texto: "A view vw_estoque foi implementada apresentando erros de cálculo que inviabilizam a apuração do valor total por produto.", cap: "C4", peso: 1 },
      { cod: "A02_02_C4+S4_2", texto: "A view vw_estoque foi implementada, realizando o cálculo do valor total por item de produto, com base na quantidade registrada e no valor unitário, e/ou retornando uma tabela com apenas duas colunas: com o valor total sendo obrigatório, e as colunas opcionais: Identificador, valor unitário, quantidade e denominação (nome).", cap: "C4", peso: 2 },
      { cod: "A02_03_C4+S4_3", texto: "A view vw_estoque foi implementada, realizando o cálculo do valor total por item de produto, com base na quantidade registrada e no valor unitário, retornando uma tabela com 3 ou 4 colunas: com o valor total, valor unitário e denominação (nome) sendo obrigatório, e a coluna quantidade fica opcional.", cap: "C4", peso: 2 },
      { cod: "A02_04_C4+S4_4", texto: "A view vw_estoque foi implementada, realizando o cálculo do valor total por item de produto, com base na quantidade registrada e no valor unitário, retornando uma tabela com, no mínimo, 5 colunas: Identificador, valor unitário, valor total, quantidade e denominação (nome).", cap: "C4", peso: 3 }
    ]
  },
  {
    atividade: "ATIVIDADE 03. CRIAÇÃO DIAGRAMA ENTIDADE-RELACIONAMENTO",
    itens: [
      { cod: "A03__0_S6_0", texto: "O aluno apresentou dificuldade para realizar a prova e/ou optou pela não realização da atividade.", cap: "C4", peso: 1 },
      { cod: "A03__0_S7_0", texto: "O tempo de prova finalizou antes de o aluno começar a atividade.", cap: "C4", peso: 1 },
      { cod: "A03_00_C4+S3_0", texto: "A modelagem apresenta registros de classes e atributos incompatíveis com os requisitos funcionais e a regra de negócio.", cap: "C4", peso: 1 },
      { cod: "A03_01_C4+S3_1", texto: "O Diagrama Entidade-Relacionamento (DER) contempla 1 elementos obrigatório: entidades, atributos, identificador com auto-incremento, chaves primárias, chaves estrangeiras e cardinalidade dos relacionamentos.", cap: "C4", peso: 1 },
      { cod: "A03_02_C4+S3_2", texto: "O Diagrama Entidade-Relacionamento (DER) contempla, no mínimo, 2 dos 6 elementos obrigatórios: entidades, atributos, identificador com auto-incremento, chaves primárias, chaves estrangeiras e cardinalidade dos relacionamentos.", cap: "C4", peso: 2 },
      { cod: "A03_03_C4+S3_3", texto: "O Diagrama Entidade-Relacionamento (DER) contempla, no mínimo, 4 dos 6 elementos obrigatórios: entidades, atributos, identificador com auto-incremento, chaves primárias, chaves estrangeiras e cardinalidade dos relacionamentos, com representação da estrutura principal dos dados do sistema.", cap: "C4", peso: 2 },
      { cod: "A03_04_C4+S3_4", texto: "O Diagrama Entidade-Relacionamento (DER) contempla os 6 elementos obrigatórios: entidades, atributos, identificador com auto-incremento, chaves primárias, chaves estrangeiras e cardinalidade dos relacionamentos, estruturando os dados conforme as regras de negócio previstas para o sistema, incluindo registro de movimentações e vínculo do responsável pela operação.", cap: "C4", peso: 3 }
    ]
  },
  {
    atividade: "ATIVIDADE 04. LISTAR VALOR TOTAL POR ITEM DE PRODUTO",
    itens: [
      { cod: "A04__0_S6_0", texto: "O aluno apresentou dificuldade para realizar a prova e optou pela não realização.", cap: "C3", peso: 1 },
      { cod: "A04__0_S7_0", texto: "O tempo de prova finalizou antes de o aluno começar a atividade.", cap: "C3", peso: 1 },
      { cod: "A04_00_C3+S3_0", texto: "A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade.", cap: "C3", peso: 1 },
      { cod: "A04_01_C3+S3_1", texto: "A aplicação apresenta implementação da funcionalidade, porém com erros de lógica e estrutura que impedem a execução do cálculo e da listagem do valor total por item de produto.", cap: "C3", peso: 1 },
      { cod: "A04_02_C3+S3_2", texto: "A aplicação apresenta uma função que realiza o cálculo do valor total por item de produto, utilizando comandos SQL e lógica diretamente no código, sem o uso de view, ou utiliza a view para realizar o cálculo retornando um resultado errado.", cap: "C3", peso: 2 },
      { cod: "A04_03_C3+S3_3", texto: "A aplicação apresenta uma função que lista o valor total por item de produto, utilizando consulta a view. No entanto, faltam informações do produto, há inconsistências no código da função.", cap: "C3", peso: 2 },
      { cod: "A04_03_C3+S3_3", texto: "A aplicação apresenta uma função que lista o valor total por item de produto, utilizando consulta a view. No entanto, faltam informações do produto, há inconsistências no código da função.", cap: "C3", peso: 3 }
    ]
  },
  {
    atividade: "ATIVIDADE 05. LISTAR TODOS OS PRODUTOS CADASTRADOS",
    itens: [
      { cod: "A05__0_S6_0", texto: "O aluno apresentou dificuldade para realizar a prova e optou pela não realização.", cap: "C3", peso: 1 },
      { cod: "A05__0_S7_0", texto: "O tempo de prova finalizou antes de o aluno começar a atividade.", cap: "C3", peso: 1 },
      { cod: "A05_00_C3+S4_0", texto: "A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade.", cap: "C3", peso: 1 },
      { cod: "A05_01_C3+S4_1", texto: "A aplicação apresenta tentativa de implementação da função de cadastro de produtos, porém com erros de lógica e estrutura que impedem a execução da operação em desacordo com as regras definidas.", cap: "C3", peso: 1 },
      { cod: "A05_02_C3+S4_2", texto: "A função permite a listagem de todos os produtos cadastrado, contemplando, no mínimo 3 campos previstos que são considerados principais: nome do produto, quantidade em estoque e valor unitário, porém apresenta falhas relacionadas às validações, ao tratamento de erros e à padronização do código.", cap: "C3", peso: 1 },
      { cod: "A05_03_C3+S4_3", texto: "A função realiza a listagem de todos os produtos cadastrados de forma funcional, contemplando, no mínimo 4 campos previstos que são considerados principais: nome do produto, unidade de medida, quantidade em estoque e valor unitário, porém apresenta falhas relacionadas às validações, ao tratamento de erros e à padronização do código.", cap: "C3", peso: 2 },
      { cod: "A05_04_C3+S4_4", texto: "A função implementa a listagem de todos os produtos, contemplando todos os 6 campos previstos e suas validações (nome do produto, unidade de medida, quantidade em estoque e valor unitário, limite máxima e mínimo), os dados, integração com o banco de dados e código padronizado, atendendo as regras de negócio.", cap: "C3", peso: 3 }
    ]
  },
  {
    atividade: "ATIVIDADE 06. CADASTRO DE NOVO PRODUTO",
    itens: [
      { cod: "A06__0_S6_0", texto: "O aluno apresentou dificuldade para realizar a prova e optou pela não realização.", cap: "C3", peso: 1 },
      { cod: "A06__0_S7_0", texto: "O tempo de prova finalizou antes de o aluno começar a atividade.", cap: "C3", peso: 1 },
      { cod: "A06_00_C3+S3_0", texto: "A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade.", cap: "C3", peso: 1 },
      { cod: "A06_01_C3+S3_1", texto: "A aplicação apresenta implementação da função de cadastro, porém com falhas de lógica e estrutura que impedem a inserção do produto no banco de dados.", cap: "C3", peso: 1 },
      { cod: "A06_02_C3+S3_2", texto: "A função de cadastro de novo produto está implementada conforme as regras de negócio, porém a função está sem os critérios de validação (valor unitário, quantidade e denominação de entrada e saída, duplicidade) e/ou tratamento de erros.", cap: "C3", peso: 2 },
      { cod: "A06_03_C3+S3_3", texto: "A função de cadastro está implementada conforme as regras de negócio, possui as validações (valor unitário, quantidade e denominação) de entrada e saída, porém sem o tratamento de erros.", cap: "C3", peso: 2 },
      { cod: "A06_04_C3+S3_4", texto: "A função de cadastro está implementada conforme as regras de negócio, possui as validações (valor unitário, quantidade e denominação) de entrada e saída, verificação contra duplicidade e tratamento de erros.", cap: "C3", peso: 3 }
    ]
  },
  {
    atividade: "ATIVIDADE 07. LIST. SAÍDAS PROD. ORDEM DEC. DATA",
    itens: [
      { cod: "A07__0_S6_0", texto: "O aluno apresentou dificuldade para realizar a prova e optou pela não realização.", cap: "C3", peso: 1 },
      { cod: "A07__0_S7_0", texto: "O tempo de prova finalizou antes de o aluno começar a atividade.", cap: "C3", peso: 1 },
      { cod: "A07_00_C3+S3_0", texto: "A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade.", cap: "C3", peso: 1 },
      { cod: "A07_01_C3+S3_1", texto: "A aplicação apresenta a função de listagem de saídas de produtos implementada com falhas de lógica e estrutura que inviabilizam o retorno dos registros em ordem decrescente por data, comprometendo a consulta das informações previstas nas regras de negócio.", cap: "C3", peso: 1 },
      { cod: "A07_02_C3+S3_2", texto: "A função retorna todas as saídas de produtos, contemplando, no mínimo 2 dos 6 campos (nome do produto, unidade de medida, quantidade em estoque, valor unitário, limite máximo e mínimo), porém não está em ordem decrescente por data e/ou a formatação não está de acordo com o padrão: exemplo da formatação: 25-12-2025 16:50.", cap: "C3", peso: 2 },
      { cod: "A07_03_C3+S3_3", texto: "A função retorna todas as saídas de produtos, em ordem decrescente por data, contemplando, no mínimo 4 campos: nome do produto, unidade de medida, quantidade em estoque e valor unitário, porém a formatação não está de acordo com o padrão: exemplo da formatação: 25-12-2025.", cap: "C3", peso: 2 },
      { cod: "A07_04_C3+S3_4", texto: "A função retorna todas as saídas de produtos, padronizadas em ordem decrescente por data, com todas as informações dos 6 campos (nome do produto, unidade de medida, quantidade em estoque, valor unitário, limite máxima e mínimo). (exemplo da formatação: 25-12-2025).", cap: "C3", peso: 3 }
    ]
  },
  {
    atividade: "ATIVIDADE 08. ENTRADA DE ITENS NO ESTOQUE",
    itens: [
      { cod: "A08__0_S6_0", texto: "O aluno apresentou dificuldade para realizar a prova e optou pela não realização.", cap: "C3", peso: 1 },
      { cod: "A08__0_S7_0", texto: "O tempo de prova finalizou antes de o aluno começar a atividade.", cap: "C3", peso: 1 },
      { cod: "A08_00_C3+S3_0", texto: "A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade.", cap: "C3", peso: 1 },
      { cod: "A08_01_C3+S3_1", texto: "A aplicação apresenta função de entrada de itens no estoque, porém a implementação contém erros de lógica e estrutura que impedem o registro da entrada conforme as regras de negócio definidas.", cap: "C3", peso: 1 },
      { cod: "A08_02_C3+S3_2", texto: "A função realiza o registro de entrada e atualiza o saldo em estoque, porém apresenta erro na fórmula do cálculo dessa atualização. Além disso, efetua a integração das informações com o banco de dados, mas a data não está padronizada no formato 25-12-2025.", cap: "C3", peso: 2 },
      { cod: "A08_03_C3+S3_3", texto: "A função realiza o registro de entrada e atualiza automaticamente as quantidades dos itens com base no saldo atual em estoque, efetuando a integração das informações com o banco de dados, porém a data não está padronizada no formato 25-12-2025.", cap: "C3", peso: 2 },
      { cod: "A08_04_C3+S3_4", texto: "A função realiza a entrada e atualiza as quantidades de itens de acordo com o saldo em estoque atual, emitindo a data padronizada conforme a formatação: 25-12-2025 e realiza a integração com o banco de dados.", cap: "C3", peso: 3 }
    ]
  },
  {
    atividade: "ATIVIDADE 09. RELAT. DE ENTRADA E SAÍDA POR PERÍODO",
    itens: [
      { cod: "A09__0_S6_0", texto: "O aluno apresentou dificuldade para realizar a prova e optou pela não realização.", cap: "C3", peso: 1 },
      { cod: "A09__0_S7_0", texto: "O tempo de prova finalizou antes de o aluno começar a atividade.", cap: "C3", peso: 1 },
      { cod: "A09_00_C3+S4_0", texto: "A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade.", cap: "C3", peso: 1 },
      { cod: "A09_01_C3+S4_1", texto: "A aplicação apresenta função de relatório por período, porém a implementação contém erros de lógica que impedem o retorno dos dados de entrada e saída no intervalo informado e erro da fórmula de cálculo do valor total financeiro de entrada e/ou saída.", cap: "C3", peso: 1 },
      { cod: "A09_02_C3+S4_2", texto: "A função emite o relatório de entradas e saídas no período informado, com cálculos precisos, contendo até 3 campos obrigatórios, conforme sequência: nome do produto, unidade de medidas e total de entradas.", cap: "C3", peso: 2 },
      { cod: "A09_03_C3+S4_3", texto: "A função emite o relatório de entradas e saídas no período informado, com cálculos precisos, contendo de 4 a 6 campos obrigatórios, conforme sequência: nome do produto, unidade de medidas, total de entradas, total de saídas, saldo no período e valor total financeiro das entradas.", cap: "C3", peso: 2 },
      { cod: "A09_04_C3+S4_4", texto: "A função emite o relatório de entradas e saídas no período informado, contendo 7 campos: nome do produto, unidade de medidas, total de entradas, total de saídas, saldo no período, valor total financeiro das entradas e valor total financeiro de saídas.", cap: "C3", peso: 3 }
    ]
  },
  {
    atividade: "ATIVIDADE 10. FUNÇ. MAIOR QUANT. DE SAÍDA DE PRODUTO",
    itens: [
      { cod: "A10__0_S6_0", texto: "O aluno apresentou dificuldade para realizar a prova e optou pela não realização.", cap: "C4", peso: 1 },
      { cod: "A10__0_S7_0", texto: "O tempo de prova finalizou antes de o aluno começar a atividade.", cap: "C3", peso: 1 },
      { cod: "A10_00_C3+S4_0", texto: "A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade.", cap: "C3", peso: 1 },
      { cod: "A10_01_C3+S4_1", texto: "A aplicação apresenta função para identificar produtos com maior quantidade de saída no período, porém a implementação contém erros de lógica que impedem o retorno dos produtos, das quantidades no intervalo informado e/ou ordenação.", cap: "C3", peso: 1 },
      { cod: "A10_02_C3+S4_2", texto: "A função retorna os produtos com maior quantidade de saída no período informado, apresentando, com agrupamento por produto e ordenação decrescente pela quantidade total de saída, 1 campo com fórmula de cálculo preciso dos 3 campos obrigatórios, e na sequência: nome do produto, quantidade total de saída e valor total financeiro das saídas.", cap: "C3", peso: 2 },
      { cod: "A10_03_C3+S4_3", texto: "A função retorna os produtos com maior quantidade de saída no período informado, apresentando, com agrupamento por produto e ordenação decrescente pela quantidade total de saída, 2 campos com fórmula de cálculo preciso dos 3 campos obrigatórios, e na sequência: nome do produto, quantidade total de saída e valor total financeiro das saídas.", cap: "C3", peso: 2 },
      { cod: "A10_04_C3+S4_4", texto: "A função retorna os produtos com maior quantidade de saída no período informado, apresentando, com agrupamento por produto e ordenação decrescente pela quantidade total de saída, os 3 campos obrigatórios com fórmula de cálculo preciso e na sequência: nome do produto, quantidade total de saída e valor total financeiro das saídas.", cap: "C3", peso: 3 }
    ]
  },
  {
    atividade: "ATIVIDADE 11. FUNÇ. PRODUTOS NÍVEL MIN./MAX. EM ESTOQUE",
    itens: [
      { cod: "A11__0_S6_0", texto: "O aluno apresentou dificuldade para realizar a prova e optou pela não realização.", cap: "C3", peso: 1 },
      { cod: "A11__0_S7_0", texto: "O tempo de prova finalizou antes de o aluno começar a atividade.", cap: "C3", peso: 1 },
      { cod: "A11_00_C3+S4_0", texto: "A aplicação envolve a entrega de script com ou sem a presença de caracteres, ou seu conjunto, que não configuram funcionalidade.", cap: "C3", peso: 1 },
      { cod: "A11_01_C3+S4_1", texto: "A aplicação apresenta função de verificação de níveis de estoque, porém a implementação contém erros de lógica que impedem a identificação de produtos nos níveis mínimo ou máximo de estoque.", cap: "C3", peso: 1 },
      { cod: "A11_02_C3+S4_2", texto: "A função retorna produtos com informação de estoque, apresentando os 3 campos de referência, sendo obrigatório o campo nome do produto, e o limite mínimo e limite máximo de estoque.", cap: "C3", peso: 2 },
      { cod: "A11_03_C3+S4_3", texto: "A função retorna produtos com informação de estoque, apresentando os 4 campos de referência, sendo obrigatório o campo nome do produto, quantidade em estoque, limite mínimo e limite máximo de estoque.", cap: "C3", peso: 2 },
      { cod: "A11_04_C3+S4_4", texto: "A função retorna produtos com informação de estoque, apresentando os 4 campos de referência, sendo obrigatório o campo nome do produto, quantidade em estoque, limite mínimo e limite máximo de estoque, com a identificação do percentual de nível atingido.", cap: "C3", peso: 3 }
    ]
  }
];

// ---------------------------------------------------------------------------
// Persistência (localStorage)
// ---------------------------------------------------------------------------
const LS_ALUNOS = "saep_alunos";
const LS_RESPOSTAS_PREFIX = "saep02_respostas_";

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
const statusSalvo = document.getElementById("statusSalvo");

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
function idItem(atividadeIdx, itemIdx) {
  return `a${atividadeIdx}_i${itemIdx}`;
}

function renderChecklist() {
  checklistContainer.innerHTML = "";

  CHECKLIST.forEach((bloco, atividadeIdx) => {
    const atividadeDiv = document.createElement("div");
    atividadeDiv.className = "atividade-bloco";

    const titulo = document.createElement("div");
    titulo.className = "atividade-titulo";
    titulo.textContent = bloco.atividade;
    atividadeDiv.appendChild(titulo);

    const table = document.createElement("table");
    table.className = "itens";
    table.innerHTML = `
      <thead>
        <tr>
          <th>Evidência observável</th>
          <th class="col-cap">Capacidade</th>
          <th class="col-peso">Peso</th>
          <th class="col-escala">Sim</th>
          <th class="col-escala">Não</th>
          <th>Justificativa do Não</th>
        </tr>
      </thead>
      <tbody></tbody>
    `;
    const tbody = table.querySelector("tbody");

    bloco.itens.forEach((item, itemIdx) => {
      const id = idItem(atividadeIdx, itemIdx);
      const tr = document.createElement("tr");
      tr.innerHTML = `
        <td class="col-evidencia"><span class="codigo-item">${item.cod}</span>${escapeHtml(item.texto)}</td>
        <td class="col-cap"><span class="badge-cap">${item.cap}</span></td>
        <td class="col-peso"><span class="badge-peso">${item.peso}</span></td>
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

    atividadeDiv.appendChild(table);
    checklistContainer.appendChild(atividadeDiv);
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
  const tr = cb.closest("tr");
  const tipo = cb.dataset.tipo;
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
  setTimeout(() => { statusSalvo.textContent = ""; }, 3000);
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
  window.print();
});

// ---------------------------------------------------------------------------
// Inicialização
// ---------------------------------------------------------------------------
renderChecklist();
renderAlunoSelect(alunos[0].cpf);
carregarAvaliacaoDoAluno();

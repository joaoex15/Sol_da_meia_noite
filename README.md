# Sol_da_meia_noite

### Bloco 1: Cadastro e Gerenciamento de Clientes e Equipamentos

**Funcionalidades:**
- **1. Cadastro de Clientes:**
  - O sistema deve permitir que novos clientes sejam cadastrados, com informações básicas como nome, endereço e telefone.

- **2. Cadastro de Equipamentos:**
  - Fabricante: Você deverá cadastrar fabricantes especificando o nome.
  - Placas Solares: Você deverá cadastrar modelos de placas solares, especificando suas capacidades (em watts ou quilowatts), preços e fabricantes correspondentes.
  - Inversores: Cadastrar diferentes modelos de inversores, com suas potências, preços e fabricantes correspondentes.

- **5. Armazenamento e Carregamento de Dados:**
  - Antes de encerrar a aplicação, todos os dados (clientes, equipamentos e projetos, por exemplo) devem ser salvos em um arquivo texto (JSON ou CSV).
  - Ao iniciar a aplicação, ela deverá carregar os dados previamente salvos, se houver.

- **7. Módulos de Cliente e Empresa:**
  - O sistema deverá simular dois módulos:
    - Módulo da Empresa: A empresa pode gerenciar solicitações, realizar o dimensionamento, gerar relatórios financeiros e notificar clientes.
    - Módulo do Cliente: O cliente poderá solicitar projetos, receber orçamentos e aprová-los ou recusá-los.
  - O sistema deve ter uma tela de login que diferenciará o acesso de clientes e empresa.
  - Será preciso ter uma tela de cadastro para os clientes.

**Requisitos Adicionais:**
- Qualidade de Software: O código deverá ser modular e seguir boas práticas de desenvolvimento.
- Tratamento de Exceções: O código deve prever e tratar adequadamente possíveis exceções e erros, garantindo a estabilidade da aplicação.
- Testes Automatizados: Implementar testes automatizados para validação do cadastro de clientes e equipamentos usando JUnit.

---

### Bloco 2: Solicitação e Dimensionamento de Projetos

**Funcionalidades:**
- **3. Solicitação de Projetos:**
  - O cliente poderá solicitar a instalação de um sistema fotovoltaico, informando seu consumo médio de energia.

- **4. Dimensionamento do Sistema Fotovoltaico:**
  - A aplicação, com ajuda de um funcionário da empresa, deve calcular o número de painéis solares e inversores necessários considerando:
    - O consumo mensal do cliente.
    - A potência dos painéis solares e inversores cadastrados.
    - A irradiação solar da região do cliente.
  - O orçamento incluirá o custo dos equipamentos, uma margem de lucro e o custo de mão de obra.

- **6. Notificações:**
  - Quando o cliente solicitar um projeto, o sistema deverá enviar uma notificação para a empresa (via e-mail, SMS ou WhatsApp).
  - Após a conclusão do dimensionamento e orçamento pela empresa, o sistema enviará uma notificação ao cliente informando que seu orçamento está pronto.

**Requisitos Adicionais:**
- Qualidade de Software: O código deverá ser modular e seguir boas práticas de desenvolvimento.
- Testes Automatizados: Implementar testes automatizados para validação dos cálculos de dimensionamento.
- Padrões de Projeto: Utilizar padrões de projeto para garantir flexibilidade e manutenção do código.

---

### Bloco 3: Módulos de Cliente e Empresa, e Relatórios

**Funcionalidades:**

- **8. Decisão de Orçamento pelo Cliente:**
  - O cliente poderá listar todos os orçamentos gerados e terá a opção de aprová-los ou rejeitá-los diretamente na aplicação.

- **9. Controle de Orçamentos:**
  - A aplicação deve manter o controle de todos os orçamentos gerados, incluindo:
    - Orçamentos aprovados (comprados pelo cliente).
    - Orçamentos reprovados (recusados pelo cliente).
    - Orçamentos pendentes (ainda sem resposta do cliente).

- **10. Relatórios:**
  - Relatório de Projetos: Detalha os projetos solicitados pelos clientes, incluindo dimensionamento e custos.
  - Relatório de Clientes: Lista todos os clientes cadastrados e seus projetos associados.
  - Relatório de Equipamentos: Exibe os modelos de placas solares e inversores cadastrados, com suas capacidades e preços.
  - Relatório Financeiro: Mostra os custos dos projetos realizados e o lucro obtido pela empresa em cada um.

**Requisitos Adicionais:**
- Interface Gráfica: A aplicação deve possuir uma interface gráfica amigável, facilitando o uso tanto para o cliente quanto para a empresa.
- Tratamento de Exceções: O código deve prever e tratar adequadamente possíveis exceções e erros, garantindo a estabilidade da aplicação.
- Orientação a Objetos: A aplicação deve seguir os princípios da orientação a objetos, incluindo encapsulamento, herança, polimorfismo e coesão.

---

Com essa divisão, fica mais fácil para cada membro da equipe se concentrar em áreas específicas do projeto. Se precisar de mais orientações ou detalhes sobre cada funcionalidade, estou aqui para ajudar!

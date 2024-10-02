# Visão Geral

Este projeto é uma casa de apostas construída inteiramente em Java. Ele permite que usuários realizem apostas em eventos esportivos. Existem dois tipos de usuários no sistema:

- **Usuário Comum:** Pode acessar os eventos e fazer apostas nas opções disponíveis.
- **Administrador:** Tem a permissão de criar novos eventos (ex: Bahia versus Vitória) e definir as opções de apostas dentro desses eventos.

O projeto utiliza um banco de dados PostgreSQL para armazenamento das informações e possui uma interface gráfica para interação com os usuários.

## Funcionalidades Principais:

- **Cadastro e Login de Usuários:** Permite o cadastro de novos usuários e login no sistema.
- **Apostas:** Usuários podem visualizar eventos e fazer apostas.
- **Administração de Eventos:** Administradores podem criar, editar e gerenciar eventos e opções de apostas.
- **Interface Gráfica:** A aplicação tem uma série de telas para facilitar a navegação e interação do usuário.

## Estrutura do Projeto:

### 3.1. Entidades

Encontradas no diretório src/entidades, o software é composto por seis entidades principais:

- **Administrador:** Gerencia a criação e edição dos eventos e opções de apostas.
- **Aposta:** Representa as apostas realizadas pelos usuários em determinado evento.
- **Evento:** Representa um evento esportivo (ex: Bahia vs Vitória), com suas respectivas opções de apostas.
- **Pessoa:** Representa tanto os usuários comuns quanto os administradores do sistema.
- **Usuario:** Representa os usuários comuns, apostadores.
- **Bilhete:** Responsável por armazenar as apostas do usuário e devolver um resultado ("ganhou" ou "perdeu") ao encerramento de todos os eventos relacionados.
- **Administrador:** Representa os administradores.

### 3.2. Telas da Aplicação

Encontradas no diretório src/telas, o projeto inclui as seguintes principais telas(arquivos .java):

- **Login:** Tela de login para acessar o sistema.
- **CadastroDeUsuario:** Tela para realizar o cadastro de novos usuários.
- **TelaPrincipalUsuario:** Tela principal para usuários comuns, onde podem alterar dados da própria conta, depositar, visualizar os eventos e realizar apostas.
- **TelaPrincipalAdm:** Tela principal para administradores, com função de alterar dados da conta e acesso a funcionalidades relacionadas aos eventos(cadastro, edição, encerramento e visualização).
- **ApostaAdm:** Tela onde o administrador pode gerenciar as apostas. É advinda do botão de visualização de evento na TelaPrincipalAdm.
- **ApostasUsuario:** Tela onde o usuário pode visualizar e fazer apostas. É advinda do botão de visualização de evento na TelaPrincipalUsuario.
- **HistoricoDeApostas:** Tela para a exibição do histórico de apostas.
- **VisualizarBilhete:** Tela responsável pela exibição das informações do bilhete selecionado.

### 3.3. Banco de Dados

O projeto utiliza o banco de dados PostgreSQL para armazenar dados de usuários, administradores, apostas e eventos. A classe DB é responsável por gerenciar as conexões com o banco de dados e inclui métodos para:

- Iniciar e fechar conexões.
- Carregar propriedades do banco.
- Fechar declarações SQL e ResultSets.

Classes relativas ao seu acesso se encontram no diretório src/db. Das quais, "DB" se responsabiliza pelo acesso ao banco e DbException pelo lançamento de exceções!

### 3.4. DAO (Data Access Object)

Encontram-se no diretório src/dao e src/dao/impl. A aplicação utiliza o padrão DAO para realizar as operações de banco de dados relacionadas às entidades. As classes DAO são responsáveis por inserir, atualizar, deletar e buscar informações no banco de dados. As principais classes DAO são:

- **ApostaDaoJDBC:** Responsável por gerenciar as operações relacionadas às apostas, como inserir novas apostas, listar apostas por evento, listar todas as apostas, editar a descrição ou a odd de uma aposta, e deletar apostas por ID.
- **EventoDaoJDBC:** Gerencia as operações referentes aos eventos, como criar novos eventos, editar eventos existentes, deletar eventos e listar eventos.
- **PessoaDaoJDBC:** Responsável pelas operações relacionadas às pessoas, como cadastro, login e gerenciamento de dados de pessoas.
- **BilheteDaoJDBC:** Gerencia as operações referentes aos bilhetes, como criar novos bilhetes, deletar bilhetes e listar bilhetes.
- **CodigoDeCadastroAdmDaoJDBC:** Gerencia a seleção de códigos listados na tabela `CodigoDeCadastroAdm`, presente no banco de dados, com a finalidade de cadastrar usuários.
- **DaoFactory:** Fábrica de objetos DAO, responsável por fornecer instâncias das classes DAO conforme necessário.

Tais quais, com exceção de DaoFactory, são implementações das interfaces presentes no diretório src/dao. Mais especificamente e respectivamente à ordem apresentada anteriormente: ApostaDao, EventoDao, PessoaDao, BilheteDao e CodigoDeCadastroAdmDao.

## Diretório "arquivos de apresentação":

Conta com arquivos de apresentação exigidos pelo professor da matéria. Entre eles, um slide apresentando as telas da aplicação, um texto em SQL para povoamento em PostgreSQL e um diagrama(está incompleto).

![image](https://github.com/user-attachments/assets/2e649001-22fd-420c-b4ed-2bd1b080336c)


<<<<<<< HEAD
# betTeste
1. Visão Geral
Este projeto é uma casa de apostas construída inteiramente em Java. Ele permite que usuários realizem apostas em eventos esportivos. Existem dois tipos de usuários no sistema:

  Usuário Comum: Pode acessar os eventos e fazer apostas nas opções disponíveis.
  Administrador: Tem a permissão de criar novos eventos (ex: Bahia versus Vitória) e definir as opções de apostas dentro desses eventos.
  
O projeto utiliza um banco de dados PostgreSQL para armazenamento das informações e possui uma interface gráfica para interação com os    usuários.

2. Funcionalidades Principais
  Cadastro e Login de Usuários: Permite o cadastro de novos usuários e login no sistema.
  Apostas: Usuários podem visualizar eventos e fazer apostas.
  Administração de Eventos: Administradores podem criar, editar e gerenciar eventos e opções de apostas.
  Interface Gráfica: A aplicação tem uma série de telas para facilitar a navegação e interação do usuário.

3. Estrutura do Projeto
3.1. Entidades
O software é composto por quatro entidades principais:

  Administrador: Gerencia a criação e edição dos eventos e opções de apostas.
  Aposta: Representa as apostas realizadas pelos usuários em determinado evento.
  Evento: Representa um evento esportivo (ex: Bahia vs Vitória), com suas respectivas opções de apostas.
  Usuário: Representa tanto os usuários comuns quanto os administradores do sistema.
  
3.2. Telas da Aplicação
O projeto inclui as seguintes telas (arquivos .java):

  BoasVindas: Tela inicial de boas-vindas ao usuário.
  Login: Tela de login para acessar o sistema.
  CadastroDeUsuario: Tela para realizar o cadastro de novos usuários.
  TelaPrincipalUsuario: Tela principal para usuários comuns, onde podem visualizar os eventos e fazer apostas.
  TelaPrincipalAdm: Tela principal para administradores, com acesso a funcionalidades de gerenciamento de eventos.
  CadastroDeEvento: Tela para administradores criarem novos eventos.
  CadastroDeAposta: Tela para criação de novas apostas.
  ApostasUsuario: Tela onde o usuário pode visualizar e fazer apostas.
  ApostaAdm: Tela onde o administrador pode gerenciar as apostas.
  EditarEvento: Tela para a edição de eventos.
  EditarAposta: Tela para a edição de apostas.
  
3.3. Banco de Dados
O projeto utiliza o banco de dados PostgreSQL para armazenar dados de usuários, apostas e eventos. A classe DB é responsável por gerenciar as conexões com o banco de dados e inclui métodos para:

  Iniciar e fechar conexões.
  Carregar propriedades do banco.
  Fechar declarações SQL e ResultSets.
  
3.4. DAO (Data Access Object)
A aplicação utiliza o padrão DAO para realizar as operações de banco de dados relacionadas às entidades. As classes DAO são responsáveis por inserir, atualizar, deletar e buscar informações no banco de dados. As principais classes DAO são:

  ApostaDaoJDBC: Responsável por gerenciar as operações relacionadas às apostas, como inserir novas apostas, listar apostas por evento, listar todas as apostas, editar a descrição ou a odd de uma aposta, e deletar apostas por ID.

  EventoDaoJDBC: Gerencia as operações referentes aos eventos, como criar novos eventos, editar eventos existentes, deletar eventos e listar eventos.

  UsuarioDaoJDBC: Responsável pelas operações relacionadas aos usuários, como cadastro, login e gerenciamento de dados de usuários.

  PovoamentoDaoJDBC: Realiza o povoamento inicial do banco de dados com informações padrão ou de teste, como a criação de usuários e eventos de exemplo.

  DaoFactory: Fábrica de objetos DAO, responsável por fornecer instâncias das classes DAO conforme necessário.
=======
Visão Geral Este projeto é uma casa de apostas construída inteiramente em Java. Ele permite que usuários realizem apostas em eventos esportivos. Existem dois tipos de usuários no sistema:
Usuário Comum: Pode acessar os eventos e fazer apostas nas opções disponíveis. Administrador: Tem a permissão de criar novos eventos (ex: Bahia versus Vitória) e definir as opções de apostas dentro desses eventos.

O projeto utiliza um banco de dados PostgreSQL para armazenamento das informações e possui uma interface gráfica para interação com os usuários.

Funcionalidades Principais Cadastro e Login de Usuários: Permite o cadastro de novos usuários e login no sistema. Apostas: Usuários podem visualizar eventos e fazer apostas. Administração de Eventos: Administradores podem criar, editar e gerenciar eventos e opções de apostas. Interface Gráfica: A aplicação tem uma série de telas para facilitar a navegação e interação do usuário.

Estrutura do Projeto 3.1. Entidades O software é composto por quatro entidades principais:

Administrador: Gerencia a criação e edição dos eventos e opções de apostas. Aposta: Representa as apostas realizadas pelos usuários em determinado evento. Evento: Representa um evento esportivo (ex: Bahia vs Vitória), com suas respectivas opções de apostas. Usuário: Representa tanto os usuários comuns quanto os administradores do sistema.

3.2. Telas da Aplicação O projeto inclui as seguintes telas (arquivos .java):

BoasVindas: Tela inicial de boas-vindas ao usuário. Login: Tela de login para acessar o sistema. CadastroDeUsuario: Tela para realizar o cadastro de novos usuários. TelaPrincipalUsuario: Tela principal para usuários comuns, onde podem visualizar os eventos e fazer apostas. TelaPrincipalAdm: Tela principal para administradores, com acesso a funcionalidades de gerenciamento de eventos. CadastroDeEvento: Tela para administradores criarem novos eventos. CadastroDeAposta: Tela para criação de novas apostas. ApostasUsuario: Tela onde o usuário pode visualizar e fazer apostas. ApostaAdm: Tela onde o administrador pode gerenciar as apostas. EditarEvento: Tela para a edição de eventos. EditarAposta: Tela para a edição de apostas.

3.3. Banco de Dados O projeto utiliza o banco de dados PostgreSQL para armazenar dados de usuários, apostas e eventos. A classe DB é responsável por gerenciar as conexões com o banco de dados e inclui métodos para:

Iniciar e fechar conexões. Carregar propriedades do banco. Fechar declarações SQL e ResultSets.

3.4. DAO (Data Access Object) A aplicação utiliza o padrão DAO para realizar as operações de banco de dados relacionadas às entidades. As classes DAO são responsáveis por inserir, atualizar, deletar e buscar informações no banco de dados. As principais classes DAO são:

ApostaDaoJDBC: Responsável por gerenciar as operações relacionadas às apostas, como inserir novas apostas, listar apostas por evento, listar todas as apostas, editar a descrição ou a odd de uma aposta, e deletar apostas por ID.

EventoDaoJDBC: Gerencia as operações referentes aos eventos, como criar novos eventos, editar eventos existentes, deletar eventos e listar eventos.

UsuarioDaoJDBC: Responsável pelas operações relacionadas aos usuários, como cadastro, login e gerenciamento de dados de usuários.

PovoamentoDaoJDBC: Realiza o povoamento inicial do banco de dados com informações padrão ou de teste, como a criação de usuários e eventos de exemplo.

DaoFactory: Fábrica de objetos DAO, responsável por fornecer instâncias das classes DAO conforme necessário.
>>>>>>> master

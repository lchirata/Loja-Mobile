App Cadastro de Cliente


Aplicativo para plataforma Android tem como objetivo mostrar a criação de CRUD utilizando como banco de dados o SQLite por ser embutido no framework.

Serão abordados os principais comandos para o uso do banco, como:

- cadastrar
- editar
- listar
- excluir


É possível verificar os dados no banco de dados através do adb. É preciso instalar o adb tools.

Depois é só entrar na pasta do adb tools e executar os comandos através do terminal CMD. São Eles:

- adb devices
    - este comando vai listar todos os devices disponíveis e conectados ao Android Studio. O emulador ou celular deve estar em funcionamento.
  
- adb -s emulator-xxxx shell
    - este comando acessa o terminal shell do device. O xxx é o número do device listado com comando acima. Por padrão é o 5554.
    
- Acessar o diretório do banco de dados através do comando:
    - cd data/data/<your-package-name>/databases/. Onde o your-package-name é nome do seu pacote. Neste exemplo, o nome do pacote é: fatec.exemplo.sqlite
  
- Acessar o banco através do comando:
    - sqlite3 exemplo_cliente.db (onde exemplo_cliente.db é o nome do banco de dados criado no arquivo BancoDeDados.java
    - O prompt do sqlite será exibido dessa forma: sqlite>
    - Agora é só digitar comandos sql da tabela que deseja consultar. Exemplo: select * from cliente; Irá mostrar os dados inseridos na tabela.
    
    ![Imagem Select ADB - SQLite](https://github.com/damiana/app-crud-cliente-sqlite/blob/master/select_com_sqlite_adb.png)

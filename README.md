<h1>crud-gerencia</h1>

<p>O projeto se trata de um CRUD básico de cadastro e persistência de dados de veículos, marcas e clientes feito em linguagem Java.</p>

<p><strong>Para clonar rodar o projeto em sua máquina, é necessário ter instalado:</strong></p>
<ul>
  <li>Git</li>
  <li>Java 18</li>
  <li>Apache Netbeans IDE 8</li>
  <li>PostgreSQL</li>
  <li>pgAdmin 4</li>
</ul>

<p><strong>Após certificar-se de que todos os itens acima estão instalados e devidamente configurados, realize os seguintes passos:</strong></p>
<ol>
  <li>Clone o repositório para um diretório em sua máquina</li>
  <li>Abra a pasta "src". Dentro dela, abra a pasta "properties"</li>
  <li>
  Crie um arquivo "configuracaobd.properties" e insira os seguinte itens dentro dele:
    <ul>
      <li>jdbc.url=jdbc:postgresql://localhost:5432/trab03</li>
      <li>jdbc.username="Seu-username"</li>
      <li>jdbc.password="Sua-senha"</li>
      <li>jdbc.driver=org.postgresql.Driver</li>
    </ul>
  </li>
  <li>Abra novamente a pasta "src". Em seguida abra o arquivo "criacao_tabelas.sql"</li>
  <li>Copie todo o texto do arquivo</li>
  <li>Abra o pgAdmin 4 e faça login com suas credenciais</li>
  <li>Crie um novo banco de dados com o nome "trab03"</li>
  <li>Abra um editor de query neste banco e cole o texto do arquivo "criacao_tabelas.sql"</li>
  <li>Agora rode esta query. O banco de dados será criado</li>
  <li>Agora o projeto já pode ser iniciado e já está integrado com o banco de dados</li>
</ol>

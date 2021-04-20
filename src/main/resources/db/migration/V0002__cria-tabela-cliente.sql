create table cliente (
	id bigint not null auto_increment,
    endereco_id bigint not  null,
    nome varchar(80) not null,
    cpf_cnpj varchar(14) not null,
    tipo_pessoa int not null,
    telefone varchar(20) not null,
    email varchar(100) not null,
    
    primary key(id)
);
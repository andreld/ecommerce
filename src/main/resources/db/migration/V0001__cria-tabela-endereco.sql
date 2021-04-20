create table endereco (
	id bigint not null auto_increment,
    cep varchar(8) not null,
	numero int not null,
    rua varchar(100) not null,
    bairro varchar(100) not null,
    complemento varchar(100),
    cidade varchar(100) not null,
    estado varchar(100) not null,
    
    primary key(id)
);
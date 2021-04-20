create table estoque_produto(
	id bigint not null auto_increment,
    codigo_barras varchar(100) not null,
    descricao varchar(100) not null,
    quantidade int not null,
    valor decimal(10,2),
    
    primary key(id)
);
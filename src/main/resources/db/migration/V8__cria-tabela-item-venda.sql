create table item_venda(
	id bigint not null auto_increment,
    estoque_produto_id bigint not null,
    venda_id bigint not null,
    quantidade bigint not null,
   
    primary key(id)
);

alter table item_venda add constraint fk_item_venda_venda
foreign key(venda_id) references venda(id);

alter table item_venda add constraint fk_item_venda_estoque_produto
foreign key(estoque_produto_id) references estoque_produto(id);
create table venda(
	id bigint not null auto_increment,
    forma_pagamento int not null,
    numero_parcelas smallint not null,
    transportadora_id bigint not null,
    cliente_id bigint not null,
    loja_id bigint not null,
    valor_frete decimal(10,2) not null,
	valor_total_itens decimal(10,2),
    status int not null,
   
    primary key(id)
);

alter table venda add constraint fk_venda_transportadora
foreign key(transportadora_id) references transportadora(id);

alter table venda add constraint fk_venda_cliente
foreign key(cliente_id) references cliente(id);

alter table venda add constraint fk_venda_loja
foreign key(loja_id) references loja(id);
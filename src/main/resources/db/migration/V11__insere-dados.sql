update ecommerce.transportadora t
set t.valor_frete = 18.50
where t.id = 1;

update ecommerce.transportadora t
set t.valor_frete = 25.70
where t.id = 2;

insert into ecommerce.estoque_produto(codigo_barras, descricao, quantidade, valor)
values('101010101017', 'TV 42 pol HD', '45', 1099.78);

insert into ecommerce.estoque_produto(codigo_barras, descricao, quantidade, valor)
values('101010101013', 'Batedeira', '40', 99.50);

insert into ecommerce.estoque_produto(codigo_barras, descricao, quantidade, valor)
values('101010101014', 'Sofa 3 lugares', '45', 1200.00);

insert into ecommerce.estoque_produto(codigo_barras, descricao, quantidade, valor)
values('101010101015', 'Ferro de passar roupa', '45', 70.20);

insert into ecommerce.estoque_produto(codigo_barras, descricao, quantidade, valor)
values('101010101015', 'Microondas', '45', 700.00);

insert into ecommerce.estoque_produto(codigo_barras, descricao, quantidade, valor)
values('101010101016', 'Monitor Ultrawide', '45', 1255.40);

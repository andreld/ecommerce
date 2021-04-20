insert into ecommerce.endereco(cep, numero, rua, bairro, complemento, cidade, estado)
values ('65000000', 10, 'Rua E', 'Village Jardins II', 'Bloco C1, apto. 110', 'São Luís', 'Maranhão');

insert into ecommerce.endereco(cep, numero, rua, bairro, complemento, cidade, estado)
values ('65000000', 11, 'Rua 2', 'São Francisco', null, 'São Luís', 'Maranhão');

insert into ecommerce.cliente(endereco_id, nome, cpf_cnpj, tipo_pessoa, telefone, email)
values(1, 'Chico Ferreira',  '12345778900', 0, '98987654325', 'email5@email.com');

insert into ecommerce.cliente(endereco_id, nome, cpf_cnpj, tipo_pessoa, telefone, email)
values(2, 'Eurico Fernandes',  '12345678900', 0, '98987654321', 'email58@email.com');
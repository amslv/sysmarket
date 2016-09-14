INSERT INTO public.enderecos(id, cep, rua, numero, bairro, cidade_id, estado_id) VALUES (56, '58500-000', 'Rua da Igreja' ,20, 'Centro', 2683, 21);


INSERT INTO public.usuarios(id, nome, sobrenome, cpf, email, telefone, username, senha, tipo, endereco_id) VALUES (90, 'José', 'da Silva', '584.301.325-11', 'josesilva@gmail.com', '(90) 1234-5678', 'admin', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'ADMIN', 56);
INSERT INTO public.usuarios(id, nome, sobrenome, cpf, email, telefone, username, senha, tipo, endereco_id) VALUES (121, 'João', 'da Silva', '273.924.269-74', 'joaoestoque@gmail.com', '(90) 1234-5678', 'estoquista', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'ESTOQUISTA', 56);
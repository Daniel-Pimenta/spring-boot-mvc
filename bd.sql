drop table evento_convidado;
drop table convidado;
drop table evento;
drop table hibernate_sequence;

drop table usuario_role;
--drop table role_usuario;
drop table usuario;
drop table role;

insert into role (NOME_ROLE) VALUES ('ROLE_ADMIN');
insert into role (NOME_ROLE) VALUES ('ROLE_OPERATOR');
insert into role (NOME_ROLE) VALUES ('ROLE_USERS');

insert into usuario (login,nome,senha) values ('daniel.s.pimenta@gmail.com','Daniel S Pimenta','$2a$10$11dj4.TGL5XX2xSpte7ZS.K5u7xFaoblkokZuTlJr3D3ICD7LWBVG');
insert into usuario (login,nome,senha) values ('michelli.brito@gmail.com'  ,'Michelli Brito'  ,'$2a$10$11dj4.TGL5XX2xSpte7ZS.K5u7xFaoblkokZuTlJr3D3ICD7LWBVG');

insert into usuario_role(usuario_id, role_id) values ('daniel.s.pimenta@gmail.com','ROLE_ADMIN');
insert into usuario_role(usuario_id, role_id) values ('michelli.brito@gmail.com'  ,'ROLE_USERS');

insert into role_usuarios(role_nome_role, usuarios_login) values ('ROLE_ADMIN','daniel.s.pimenta@gmail.com');
insert into role_usuarios(role_nome_role, usuarios_login) values ('ROLE_USERS', 'michelli.brito@gmail.com');
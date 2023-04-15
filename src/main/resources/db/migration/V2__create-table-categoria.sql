create table categoria (

    id bigint not null auto_increment,
    titulo varchar(50) not null,
    cor varchar(50) not null,
    ativo tinyint not null,

    primary key (id)
    
);

insert into categoria (titulo, cor, ativo) values ('LIVRE', 'VERDE', 1);
create table video (

    id bigint not null auto_increment,
    titulo varchar(50) not null,
    descricao varchar(510) not null,
    url varchar(510) not null,
    ativo tinyint not null,

    primary key (id)

);
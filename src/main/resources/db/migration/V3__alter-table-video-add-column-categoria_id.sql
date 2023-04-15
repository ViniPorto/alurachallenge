alter table video add column categoria_id bigint;

alter table video add foreign key (categoria_id) references categoria(id);
drop table if exists client_entity_authorities;
drop table if exists client_entity_grant_types;
drop table if exists client_entity_redirect_uris;
drop table if exists client_entity_resources;
drop table if exists client_entity_scopes;
drop table if exists client_entity;
drop table if exists user_entity_authorities;
drop table if exists user_entity;

create table client_entity (client_id varchar(255) not null, client_secret varchar(255) not null, primary key (client_id));
create table client_entity_authorities (client_entity_client_id varchar(255) not null, authorities varchar(255));
create table client_entity_grant_types (client_entity_client_id varchar(255) not null, grant_types varchar(255));
create table client_entity_redirect_uris (client_entity_client_id varchar(255) not null, redirect_uris varchar(255));
create table client_entity_resources (client_entity_client_id varchar(255) not null, resources varchar(255));
create table client_entity_scopes (client_entity_client_id varchar(255) not null, scopes varchar(255));
create table user_entity (username varchar(255) not null, password varchar(255), primary key (username));
create table user_entity_authorities (user_entity_username varchar(255) not null, authorities varchar(255));

alter table client_entity_authorities add constraint FKbhsbvk2tu3xuvcycptcgrtkkq
  foreign key (client_entity_client_id) references client_entity(client_id);

alter table client_entity_grant_types add constraint FKpnxwp2c200212p7st4krhir31
  foreign key (client_entity_client_id) references client_entity(client_id);

alter table client_entity_redirect_uris add constraint FK41fv40sxhlsjiolmht1c8aieg
  foreign key (client_entity_client_id) references client_entity(client_id);

alter table client_entity_resources add constraint FK6a8uupuj023w3qwa1kxg2vjx8
  foreign key (client_entity_client_id) references client_entity(client_id);

alter table client_entity_scopes add constraint FKbksngnxrh0tuapdeplf9q0nrd
  foreign key (client_entity_client_id) references client_entity(client_id);

alter table user_entity_authorities add constraint FKkdb4v2f1d90cbc2gl63snbg12
  foreign key (user_entity_username) references user_entity(username);
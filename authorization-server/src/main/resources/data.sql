insert into user_entity(username, password) values ('user-a', '$2a$10$8I3dtrvldXfjG8bGSbSJXO7xQY7IkmFVUdsHM8FYb241IhkcfX5gy'); -- pasword '123456' encrypted by BCrypt;
insert into user_entity_authorities(user_entity_username, authorities) values ('user-a', 'ADMIN');

insert into client_entity(client_id, client_secret) values ('client-a', '$2a$10$8I3dtrvldXfjG8bGSbSJXO7xQY7IkmFVUdsHM8FYb241IhkcfX5gy'); -- pasword '123456' encrypted by BCrypt;
insert into client_entity_grant_types(client_entity_client_id, grant_types) values ('client-a', 'authorization_code');
insert into client_entity_redirect_uris(client_entity_client_id, redirect_uris) values ('client-a', 'http://localhost:8000');
insert into client_entity_scopes(client_entity_client_id, scopes) values ('client-a', 'read');
insert into client_entity_scopes(client_entity_client_id, scopes) values ('client-a', 'write');
insert into client_entity_scopes(client_entity_client_id, scopes) values ('client-a', 'other');

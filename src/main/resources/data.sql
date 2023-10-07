insert into userlab(name, second_name, email, username)
values('Joan Ramon', 'Roca', 'joanra@gmail.com', 'joanra');

insert into userlab(name, second_name, email, username)
values('Cristina', 'Garcia', 'tina@gmail.com', 'tina');

insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Lleida-Pirineus');
insert into station(latitud, longitud, nom) values('41.654221', '0.685937', 'Alcoletge');
insert into station(latitud, longitud, nom) values('41.687383', '0.72789', 'Vilanova de la Barca');
insert into station(latitud, longitud, nom) values('41.716451', '0.76295', 'Térmens');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Vallfogona de Balaguer');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Balaguer');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Gerb');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Sant Llorenç de Montgai');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Vilanova de la Sal');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Santa Linya');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Àger');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Cellers-Llimiana');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Guàrdia de Tremp');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Palau de Noguera');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Tremp');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Salàs de Pallars');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'La Pobla de Segur');

insert into journey(destination_nom,id, origin_nom) values('Lleida-Pirineus', '1','La Pobla de Segur');
insert into favorite_journey(journey_id, user_id) values('1', 'tina');
insert into day_time_start (day_of_week, id, time_start) values ('Monday','1', '12:51');
insert into day_time_start (day_of_week, id, time_start) values ('Tuesday','2', '12:30');

insert into friends(username) values('tina');
insert into friends(username) values('joanra');

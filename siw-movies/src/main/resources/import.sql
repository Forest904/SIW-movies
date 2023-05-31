
insert into artist (id, name, surname, nationality, date_of_birth) values(nextval('hibernate_sequence'), 'Tim', 'Burton', 'Italian','1958-08-25');
insert into artist (id, name, surname, nationality, date_of_birth) values(nextval('hibernate_sequence'), 'Himesh', 'Patel', 'Italian','1990-10-13');
insert into artist (id, name, surname, nationality, date_of_birth) values(nextval('hibernate_sequence'), 'Danny', 'Boyle', 'Italian','1956-10-20');
insert into artist (id, name, surname, nationality, date_of_birth) values(nextval('hibernate_sequence'), 'Tim', 'Roth', 'Italian','1961-05-14');
insert into artist (id, name, surname, nationality, date_of_birth) values(nextval('hibernate_sequence'), 'Michael', 'Keaton', 'Italian','1951-09-05');
insert into artist (id, name, surname, nationality, date_of_birth) values(nextval('hibernate_sequence'), 'Stanley', 'Kubrick', 'Italian','1928-07-26');
insert into artist (id, name, surname, nationality, date_of_birth) values(nextval('hibernate_sequence'), 'Ronald Lee', 'Ermey', 'Italian','1944-03-24');	
insert into artist (id, name, surname, nationality, date_of_birth) values(nextval('hibernate_sequence'), 'Claudio', 'Santamaria', 'Italian','1974-07-22');
insert into artist (id, name, surname, nationality, date_of_birth) values(nextval('hibernate_sequence'), 'Gabriele', 'Mainetti', 'Italian','1976-11-07');


insert into movie (id, title, url_image, YEAR, director_id) values(nextval('hibernate_sequence'), 'Full metal jacket', 'https://pad.mymovies.it/filmclub/2006/04/020/locandina.jpg', 1987,1);
insert into movie (id, title, url_image, YEAR) values(nextval('hibernate_sequence'), 'Non e'' un paese per vecchi', 'https://aforismi.meglio.it/img/film/Non_%C3%A8_un_paese_per_vecchi.jpg',2007);
insert into movie (id, title, url_image, YEAR) values(nextval('hibernate_sequence'), 'The founder', 'https://pad.mymovies.it/filmclub/2016/03/235/locandina.jpg',2016);
insert into movie (id, title, url_image, YEAR) values(nextval('hibernate_sequence'), 'Harry Potter e la pietra filosofale', 'https://pad.mymovies.it/filmclub/2001/12/001/locandina.jpg',2001);
insert into movie (id, title, url_image, YEAR) values(nextval('hibernate_sequence'), 'Il pianeta delle scimmie', 'https://media-assets.wired.it/photos/615daad62707bc568326abfa/master/w_1600,c_limit/war-for-the-planet-of-the-apes1.jpg',2001);
insert into movie (id, title, url_image, YEAR) values(nextval('hibernate_sequence'), 'Lo chiamavano Jeeg Robot', 'https://www.rai.it/dl/img/2016/02/23/1280x720_1456237082397_jeegrobot.jpg',2015);
insert into movie (id, title, url_image, YEAR) values(nextval('hibernate_sequence'), 'Yesterday', 'https://citynews-today.stgy.ovh/~media/horizontal-mid/20648620006811/yesterday-film-poster-movie-universal-pictures-2.jpg',2019);


INSERT INTO news(id, title, publishing_date, text) values(nextval('hibernate_sequence'), 'Elon Musk', '1976-11-07', 'Elon Musk dies on Mars' );
INSERT INTO news(id, title, publishing_date, text) values(nextval('hibernate_sequence'), 'Sus', '1976-11-07', 'Elon Musk dies on Mars' );
INSERT INTO news(id, title, publishing_date, text) values(nextval('hibernate_sequence'), 'Sas', '1976-11-07', 'Elon Musk dies on Mars' );
INSERT INTO news(id, title, publishing_date, text) values(nextval('hibernate_sequence'), 'Bab', '1976-11-07', 'Elon Musk dies on Mars' );
INSERT INTO news(id, title, publishing_date, text) values(nextval('hibernate_sequence'), 'Cat', '1976-11-07', 'Elon Musk dies on Mars' );
INSERT INTO news(id, title, publishing_date, text) values(nextval('hibernate_sequence'), 'Dogg', '1976-11-07', 'Elon Musk dies on Mars' );

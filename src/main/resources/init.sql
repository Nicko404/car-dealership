insert into categories(name) values ('Внедорожник');
insert into categories(name) values ('Седан');
insert into categories(name) values ('Кабриолет');

insert into car_showrooms(name, address) values ('Топ Тачки', 'Пионерская 45');
insert into car_showrooms(name, address) values ('Не Топ Тачки', 'Сурганова 40');
insert into car_showrooms(name, address) values ('Просто Тачки', 'Куйбышева 12');

insert into cars(brand, model, price, manufacture_date, category_id, car_showroom_id) values ('Porsche', '911 GTS', 100000, 2000, 3, 1);
insert into cars(brand, model, price, manufacture_date, category_id, car_showroom_id) values ('Porsche', 'Boxer', 120000, 2010, 3, 1);
insert into cars(brand, model, price, manufacture_date, category_id, car_showroom_id) values ('BMV', 'Trash', 1000, 2000, 3, 2);
insert into cars(brand, model, price, manufacture_date, category_id, car_showroom_id) values ('BMV', 'Trash', 1000, 2000, 3, 2);
insert into cars(brand, model, price, manufacture_date, category_id, car_showroom_id) values ('Tesla', 'Model S', 200000, 2020, 1, 3);
insert into cars(brand, model, price, manufacture_date, category_id, car_showroom_id) values ('Tesla', 'Model U', 150000, 2021, 1, 3);
insert into cars(brand, model, price, manufacture_date, category_id, car_showroom_id) values ('Audi', 'TT', 50000, 2012, 1, 3);
insert into cars(brand, model, price, manufacture_date, category_id, car_showroom_id) values ('Audi', 'TT', 5000, 1999, 1, 3);

insert into clients(name, registration_date) values ('Иванов Иван Иванович', '2015-11-02');
insert into clients(name, registration_date) values ('Николаев Николай Николаевич', '2015-11-02');
insert into clients(name, registration_date) values ('Надежда Вера Геннадьевна', '2015-11-02');
insert into clients(name, registration_date) values ('Фамилия Имя Отчество', '2015-11-02');

insert into client_contacts(client_id, contact) values(1, 'ivanov@email.com');
insert into client_contacts(client_id, contact) values(1, '+375442347745');
insert into client_contacts(client_id, contact) values(2, 'nicko@email.com');
insert into client_contacts(client_id, contact) values(3, '+375296661245');
insert into client_contacts(client_id, contact) values(4, 'surname@email.com');

insert into reviews(rating, content, car_id, client_id) values (5, 'This car is AWESOME', 1, 2);
insert into reviews(rating, content, car_id, client_id) values (1, 'This car is really peace of shit', 3, 1);
insert into reviews(rating, content, car_id, client_id) values (5, 'I`m 3 years old and I like to eat trash', 4, 3);
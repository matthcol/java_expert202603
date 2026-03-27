INSERT INTO department (id, code, name) VALUES (1000000000000031, '31', 'Haute Garonne');
INSERT INTO department (id, code, name) VALUES (1000000000000082, '82', 'Tarn et Garonne');
INSERT INTO city(name, zipcode, population, department_id) VALUES ('Toulouse', '31000', 500000, 1000000000000031);
INSERT INTO city(name, zipcode, population, department_id) VALUES ('Montauban', '82000', 68000, 1000000000000082);
INSERT INTO city(name, zipcode, population, department_id) VALUES ('Aucamville', '82600', 1500, 1000000000000082);
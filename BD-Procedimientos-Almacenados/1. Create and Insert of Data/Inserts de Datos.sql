INSERT INTO Provincia (desc_provincia) VALUES ('Lima');
INSERT INTO Provincia (desc_provincia) VALUES ('Cusco');
INSERT INTO Provincia (desc_provincia) VALUES ('Arequipa');
INSERT INTO Provincia (desc_provincia) VALUES ('Trujillo');

INSERT INTO USUARIO (USERNAME, PASSWORD, ROL) 
VALUES ('ADMIN', '123456', 'ADMIN');

INSERT INTO Distrito (id_provincia, desc_distrito) VALUES (1, 'Miraflores');
INSERT INTO Distrito (id_provincia, desc_distrito) VALUES (1, 'San Isidro');
INSERT INTO Distrito (id_provincia, desc_distrito) VALUES (2, 'Cusco Centro');
INSERT INTO Distrito (id_provincia, desc_distrito) VALUES (3, 'Cayma');
INSERT INTO Distrito (id_provincia, desc_distrito) VALUES (3, 'Yanahuara');
-- En Trujillo
INSERT INTO Distrito (id_provincia, desc_distrito) VALUES (4, 'Centro Histórico');
INSERT INTO Distrito (id_provincia, desc_distrito) VALUES (4, 'Víctor Larco');

INSERT INTO Sede (desc_sede) VALUES ('Sede Central');
INSERT INTO Sede (desc_sede) VALUES ('Sede Regional');
INSERT INTO Sede (desc_sede) VALUES ('Sede Norte');
INSERT INTO Sede (desc_sede) VALUES ('Sede Sur');

INSERT INTO Gerente (desc_gerente) VALUES ('Juan Pérez');
INSERT INTO Gerente (desc_gerente) VALUES ('María López');
INSERT INTO Gerente (desc_gerente) VALUES ('Pedro Ramírez');
INSERT INTO Gerente (desc_gerente) VALUES ('Gabriela Suárez');

INSERT INTO Condicion (desc_condicion) VALUES ('Operativo');
INSERT INTO Condicion (desc_condicion) VALUES ('Mantenimiento');
INSERT INTO Condicion (desc_condicion) VALUES ('Expansión');
INSERT INTO Condicion (desc_condicion) VALUES ('Cerrado Temporalmente');


INSERT INTO Hospital (id_distrito, nombre, antiguedad, area, id_sede, id_gerente, id_condicion) 
VALUES (1, 'Hospital Nacional', 15, 2500.50, 1, 1, 1);


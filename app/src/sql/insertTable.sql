begin;

-- Inserção de dados na tabela DISPOSITIVO
INSERT INTO DISPOSITIVO (noserie, latitude, longitude, bateria)
VALUES
  (1, -23.5678, -46.7890, 80),
  (2, -22.3456, -47.1234, 95),
  (3, -24.9876, -45.6789, 60),
  (4, -50.6954, 62.2559, 88),
  (5, -87.6545, -88.7536, 48);

-- Inserção de dados na tabela BICICLETA
INSERT INTO BICICLETA (id, peso, raio, modelo, marca, mudanca, estado, atrdisc, dispositivo)
VALUES
  (1, 15.5, 18, 'Modelo-A', 'Marca-A', 6, 'livre', 'C', 1),
  (2, 20.3, 23, 'Modelo-B', 'Marca-A', 1, 'em reserva', 'E', 2),
  (3, 18.0, 20, 'Modelo-A', 'Marca-B', 18, 'em reserva', 'C', 3),
  (4, 19.0, 21, 'Modelo-B', 'Marca-A', 1, 'livre', 'E', 4),
  (5, 18.69, 18, 'Modelo-B', 'Marca-B', 1, 'livre', 'C', 5);

-- Inserção de dados na tabela CLASSICA
INSERT INTO CLASSICA (bicicleta)
VALUES
  (1),
  (5),
  (3);

-- Inserção de dados na tabela ELETRICA
INSERT INTO ELETRICA (bicicleta, autonomia, velocidade)
VALUES
  (4, 40, 25),
  (2, 30, 20);

-- Inserção de dados na tabela PESSOA
INSERT INTO PESSOA (id, nome, morada, email, telefone, noident, nacionalidade, atrdisc)
VALUES
  (1, 'João Pessoa', 'Rua A, 123', 'joao@email.com', '123456789', '123456789012', 'Portuguesa', 'C'),
  (2, 'John', 'Rua B, 456', 'maria@email.com', '987654321', '987654321012', 'Portuguesa', 'G'),
  (3, 'Carlos Oliveira', 'Rua C, 789', 'carlos@email.com', '654321789', '654321789012', 'Brasileira', 'C'),
  (4, 'Antonio Morais', 'Rua D, 564', 'antonio@email.com', '987456321', '963258741951', 'Portuguesa', 'C'),
  (5, 'João Felipe', 'Rua R, 668', 'julia@email.com', '951368425', '987653214786', 'Brasileira', 'C'),
  (6, 'João Maria', 'Rua ABC, 111', 'joaom@email.com', '159876543', '78965412258', 'Portuguesa', 'G'),
  (7, 'José Manuel', 'Rua X, 354', 'jose@email.com', '654831297', '354987621354', 'Portuguesa', 'C');

-- Inserção de dados na tabela LOJA
INSERT INTO LOJA (codigo, email, endereco, localidade, gestor)
VALUES
  (101, 'loja1@email.com', 'Av. Principal, 789', 'Lisboa', 1),
  (102, 'loja2@email.com', 'Rua Secundária, 456', 'Cidade B', 2),
  (103, 'loja3@email.com', 'Praça Central, 123', 'Lisboa', 5),
  (104, 'loja4@email.com', 'Rua Test, 598', 'Cidade R', 6);

-- Inserção de dados na tabela RESERVA
INSERT INTO RESERVA (noreserva, loja, dtinicio, dtfim, valor, bicicleta)
VALUES
  (1, 101, '2090-11-25 10:00:00', '2095-11-25 12:00:00', 15.00, 1),
  (1, 102, '2090-11-26 14:00:00', '2095-11-26 16:00:00', 20.00, 2),
  (2, 101, '2090-11-24 15:00:00', '2095-12-24 18:00:00', 175.00, 4),
  (1, 103, '2090-11-27 09:00:00', '2095-11-27 11:00:00', 25.00, 4),
  (4, 101, '2090-11-27 09:00:00', '2095-11-27 11:00:00', 25.00, 1),
  (5, 101, '2090-11-27 09:00:00', '2095-11-27 11:00:00', 25.00, 2),
  (6, 101, '2090-11-27 09:00:00', '2095-11-27 11:00:00', 25.00, 5),
  (2, 102, '2090-11-26 18:00:00', '2095-12-24 18:00:00', 175.00, 2),
  (2, 103, '2090-12-26 12:00:00', '2095-01-07 09:00:00', 100.00, 1),
  (3, 101, '2090-12-26 12:00:00', '2095-01-07 09:00:00', 100.00, 3);

-- Inserção de dados na tabela TELEFONELOJA
INSERT INTO TELEFONELOJA (loja, numero)
VALUES
  (101, '987654321'),
  (102, '123456789'),
  (103, '555555555');

-- Inserção de dados na tabela CLIENTERESERVA
INSERT INTO CLIENTERESERVA (cliente, reserva, loja)
VALUES
  (1, 1, 101),
  (5, 1, 102),
  (3, 1, 103),
  (4, 2, 101),
  (7, 2, 102),
  (3, 4, 101),
  (4, 5, 101),
  (5, 6, 101),
  (1, 3, 101),
  (7, 2, 103);

commit;


rollback;
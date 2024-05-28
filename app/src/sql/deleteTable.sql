begin;

delete from    CLIENTERESERVA;
delete from    TELEFONELOJA;
delete from    RESERVA;
delete from    LOJA;
delete from    PESSOA;
delete from    ELETRICA;
delete from    CLASSICA;
delete from    BICICLETA;
delete from    DISPOSITIVO;
/*

a versao em portugues dizia que era suposto apagar a informaçao das tabelas e as proprias tabelas,
mas a versao do enunciado em ingles so dizia que era para apagar a informaçao das tabelas.
Nos decidimos fazer ambas, mas comentamos a parte de apagar as tabelas uma vez que achamos
que nao faz muito sentido estar a fazer o mesmo que o script removeTable

drop view if exists BICICLETASEMNUMERO;
drop view if exists LISTAJOAOFILIPE;
drop table if exists CLIENTERESERVA;
drop table if exists TELEFONELOJA;
drop table if exists RESERVA;
drop table if exists LOJA;
drop table if exists PESSOA;
drop table if exists ELETRICA;
drop table if exists CLASSICA;
drop table if exists BICICLETA;
drop table if exists DISPOSITIVO;
*/

commit;
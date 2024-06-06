drop procedure if exists make_reservation;

CREATE OR REPLACE PROCEDURE make_reservation(
    IN p_loja INTEGER,
    IN p_dtinicio VARCHAR(50),
    IN p_dtfim VARCHAR(50),
    IN p_valor NUMERIC,
    IN p_bicicleta INTEGER
)
    LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO RESERVA(loja, dtinicio, dtfim, valor, bicicleta)
    VALUES (p_loja, p_dtinicio::timestamp, p_dtfim::timestamp, p_valor, p_bicicleta);
END;
$$;

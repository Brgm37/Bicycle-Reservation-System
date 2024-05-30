CREATE OR REPLACE PROCEDURE make_reservation(
    p_loja INTEGER,
    p_dtinicio VARCHAR(50),
    p_dtfim VARCHAR(50),
    p_valor NUMERIC,
    p_bicicleta INTEGER
)
    LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO RESERVA(loja, dtinicio, dtfim, valor, bicicleta)
    VALUES (p_loja, p_dtinicio, p_dtfim, p_valor, p_bicicleta);
END;
$$;
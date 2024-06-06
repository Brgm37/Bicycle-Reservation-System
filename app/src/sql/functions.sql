CREATE OR REPLACE FUNCTION check_bike_availability(bike_id INTEGER, check_time VARCHAR(50))
    RETURNS BOOLEAN AS $$
DECLARE
    is_available BOOLEAN;
BEGIN
    SELECT COUNT(*) = 0 INTO is_available
    FROM Reserva
    WHERE bicicleta = bike_id AND
        (dtinicio <= to_date(check_time, 'YYYY-MM-DD') AND dtfim >= to_date(check_time, 'YYYY-MM-DD'));

    RETURN is_available;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_cliente_reserva()
    RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM clientereserva
    WHERE reserva = OLD.noreserva and loja = OLD.loja;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION add_reserva()
    RETURNS TRIGGER AS $$
DECLARE
    total_bikes INTEGER;
    rented_bikes INTEGER;
BEGIN
    SELECT COUNT(*) INTO total_bikes
    FROM Bicicleta
    WHERE estado = 'livre';

    SELECT COUNT(*) INTO rented_bikes
    FROM Bicicleta
    WHERE estado = 'em reserva';

    IF rented_bikes < total_bikes*0.1 THEN
        RAISE EXCEPTION 'Unable to add reservation. rented bikes = %, total bikes = %', rented_bikes, total_bikes;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
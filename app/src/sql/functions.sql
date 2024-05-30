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

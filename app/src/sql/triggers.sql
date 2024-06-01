CREATE OR REPLACE TRIGGER delete_cliente_reserva
    BEFORE DELETE ON reserva
    FOR EACH ROW
EXECUTE FUNCTION delete_cliente_reserva();


CREATE OR REPLACE TRIGGER delete_cliente_reserva
    BEFORE DELETE ON reserva
    FOR EACH ROW
EXECUTE FUNCTION delete_cliente_reserva();

CREATE OR REPLACE TRIGGER add_reserva
    BEFORE INSERT ON reserva
    FOR EACH ROW
EXECUTE FUNCTION add_reserva();

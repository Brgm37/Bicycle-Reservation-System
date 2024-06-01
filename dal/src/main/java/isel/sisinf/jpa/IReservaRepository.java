package isel.sisinf.jpa;

import isel.sisinf.model.EntityClass.Reserva;
import isel.sisinf.model.EntityClass.ReservaKey;

import java.util.Collection;

public interface IReservaRepository extends IRepository<Reserva, Collection<Reserva>, ReservaKey>, IDataMapper<Reserva> {
    void makeBooking(Reserva reserva);
}

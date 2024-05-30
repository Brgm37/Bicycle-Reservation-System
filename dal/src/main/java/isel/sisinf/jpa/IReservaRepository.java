package isel.sisinf.jpa;

import isel.sisinf.model.EntityClass.Reserva;

import java.util.Collection;

public interface IReservaRepository extends IRepository<Reserva, Collection<Reserva>, Integer>, IDataMapper<Reserva> {

}

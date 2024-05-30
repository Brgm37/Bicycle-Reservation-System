package isel.sisinf.jpa;

import isel.sisinf.model.EntityClass.Bicicleta;

import java.util.Collection;

public interface IBicicletaRepository extends IRepository<Bicicleta, Collection<Bicicleta>, Integer>, IDataMapper<Bicicleta> {
    boolean availability(int id, String date);
}

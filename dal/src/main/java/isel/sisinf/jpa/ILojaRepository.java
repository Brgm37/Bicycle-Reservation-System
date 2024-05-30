package isel.sisinf.jpa;

import isel.sisinf.model.EntityClass.Loja;

import java.util.Collection;

public interface ILojaRepository extends IRepository<Loja, Collection<Loja>, Integer>, IDataMapper<Loja>{

}

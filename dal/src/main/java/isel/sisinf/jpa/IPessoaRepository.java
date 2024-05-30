package isel.sisinf.jpa;

import isel.sisinf.model.EntityClass.Pessoa;

import java.util.Collection;

public interface IPessoaRepository extends IRepository<Pessoa, Collection<Pessoa>, Integer>, IDataMapper<Pessoa> {

}

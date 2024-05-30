
package isel.sisinf.jpa;

public interface IContext extends AutoCloseable {

	 enum IsolationLevel {READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE }
	 void beginTransaction();
	 void beginTransaction(IsolationLevel isolationLevel);
	 void commit();
	 void flush();
	 void clear();
	 void persist(Object entity);

	 IPessoaRepository getPessoas();
	 IBicicletaRepository getBicicletas();
	 IReservaRepository getReservas();
	 ILojaRepository getLojas();

}

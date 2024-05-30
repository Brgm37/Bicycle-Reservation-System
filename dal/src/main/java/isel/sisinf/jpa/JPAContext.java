/*
MIT License

Copyright (c) 2022-2024, Nuno Datia, ISEL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package isel.sisinf.jpa;

import java.util.Collection;

import isel.sisinf.model.EntityClass.Bicicleta;
import isel.sisinf.model.EntityClass.Pessoa;
import isel.sisinf.model.EntityClass.Reserva;
import org.eclipse.persistence.sessions.DatabaseLogin;
import org.eclipse.persistence.sessions.Session;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class JPAContext implements IContext {

	private EntityManagerFactory _emf;
    private EntityManager _em;
    private EntityTransaction _tx;
    private int _txcount;

	private final IPessoaRepository _pessoaRepository;
	private final IBicicletaRepository _bicicletaRepository;
	private final IReservaRepository _reservaRepository;
    
/// HELPER METHODS    
    protected Collection helperQueryImpl(String jpql, Object... params) {
    	Query q = _em.createQuery(jpql);

		for(int i = 0; i < params.length; ++i)
			q.setParameter(i+1, params[i]);
		
		return q.getResultList();
    }
    
    protected Object helperCreateImpl(Object entity) {
    	beginTransaction();
		_em.persist(entity);
		commit();
		return entity;
    }
    
    protected Object helperUpdateImpl(Object entity) {
    	beginTransaction();
		_em.merge(entity);
		commit();
		return entity;	
    }
    
    protected Object helperDeleteImpl(Object entity) {
    	beginTransaction();
		_em.remove(entity);
		commit();
		return entity;
    }

	protected class PessoaRepository implements IPessoaRepository {
		@Override
		public Pessoa create(Pessoa entity) {
			return (Pessoa)helperCreateImpl(entity);
		}

		@Override
		public Pessoa update(Pessoa entity) {
			return (Pessoa)helperUpdateImpl(entity);
		}

		@Override
		public Pessoa delete(Pessoa entity) {
			return (Pessoa)helperDeleteImpl(entity);
		}

		@SuppressWarnings("unchecked")
		@Override
		public Collection<Pessoa> find(String jpql, Object... params) {
			return helperQueryImpl(jpql, params);
		}

		@Override
		public Pessoa findByKey(Integer key) {
			return _em.createNamedQuery("Pessoa.findByKey", Pessoa.class)
					.setParameter("key", key)
					.getSingleResult();
		}
	}

	protected class BicicletaRepository implements IBicicletaRepository {

		@Override
		public Bicicleta create(Bicicleta entity) {
			return (Bicicleta)helperCreateImpl(entity);
		}

		@Override
		public Bicicleta update(Bicicleta entity) {
			return (Bicicleta)helperUpdateImpl(entity);
		}

		@Override
		public Bicicleta delete(Bicicleta entity) {
			return (Bicicleta)helperDeleteImpl(entity);
		}

		@Override
		public Bicicleta findByKey(Integer key) {
			return _em.createNamedQuery("Bicicleta.findByKey", Bicicleta.class)
					.setParameter("key", key)
					.getSingleResult();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Collection<Bicicleta> find(String jpql, Object... params) {
			return helperQueryImpl(jpql, params);
		}
	}

	protected class ReservaRepository implements IReservaRepository {

		@Override
		public Reserva create(Reserva entity) {
			return (Reserva)helperCreateImpl(entity);
		}

		@Override
		public Reserva update(Reserva entity) {
			return (Reserva)helperUpdateImpl(entity);
		}

		@Override
		public Reserva delete(Reserva entity) {
			return (Reserva)helperDeleteImpl(entity);
		}

		@Override
		public Reserva findByKey(Integer key) {
			return _em.createNamedQuery("Reserva.findByKey", Reserva.class)
					.setParameter("key", key)
					.getSingleResult();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Collection<Reserva> find(String jpql, Object... params) {
			return helperQueryImpl(jpql, params);
		}
	}
    
	@Override
	public void beginTransaction() {
		if(_tx == null)
		{
			_tx = _em.getTransaction();
			_tx.begin();
			_txcount=0;
		}
		++_txcount;
	}
	
	@Override
	public void beginTransaction(IsolationLevel isolationLevel) {
		beginTransaction();
		Session session =_em.unwrap(Session.class);
		DatabaseLogin databaseLogin = (DatabaseLogin) session.getDatasourceLogin();
		System.out.println(databaseLogin.getTransactionIsolation());
		
		int isolation = DatabaseLogin.TRANSACTION_READ_COMMITTED;
		if(isolationLevel == IsolationLevel.READ_UNCOMMITTED)
			isolation = DatabaseLogin.TRANSACTION_READ_UNCOMMITTED;
		else if(isolationLevel == IsolationLevel.REPEATABLE_READ)
			isolation = DatabaseLogin.TRANSACTION_REPEATABLE_READ;
		else if(isolationLevel == IsolationLevel.SERIALIZABLE)
			isolation = DatabaseLogin.TRANSACTION_SERIALIZABLE;
		
		databaseLogin.setTransactionIsolation(isolation);
	}

	@Override
	public void commit() {
		
		--_txcount;
		if(_txcount==0 && _tx != null)
		{
			_em.flush();
			_tx.commit();
			_tx = null;
		}
	}

	@Override
	public void flush() {
		_em.flush();
	}


	@Override
	public void clear() {
		_em.clear();
	}

	@Override
	public void persist(Object entity) {
		_em.persist(entity);
		
	}

	@Override
	public IPessoaRepository getPessoas() {
		return _pessoaRepository;
	}

	@Override
	public IBicicletaRepository getBicicletas() {
		return _bicicletaRepository;
	}

	@Override
	public IReservaRepository getReservas() {
		return _reservaRepository;
	}

	public JPAContext() {
		this("dal-lab");
	}
	
	public JPAContext(String persistentCtx) {
		super();
		this._emf = Persistence.createEntityManagerFactory(persistentCtx);
		this._em = _emf.createEntityManager();
		this._pessoaRepository = new PessoaRepository();
        this._bicicletaRepository = new BicicletaRepository();
		this._reservaRepository = new ReservaRepository();
	}

	@Override
	public void close() throws Exception {
        if(_tx != null)
        	_tx.rollback();
        _em.close();
        _emf.close();
	}


}

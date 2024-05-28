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
package isel.sisinf.dal.lab.repo;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.eclipse.persistence.sessions.DatabaseLogin;
import org.eclipse.persistence.sessions.Session;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import isel.sisinf.dal.lab.repo.IContext;


public class JPAContext implements IContext {

	private EntityManagerFactory _emf;
    private EntityManager _em;
    private EntityTransaction _tx;
    private int _txcount;
    
    //private ICountryRepository _countryRepository;
//    private IStudentRepository _studentRepository;
//    private ICourseRepository _courseRepository;

	private IPessoaRepository _pessoaRepository;
    
/// HELPER METHODS    
    protected List helperQueryImpl(String jpql, Object... params)
    {
    	Query q = _em.createQuery(jpql);

		for(int i = 0; i < params.length; ++i)
			q.setParameter(i+1, params[i]);
		
		return q.getResultList();
    }
    
    protected Object helperCreateImpl(Object entity)
    {
    	beginTransaction();
		_em.persist(entity);
		commit();
		return entity;
    }
    
    protected Object helperUpdateImpl(Object entity)
    {
    	beginTransaction();
		_em.merge(entity);
		commit();
		return entity;	
    }
    
    protected Object helperDeleteImpl(Object entity)
    {
    	beginTransaction();
		_em.remove(entity);
		commit();
		return entity;
    }

    /*protected class StudentRepository implements IStudentRepository
    {

		@Override
		public Student findByKey(Integer key) {
			return _em.createNamedQuery("Student.findByKey",Student.class)
					 .setParameter("key", key)
	            	  .getSingleResult();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Collection<Student> find(String jpql, Object... params) {
			
			return helperQueryImpl( jpql, params);
		}
    	
		@Override
		public Collection<Student> getEnrolledStudents(Course c){
			return _em.createNamedQuery("Student.EnrolledInCourse",Student.class)
			 .setParameter("key", c.getCourseId())
			 .getResultList();
		}

		@Override
		public Student create(Student entity) {
			return (Student)helperCreateImpl(entity);
		}

		@Override
		public Student update(Student entity) {
			return (Student)helperUpdateImpl(entity);
		}

		@Override
		public Student delete(Student entity) {
			return (Student)helperDeleteteImpl(entity);
		}
		
    }*/

    
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
	public void beginTransaction(IsolationLevel isolationLevel) 
	{
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

	public JPAContext() {
		this("dal-lab");
	}
	
	public JPAContext(String persistentCtx) 
	{
		super();
	
		this._emf = Persistence.createEntityManagerFactory(persistentCtx);
		this._em = _emf.createEntityManager();
		//this._countryRepository = new CountryRepository();
//		this._studentRepository = new StudentRepository();
//		this._courseRepository = new CourseRepository();
	}

	@Override
	public void close() throws Exception {
		
        if(_tx != null)
        	_tx.rollback();
        _em.close();
        _emf.close();
	}

	//@Override
	public ICountryRepository getCountries() {
		//return _countryRepository;
		return null; //TODO
	}

	@Override
	public IStudentRepository getStudents() {
		
		return _studentRepository;
	}
	@Override
	public ICourseRepository getCourses() {
		return _courseRepository;
	}
	// functions and stored procedure

	public java.math.BigDecimal rand_fx(int seed) {
	
		StoredProcedureQuery namedrand_fx = 
		          _em.createNamedStoredProcedureQuery("namedrand_fx");
		namedrand_fx.setParameter(1, seed);
		namedrand_fx.execute();
		
		return (java.math.BigDecimal)namedrand_fx.getOutputParameterValue(2);
	}

}

package adminhome;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import authentication.Dolgozo;
import authentication.LoginBean;
import backend.Kikire;
import backend.Pontok;
import newfile.DbManager;

@Stateless
public class AdminManager {

	@PersistenceContext(unitName="Szavazas")
	private EntityManager em;
	
	@Inject
	private LoginBean lb;
	
	@Inject
	private DbManager dm;
	
	public List<Object[]> getPontok(){
		Query query;
		query=em.createNativeQuery("SELECT d.nev,d.torzsszam,SUM(p.kapott) as kap FROM pontok p JOIN dolgozo d ON p.adoszam=d.adoszam group by p.adoszam ORDER BY kap DESC");
        return query.getResultList();
	}
	
	public List<Object[]> getKikire(){
		 Query query;
		 query=em.createNativeQuery("SELECT ki,kire FROM kikire ORDER BY ki ASC;");
		 return query.getResultList();
	}
	
	public Levon saveLevon(Levon l){
		em.persist(l);
		return l;
	}
	
	public Levon mergeLevon(Levon l){
		em.merge(l);
		return l;
	}
}

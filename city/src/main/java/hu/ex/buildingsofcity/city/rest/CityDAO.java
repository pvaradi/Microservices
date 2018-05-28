package hu.ex.buildingsofcity.city.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@RequestScoped
public class CityDAO {
    
    @PersistenceContext(unitName = "cityPU")
    EntityManager em;

    public List<City> get(int pStart, int pCount, String pOrderField, String pOrderDirection ) throws Exception{
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(City.class);
        Root<City> root = query.from(City.class);
        
        if(pOrderField !=null && !pOrderField.isEmpty() && 
            pOrderDirection !=null && !pOrderDirection.isEmpty()
        ){
            if("asc".equals(pOrderDirection.toLowerCase()))
                query.orderBy(builder.asc(root.get(pOrderField)));
            else 
                query.orderBy(builder.desc(root.get(pOrderField)));
        }
        
        return em.createQuery(query).setFirstResult(pStart).setMaxResults(pCount).getResultList();
    }
    
    
    public City getByName(String pName) throws Exception{
        return (City) em.createNamedQuery("City.getByName").setParameter("name", pName).getSingleResult();
    }
    
    public void add(City pCity){
        em.persist(pCity);
    }
    
    public City findById(long id) {
        return em.find(City.class, id);
    }
    
    public void delete(String pid){
        City city = findById(Long.valueOf(pid));
        if (city != null) {
            em.remove(city);
        }
    }
    
    public void modify(City updatedCity){
            City oldCity = (City) em.createNamedQuery("City.getByName").setParameter("name", updatedCity.getName()).getSingleResult();
            oldCity.setName(updatedCity.getName());
            oldCity.setDescription(updatedCity.getDescription());
            em.persist(oldCity);
    }
    
}

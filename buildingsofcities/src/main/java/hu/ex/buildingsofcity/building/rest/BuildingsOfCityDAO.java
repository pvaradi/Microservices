package hu.ex.buildingsofcity.building.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@RequestScoped
public class BuildingsOfCityDAO {
    
    @PersistenceContext(unitName = "buildingsofcityPU")
    EntityManager em;

    public List<BuildingsOfCity> get(int pStart, int pCount, String pOrderField, String pOrderDirection ) throws Exception{
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(BuildingsOfCity.class);
        Root<BuildingsOfCity> root = query.from(BuildingsOfCity.class);
        
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
    
    
    public BuildingsOfCity getByName(String pName) throws Exception{
        return (BuildingsOfCity) em.createNamedQuery("BuildingsOfCity.getByName").setParameter("name", pName).getSingleResult();
    }
    
    public void add(BuildingsOfCity pBuildingsOfCity){
        em.persist(pBuildingsOfCity);
    }
    
    public BuildingsOfCity findById(long id) {
        return em.find(BuildingsOfCity.class, id);
    }
    
    public void delete(String pid){
        BuildingsOfCity buildingsofcity = findById(Long.valueOf(pid));
        if (buildingsofcity != null) {
            em.remove(buildingsofcity);
        }
    }

    public void modify(String pid, String newdescription){
            BuildingsOfCity buildingsofcity = findById(Long.valueOf(pid));
            if (buildingsofcity != null) {
                buildingsofcity.setDescription(newdescription);
                em.persist(buildingsofcity);
            }
    }
    
}










    /*
    public void modify(BuildingsOfCity updatedBuildingsOfCity){
            BuildingsOfCity buildingsofcity = (BuildingsOfCity) em.createNamedQuery("BuildingsOfCity.getByName").setParameter("name", updatedBuildingsOfCity.getName()).getSingleResult();
            buildingsofcity.setName(updatedBuildingsOfCity.getName());
            buildingsofcity.setDescription(updatedBuildingsOfCity.getDescription());
            em.persist(buildingsofcity);
    }
    */
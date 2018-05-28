package hu.ex.buildingsofcity.building.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@RequestScoped
public class BuildingDAO {
    
    @PersistenceContext(unitName = "buildingPU")
    EntityManager em;

    public List<Building> get(int pStart, int pCount, String pOrderField, String pOrderDirection ) throws Exception{
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Building.class);
        Root<Building> root = query.from(Building.class);
        
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
    
    
    public Building getByName(String pName) throws Exception{
        return (Building) em.createNamedQuery("Building.getByName").setParameter("name", pName).getSingleResult();
    }
    
    public void add(Building pBuilding){
        em.persist(pBuilding);
    }
    
    public Building findById(long id) {
        return em.find(Building.class, id);
    }
    
    public void delete(String pid){
        Building building = findById(Long.valueOf(pid));
        if (building != null) {
            em.remove(building);
        }
    }
    
    public void modify(Building updatedBuilding){
            Building oldBuilding = (Building) em.createNamedQuery("Building.getByName").setParameter("name", updatedBuilding.getName()).getSingleResult();
            oldBuilding.setName(updatedBuilding.getName());
            oldBuilding.setDescription(updatedBuilding.getDescription());
            em.persist(oldBuilding);
    }
    
}

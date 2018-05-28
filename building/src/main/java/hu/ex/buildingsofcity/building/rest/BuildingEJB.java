package hu.ex.buildingsofcity.building.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BuildingEJB {
    @Inject BuildingDAO dao;
    
    
    private static List<String> ORDER_FIELDNAMES =new ArrayList<>(Arrays.asList(new String[]{"name","description","",null})) ;
    private static List<String> ORDER_DIRECTIONS =new ArrayList<>(Arrays.asList(new String[]{"asc","desc","",null})) ;

    public List<Building> get(int pStart, int pCount, String pOrderField, String pOrderDirection ) throws Exception{
        if(pStart<0 || pCount <1 || !ORDER_DIRECTIONS.contains(pOrderDirection) || !ORDER_FIELDNAMES.contains(pOrderField))
            throw new Exception("Param Error");
        return dao.get(pStart, pCount, pOrderField, pOrderDirection);
    }
    
    private boolean isNameOK(String pName){
       try{
            dao.getByName(pName);
            return false;
       }
       catch(Exception e) {return true;}
    }
    
    public Building add(Building pBuilding) throws Exception{
        if(isNameOK(pBuilding.getName())){
            dao.add(pBuilding);
            return pBuilding;
        }
        throw new Exception();
    }
    
    public void delete(String pid) throws Exception{
            dao.delete(pid);
    }
    
    public Building modify(Building pBuilding) throws Exception{
        if(isExist(pBuilding.getName())){
            dao.modify(pBuilding);
            return pBuilding;
        }
        throw new Exception();
    }
    
    private boolean isExist(String pName){
       try{
            dao.getByName(pName);
            return true;
       }
       catch(Exception e) {return false;}
    }
    
    
}

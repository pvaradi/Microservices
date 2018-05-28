package hu.ex.buildingsofcity.building.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BuildingsOfCityEJB {
    @Inject BuildingsOfCityDAO dao;
    
    
    private static List<String> ORDER_FIELDNAMES =new ArrayList<>(Arrays.asList(new String[]{"name","description","",null})) ;
    private static List<String> ORDER_DIRECTIONS =new ArrayList<>(Arrays.asList(new String[]{"asc","desc","",null})) ;

    public List<BuildingsOfCity> get(int pStart, int pCount, String pOrderField, String pOrderDirection ) throws Exception{
        if(pStart<0 || pCount <1 || !ORDER_DIRECTIONS.contains(pOrderDirection) || !ORDER_FIELDNAMES.contains(pOrderField))
            throw new Exception("Param Error");
        return dao.get(pStart, pCount, pOrderField, pOrderDirection);
    }
    
    public BuildingsOfCity add(BuildingsOfCity pBuildingsOfCity){
            dao.add(pBuildingsOfCity);
            return pBuildingsOfCity;
    }
    
    public void delete(String pid) throws Exception{
            dao.delete(pid);
    }
    
    public void modify(String pid, String newdescription){
            dao.modify(pid,newdescription);
    }
    
    private boolean isExist(String pName){
       try{
            dao.getByName(pName);
            return true;
       }
       catch(Exception e) {return false;}
    }
}










/*
    public BuildingsOfCity modify(BuildingsOfCity pBuildingsOfCity) throws Exception{
        if(isExist(pBuildingsOfCity.getName())){
            dao.modify(pBuildingsOfCity);
            return pBuildingsOfCity;
        }
        throw new Exception();
    }
*/
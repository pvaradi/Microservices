package hu.ex.buildingsofcity.building.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/buildingsofcity")
@Api("Épületek és Városok kezelését megvalósító erőforrás")
public class BuildingsOfCityResource {

    @EJB BuildingsOfCityEJB service;
    
	@GET
        //POSTMAN - GET - localhost:8082/buildingsofcity?
	@Produces(MediaType.APPLICATION_JSON)
        @ApiOperation("Épületek és Városok listázása")
	public List<BuildingsOfCity> get(
                @ApiParam("kezdeti index") @DefaultValue("0") @QueryParam("start") int pStart,
                @ApiParam("darab szám") @DefaultValue("100") @QueryParam("count") int pCount,
                @ApiParam("rendezési mező név") @DefaultValue("name") @QueryParam("orderfield") String pOrderField,
                @ApiParam("rendezési irány") @DefaultValue("asc") @QueryParam("orderdirection") String pOrderDirection        
        ) {
            try{
		return service.get(pStart, pCount, pOrderField, pOrderDirection);
            }
            catch(Throwable e){
                e.printStackTrace();
                return new  ArrayList<>();
            }
	}
        
        @POST
        //POSTMAN - POST - Body - x-www-form-urlencoded
        // URL - localhost:8082/buildingsofcity?
        // Key - name - valuenal név
        // Key - description - valuenal leiras
	@Produces(MediaType.APPLICATION_JSON)
        @ApiOperation("Új épület és város felvitele")
        public Response add(
                @ApiParam("neve") @FormParam("name") @DefaultValue("") String pName,
                @ApiParam("leírása") @FormParam("description") @DefaultValue("") String pDescription
        ){
            BuildingsOfCity buildingsofcity = new BuildingsOfCity();
            buildingsofcity.setName(pName);
            buildingsofcity.setDescription(pDescription);
            try{
                return Response.ok().entity(service.add(buildingsofcity)).build();
            }
            catch(Throwable e){
                e.printStackTrace();
                return Response.status(444).build();
            }
        }
        
        @DELETE
        //POSTMAN - DELETE
        //localhost:8082/buildingsofcity?deleteId=201
	@Produces(MediaType.APPLICATION_JSON)
        @ApiOperation("Épület és város törlése")
        public Response delete(@QueryParam(value = "deleteId") String deleteId)
        {
            try{
                service.delete(deleteId);
                return Response.ok("Törlés sikeres!").build();
            }
            catch(Throwable e){
                e.printStackTrace();
                return Response.status(444).build();
            }
        }
        
        @PUT
        //POSTMAN - PUT - Body - x-www-form-urlencoded
        // URL - localhost:8082/buildingsofcity?
        // Key - nevmodosit - valuenal id
        // Key - descmodosit - valuenal leiras
	@Produces(MediaType.APPLICATION_JSON)
        @ApiOperation("Épület és város módosítása")
        public Response modify(
                @ApiParam("idm") @FormParam("idmodosit") @DefaultValue("") String mId,
                @ApiParam("descm") @FormParam("descmodosit") @DefaultValue("") String mDescription
        ){
            try{
                service.modify(mId,mDescription);
                return Response.ok("Módosítás sikeres!").build();
            }
            catch(Throwable e){
                e.printStackTrace();
                return Response.status(444).build();
            }
        }
}





















/*
        @PUT
        //POSTMAN - PUT - Body - x-www-form-urlencoded
        // URL - localhost:8082/buildingsofcity?
        // Key - nevmodosit - valuenal név
        // Key - descmodosit - valuenal leiras
	@Produces(MediaType.APPLICATION_JSON)
        @ApiOperation("Épület és város módosítása")
        public Response modify(
                @ApiParam("nevm") @FormParam("nevmodosit") @DefaultValue("") String mName,
                @ApiParam("descm") @FormParam("descmodosit") @DefaultValue("") String mDescription
        ){
            BuildingsOfCity buildingsofcity = new BuildingsOfCity();
            buildingsofcity.setName(mName);
            buildingsofcity.setDescription(mDescription);
            try{
                return Response.ok().entity(service.modify(buildingsofcity)).build();
            }
            catch(Throwable e){
                e.printStackTrace();
                return Response.status(444).build();
            }
        }
        */
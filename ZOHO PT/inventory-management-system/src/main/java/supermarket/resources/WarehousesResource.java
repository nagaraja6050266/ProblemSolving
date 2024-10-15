package supermarket.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import supermarket.dao.DaoDistributor;
import supermarket.dao.AdjustedItemsDao;
import supermarket.dao.WarehousesDao;
import supermarket.models.Warehouse;
import supermarket.publicUtilities.Utilities;

import java.sql.SQLException;
import java.util.List;

@Path("/warehouses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WarehousesResource {

    WarehousesDao warehousesDao = DaoDistributor.getWarehousesDao();
    AdjustedItemsDao adjustedItemsDao =DaoDistributor.getExpiredItemsDao();


    public WarehousesResource() throws SQLException {
    }

    @GET
    public Response getAllWarehouses() {
        try {
            List<Warehouse> warehouses = warehousesDao.getAllWarehouses();
            return Response.ok(warehouses).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @GET
    @Path("/{itemId}")
    public Response getWarehouseById(@PathParam("itemId") int id) {
        try {
            return Response.ok(warehousesDao.getWarehouseById(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @POST
    public Response createWarehouse(Warehouse warehouse) {
        try {
            return Response.ok(warehousesDao.addWarehouse(warehouse)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @PUT
    @Path("/{itemId}")
    public Response editWarehouse(@PathParam("itemId") int id, Warehouse warehouse) {
        try {
            return Response.ok(warehousesDao.editWarehouse(id, warehouse) == 1 ? warehouse : Utilities.jsonMessage("Nothing Edited")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @DELETE
    @Path("/{itemId}")
    public Response deleteWarehouse(@PathParam("itemId") int id) {
        try {
            return Response.ok(Utilities.jsonMessage("Deleted " + warehousesDao.deleteWarehouse(id) + " warehouses")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @GET
    @Path("{warehouseId}/items")
    public Response getWarehouseItems(@PathParam("warehouseId") int warehouseId){
        try{
            return Response.ok(warehousesDao.getWarehouseItems(warehouseId)).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @GET
    @Path("/items")
    public Response getAllWarehouseItems(){
        try{
            return Response.ok(warehousesDao.getWarehouseItems()).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @GET
    @Path("/items/expiredGoods")
    public Response getExpiredItems(){
        try{
            return Response.ok(adjustedItemsDao.reviewExpiredBatches()).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Utilities.jsonMessage("Error: "+e.toString()))
                    .build();
        }
    }
}

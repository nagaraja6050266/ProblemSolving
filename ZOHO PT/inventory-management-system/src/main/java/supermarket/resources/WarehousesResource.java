package supermarket.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import supermarket.dao.DaoDistributor;
import supermarket.dao.WarehousesDao;
import supermarket.models.Warehouse;
import supermarket.publicUtilities.Utilities;

import java.util.List;

@Path("/warehouses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WarehousesResource {

    WarehousesDao warehousesDao = DaoDistributor.getWarehousesDao();

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
}

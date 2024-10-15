package supermarket.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import supermarket.dao.DaoDistributor;
import supermarket.dao.ItemsDao;
import supermarket.dao.PurchasesDao;
import supermarket.dto.ItemDto;
import supermarket.dto.ItemWarehouseDto;
import supermarket.models.Item;
import supermarket.models.Purchase;
import supermarket.publicUtilities.Utilities;

import java.sql.SQLException;
import java.util.List;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemsResource {

    ItemsDao itemsDao = DaoDistributor.getItemsDao();

    public ItemsResource() throws SQLException {
    }

    @GET
    public Response getAllItems() {
        try {
            List<Item> items = itemsDao.getAllItems();
            return Response.ok(items).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @GET
    @Path("/{itemId}")
    public Response getItemById(@PathParam("itemId") int id) {
        try {
            return Response.ok(itemsDao.getItemById(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @POST
    public Response createItem(ItemWarehouseDto itemWarehouseDto) {
        try {
            return Response.ok(itemsDao.addItem(itemWarehouseDto)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @PUT
    @Path("/{itemId}")
    public Response editItem(@PathParam("itemId") int id, Item item) {
        try {
            return Response.ok(itemsDao.editItem(id, item) == 1 ? item : Utilities.jsonMessage("Nothing Edited")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @DELETE
    @Path("/{itemId}")
    public Response deleteItem(@PathParam("itemId") int id) {
        try {
            return Response.ok(Utilities.jsonMessage("Deleted " + itemsDao.deleteItem(id) + " items")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

}

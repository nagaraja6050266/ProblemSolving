package org.inr.tutorial.messenger.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.inr.tutorial.messenger.dao.ItemsDao;
import org.inr.tutorial.messenger.model.Item;

import java.sql.SQLException;
import java.util.List;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemsResource {

    ItemsDao itemsDao = new ItemsDao();

    @GET
    public Response getAllItems() {
        try {
            List<Item> items = itemsDao.getAllItems();
            return Response.ok(items).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
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
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

    @POST
    public Response createItem(Item item) {
        try {
            return Response.ok(itemsDao.addItem(item)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

    @PUT
    @Path("/{itemId}")
    public Response editItem(@PathParam("itemId") int id, Item item) {
        try {
            return Response.ok(itemsDao.editItem(id, item)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

    @DELETE
    @Path("/{itemId}")
    public Response deleteItem(@PathParam("itemId") int id) {
        try {
            return Response.ok(itemsDao.deleteItem(id) >= 1 ? "Deleted" : "Not Deleted").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

}

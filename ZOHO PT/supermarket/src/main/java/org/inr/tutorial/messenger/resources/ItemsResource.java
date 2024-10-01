package org.inr.tutorial.messenger.resources;

//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import org.inr.tutorial.messenger.model.Message;
//
//import java.util.List;
//
//
//@Path("/messages")
//public class ItemsServer {
//
//    MessageDao messageDao = new MessageDao();
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Message> getAllMessages() {
//        return messageDao.getAllMessages();
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/{messageId}")
//    public Message getMessage(@PathParam("messageId") int id){
//        return messageDao.getMessageById(id);
//    }
//
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Message postMessage(Message message) {
//        return messageDao.addMessage(message);
//    }
//
//    @PUT
//    @Path("/{messageId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Message editMessage(@PathParam("messageId") int id,Message message) {
//        message.setId(id);
//        return messageDao.editMessage(id,message);
//    }
//
//    @DELETE
//    @Path("/{messageId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Message deleteMessage(@PathParam("messageId") int id){
//        return messageDao.deleteMessage(id);
//    }
//
//    /*
//        @Path, @Matrix,
//        @Cookies,Headers, in seperate
//        Motthama edukka @Context, @UriInfo, @Bean
//
//        Subresources by leaving instance alone in parent resource
//
//     */
//
//}

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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

    @POST
    public Response createItem(Item item) {
        try {
            return Response.ok(itemsDao.addItem(item)).build();
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

}

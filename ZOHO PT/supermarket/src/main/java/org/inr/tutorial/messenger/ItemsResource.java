package org.inr.tutorial.messenger;

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

import java.util.List;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemsResource {

    ItemsDao itemsDao = new ItemsDao();

    @GET
    public Response getAllItems() {
        List<Item> items = itemsDao.getAllItems();
        if (items == null) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("No items in Database")
                    .build();
        }
        return Response.ok(items).build();
    }

    @GET
    @Path("/{itemId}")
    public Response getItemById(@PathParam("itemId") int id) {
        Item item = itemsDao.getItemById(id);
        if (item == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Item with id " + id + " not found")
                    .build();
        }
        return Response.ok(item).build();
    }

    @POST
    public Response createItem(Item item) {
        if (item == null || item.getName() == null || item.getPrice() == 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Incorrect input")
                    .build();
        }
        return Response.ok(itemsDao.addItem(item)).build();
    }

    @PUT
    @Path("/{itemId}")
    public Response editItem(@PathParam("itemId") int id, Item item) {
        Item editedItem = itemsDao.editItem(id, item);
        if (editedItem == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Item with id " + id + " not found")
                    .build();
        }
        return Response.ok(editedItem).build();
    }

    @DELETE
    @Path("/{itemId}")
    public Response deleteItem(@PathParam("itemId") int id) {
        Item item = itemsDao.deleteItem(id);
        if (item == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Item with id " + id + " not found")
                    .build();
        }
        return Response.ok(item).build();
    }

}

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
import org.inr.tutorial.messenger.dao.ItemsDao;
import org.inr.tutorial.messenger.model.Item;

import java.util.List;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemsResource {

    ItemsDao itemsDao = new ItemsDao();

    @GET
    public List<Item> getAllItems() {
        return itemsDao.getAllItems();
    }

    @GET
    @Path("/{itemId}")
    public Item getItemById(@PathParam("itemId") int id){
        return itemsDao.getItemById(id);
    }

    @POST
    public Item createItem(Item item){
        return itemsDao.addItem(item);
    }

    @PUT
    @Path("/{itemId}")
    public Item editItem(@PathParam("itemId") int id,Item item){
        return itemsDao.editItem(id,item);
    }

    @DELETE
    @Path("/{itemId}")
    public Item deleteItem(@PathParam("itemId") int id){
        return itemsDao.deleteItem(id);
    }

}

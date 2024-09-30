package org.inr.tutorial.messenger;

import com.sun.research.ws.wadl.Param;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.inr.tutorial.messenger.dao.MessageDao;
import org.inr.tutorial.messenger.model.Message;

import java.util.List;


@Path("/messages")
public class Server {

    MessageDao messageDao = new MessageDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") int id){
        return messageDao.getMessageById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Message postMessage(Message message) {
        return messageDao.addMessage(message);
    }

    @PUT
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Message editMessage(@PathParam("messageId") int id,Message message) {
        message.setId(id);
        return messageDao.editMessage(id,message);
    }

    @DELETE
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Message deleteMessage(@PathParam("messageId") int id){
        return messageDao.deleteMessage(id);
    }

    /*
        @Path, @Matrix,
        @Cookies,Headers, in seperate
        Motthama edukka @Context, @UriInfo, @Bean

        Subresources by leaving instance alone in parent resource

     */

}

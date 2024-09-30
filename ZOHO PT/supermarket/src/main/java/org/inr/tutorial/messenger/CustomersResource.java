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
import org.inr.tutorial.messenger.dao.CustomersDao;
import org.inr.tutorial.messenger.model.Customer;

import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomersResource {

    CustomersDao customersDao = new CustomersDao();

    @GET
    public List<Customer> getAllCustomers() {
        return customersDao.getAllCustomers();
    }

    @GET
    @Path("/{customerId}")
    public Customer getCustomerById(@PathParam("customerId") int id){
        return customersDao.getCustomerById(id);
    }

    @POST
    public Customer createCustomer(Customer customer){
        return customersDao.addCustomer(customer);
    }

    @PUT
    @Path("/{customerId}")
    public Customer editCustomer(@PathParam("customerId") int id,Customer customer){
        return customersDao.editCustomer(id,customer);
    }

    @DELETE
    @Path("/{customerId}")
    public Customer deleteCustomer(@PathParam("customerId") int id){
        return customersDao.deleteCustomer(id);
    }

}

package supermarket.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import supermarket.dao.CustomersDao;
import supermarket.dao.DaoDistributor;
import supermarket.models.Customer;
import supermarket.publicUtilities.Utilities;

import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomersResource {

    CustomersDao customersDao = DaoDistributor.getCustomersDao();

    @GET
    public Response getAllCustomers() {
        try {
            List<Customer> customers = customersDao.getAllCustomers();
            return Response.ok(customers).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @GET
    @Path("/{customerId}")
    public Response getCustomerById(@PathParam("customerId") int id) {
        try {
            Customer customer = customersDao.getCustomerById(id);
            return Response.ok(customer).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Utilities.jsonMessage(e.toString()))
                    .build();
        }
    }

    @POST
    public Response createCustomer(Customer customer) {
        try {
            return Response.ok(customersDao.addCustomer(customer)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error " + e.toString())
                    .build();
        }
    }

    @PUT
    @Path("/{customerId}")
    public Response editCustomer(@PathParam("customerId") int id, Customer customer) {
        try {
            return Response.ok(customersDao.editCustomer(id, customer) >= 1 ? customer : Utilities.jsonMessage("Nothing Edited")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @DELETE
    @Path("/{customerId}")
    public Response deleteCustomer(@PathParam("customerId") int id) {
        try {
            return Response.ok(customersDao.deleteCustomer(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

}

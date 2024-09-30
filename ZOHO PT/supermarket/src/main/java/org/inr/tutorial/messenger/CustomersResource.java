package org.inr.tutorial.messenger;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.inr.tutorial.messenger.dao.CustomersDao;
import org.inr.tutorial.messenger.model.Customer;

import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomersResource {

    CustomersDao customersDao = new CustomersDao();

    @GET
    public Response getAllCustomers() {
        List<Customer> customers = customersDao.getAllCustomers();
        if (customers == null) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("No customers in Database")
                    .build();
        }
        return Response.ok(customers).build();
    }

    @GET
    @Path("/{customerId}")
    public Response getCustomerById(@PathParam("customerId") int id) {
        Customer customer = customersDao.getCustomerById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Customer with id " + id + " not found")
                    .build();
        }
        return Response.ok(customer).build();
    }

    @POST
    public Response createCustomer(Customer customer) {
        if (customer == null || customer.getName() == null || customer.getPhNumber() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Incorrect input")
                    .build();
        }
        return Response.ok(customersDao.addCustomer(customer)).build();
    }

    @PUT
    @Path("/{customerId}")
    public Response editCustomer(@PathParam("customerId") int id, Customer customer) {
        Customer editedCustomer = customersDao.editCustomer(id, customer);
        if (editedCustomer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Customer with id " + id + " not found")
                    .build();
        }
        return Response.ok(editedCustomer).build();
    }

    @DELETE
    @Path("/{customerId}")
    public Response deleteCustomer(@PathParam("customerId") int id) {
        Customer customer = customersDao.deleteCustomer(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Customer with id " + id + " not found")
                    .build();
        }
        return Response.ok(customer).build();
    }

}

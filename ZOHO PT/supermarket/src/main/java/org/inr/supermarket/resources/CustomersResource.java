package org.inr.supermarket.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.inr.supermarket.dao.CustomersDao;
import org.inr.supermarket.dao.InvoicesDao;
import org.inr.supermarket.models.Customer;
import org.inr.supermarket.models.Invoice;

import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomersResource {

    CustomersDao customersDao = new CustomersDao();
    InvoicesDao invoicesDao = new InvoicesDao();

    @GET
    public Response getAllCustomers() {
        try {
            List<Customer> customers = customersDao.getAllCustomers();
            return Response.ok(customers).build();
        } catch (Exception e) {
            System.out.println(e.toString());
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("Error: " + e.toString())
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
            System.out.println(e.toString());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Error " + e.toString())
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
            return Response.ok(customersDao.editCustomer(id, customer) >= 1 ? customer : "Nothing Edited").build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Error: " + e.toString())
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
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

    @Path("/{customerId}/invoices")
    @GET
    public Response getCustomerInvoices(@PathParam("customerId") int customerId) {
        try {
            List<Invoice> customerInvoices = invoicesDao.getAllInvoices(customerId);
            if (customerInvoices.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No invoices exist")
                        .build();
            }
            return Response.ok(customerInvoices).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error " + e.toString())
                    .build();
        }
    }

}

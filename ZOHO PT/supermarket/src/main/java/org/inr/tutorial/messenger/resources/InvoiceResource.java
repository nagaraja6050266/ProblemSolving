package org.inr.tutorial.messenger.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.inr.tutorial.messenger.dao.InvoicesDao;
import org.inr.tutorial.messenger.model.Invoice;

//import java.sql.Exception;
import java.util.List;

@Path("/invoices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvoiceResource {

    InvoicesDao invoicesDao = new InvoicesDao();

    @GET
    public Response getAllInvoices() {
        try {
            List<Invoice> invoiceList = invoicesDao.getAllInvoices();
            if (invoiceList == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No invoice found")
                        .build();
            }
            return Response.ok(invoiceList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

    @GET
    @Path("/{invoiceId}")
    public Response getInvoiceById(@PathParam("invoiceId") int id) {
        try {
            Invoice invoice = invoicesDao.getInvoiceById(id);
            if (invoice == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No Item found with id " + id)
                        .build();
            }
            return Response.ok(invoice).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

    @POST
    public Response createInvoice(Invoice invoice) {
        try {
            return Response.ok(invoicesDao.addInvoice(invoice)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

    @PUT
    @Path("/{invoiceId}")
    public Response editInvoice(@PathParam("invoiceId") int id, Invoice invoice) {
        try {
            return Response.ok(invoicesDao.editInvoice(id, invoice)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

    @DELETE
    @Path("/{invoiceId}")
    public Response deleteInvoice(@PathParam("invoiceId") int id) {
        try {
            return Response.ok(invoicesDao.deleteInvoice(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

}


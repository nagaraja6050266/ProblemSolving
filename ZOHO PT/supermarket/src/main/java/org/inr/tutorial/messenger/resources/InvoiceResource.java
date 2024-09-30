package org.inr.tutorial.messenger.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.inr.tutorial.messenger.dao.InvoicesDao;
import org.inr.tutorial.messenger.model.Invoice;

import java.util.List;

@Path("/invoices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvoiceResource {

    InvoicesDao invoicesDao = new InvoicesDao();

    @GET
    public Response getAllInvoices() {
        List<Invoice> invoices = invoicesDao.getAllInvoices();
        if (invoices.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("No invoices in Database")
                    .build();
        }
        return Response.ok(invoices).build();
    }

    @GET
    @Path("/{invoiceId}")
    public Response getInvoiceById(@PathParam("invoiceId") int id) {
        Invoice invoice = invoicesDao.getInvoiceById(id);
        if (invoice == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Invoice with id " + id + " not found")
                    .build();
        }
        return Response.ok(invoice).build();
    }

    @POST
    public Response createInvoice(Invoice invoice) {
        if (invoice == null || invoice.getCustomerId() == 0 || invoice.getPurchases() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Incorrect input")
                    .build();
        }
        return Response.ok(invoicesDao.addInvoice(invoice)).build();
    }

    @PUT
    @Path("/{invoiceId}")
    public Response editInvoice(@PathParam("invoiceId") int id, Invoice invoice) {
        Invoice editedInvoice = invoicesDao.editInvoice(id, invoice);
        if (editedInvoice == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Invoice with id " + id + " not found")
                    .build();
        }
        return Response.ok(editedInvoice).build();
    }

    @DELETE
    @Path("/{invoiceId}")
    public Response deleteInvoice(@PathParam("invoiceId") int id) {
        Invoice invoice = invoicesDao.deleteInvoice(id);
        if (invoice == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Invoice with id " + id + " not found")
                    .build();
        }
        return Response.ok(invoice).build();
    }

}


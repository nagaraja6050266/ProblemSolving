package supermarket.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import supermarket.dao.DaoDistributor;
import supermarket.dao.InvoicesDao;
import supermarket.models.Invoice;
import supermarket.models.Payment;

//import java.sql.Exception;
import java.util.List;

@Path("/invoices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvoiceResource {

    InvoicesDao invoicesDao = DaoDistributor.getInvoicesDao();

    @GET
    public Response getAllInvoices() {
        try {
            List<Invoice> invoiceList = invoicesDao.getAllInvoices();
            if (invoiceList.isEmpty()) {
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
            return Response.ok("Deleted " + invoicesDao.deleteInvoice(id) + " invoices").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

    @POST
    @Path("/{invoiceId}/payment")
    public Response recordPayment(@PathParam("invoiceId") int id, Payment payment) {
        try {
            return Response.ok(invoicesDao.recordPayment(id, payment)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }
}


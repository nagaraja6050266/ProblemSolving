package supermarket.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import supermarket.dao.DaoDistributor;
import supermarket.dao.InvoicesDao;
import supermarket.models.Payment;
import supermarket.dao.PaymentsDao;

import java.util.List;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentsResource {

    PaymentsDao paymentsDao = DaoDistributor.getPaymentsDao();
    InvoicesDao invoicesDao = DaoDistributor.getInvoicesDao();

    @GET
    public Response getAllPayments() {
        try {
            List<Payment> payments = paymentsDao.getAllPayments();
            return Response.ok(payments).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

    @GET
    @Path("/{paymentId}")
    public Response getPaymentById(@PathParam("paymentId") int id) {
        try {
            return Response.ok(paymentsDao.getPaymentById(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

    @POST
    public Response createPayment(Payment payment) {
        try {
            return Response.ok(invoicesDao.recordPayment(payment.getInvoiceId(),payment)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

    @PUT
    @Path("/{paymentId}")
    public Response editPayment(@PathParam("paymentId") int id, Payment payment) {
        try {
            return Response.ok(paymentsDao.editPayment(id, payment)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

    @DELETE
    @Path("/{paymentId}")
    public Response deletePayment(@PathParam("paymentId") int id) {
        try {
            return Response.ok( "{\"message\":\"Deleted " +paymentsDao.deletePayment(id)+" payment\"").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.toString())
                    .build();
        }
    }

}

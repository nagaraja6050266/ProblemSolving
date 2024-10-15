package supermarket.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import supermarket.dao.PurchasesDao;
import supermarket.dao.DaoDistributor;
import supermarket.dto.PurchaseDto;
import supermarket.models.Purchase;
import supermarket.publicUtilities.Utilities;

import java.sql.SQLException;
import java.util.List;

@Path("/purchases")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PurchasesResource {

    PurchasesDao purchasesDao = DaoDistributor.getPurchasesDao();

    public PurchasesResource() throws SQLException {
    }

    @GET
    public Response getAllPurchases() {
        try {
            List<PurchaseDto> purchases = purchasesDao.getAllPurchases();
            return Response.ok(purchases).build();
        } catch (Exception e) {
            return Response.status(500)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }
//
//    @GET
//    @Path("/{purchaseId}")
//    public Response getPurchaseById(@PathParam("purchaseId") int id) {
//        try {
//            Purchase purchase = purchasesDao.getPurchaseById(id);
//            return Response.ok(purchase).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity("Error " + e.toString())
//                    .build();
//        }
//    }

    @POST
    public Response createPurchase(Purchase purchase) {
        try {
            return Response.ok(purchasesDao.createPurchase(purchase)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error " + e.toString())
                    .build();
        }
    }

//    @PUT
//    @Path("/{purchaseId}")
//    public Response editPurchase(@PathParam("purchaseId") int id, Purchase purchase) {
//        try {
//            return Response.ok(purchasesDao.editPurchase(id, purchase) >= 1 ? purchase : "Nothing Edited").build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
//                    .build();
//        }
//    }
//
//    @DELETE
//    @Path("/{purchaseId}")
//    public Response deletePurchase(@PathParam("purchaseId") int id) {
//        try {
//            return Response.ok(purchasesDao.deletePurchase(id)).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
//                    .build();
//        }
//    }

}

package supermarket.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import supermarket.dao.SalesDao;
import supermarket.dao.DaoDistributor;
import supermarket.dto.SaleDto;
import supermarket.models.Sale;
import supermarket.publicUtilities.Utilities;

import java.sql.SQLException;
import java.util.List;

@Path("/sales")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SalesResource {

    SalesDao salesDao = DaoDistributor.getSalesDao();

    public SalesResource() throws SQLException {
    }

    @GET
    public Response getAllSales() {
        try {
            return Response.ok(salesDao.getAllSales()).build();
        } catch (Exception e) {
            return Response.status(404)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }
//
//    @GET
//    @Path("/{saleId}")
//    public Response getSaleById(@PathParam("saleId") int id) {
//        try {
//            Sale sale = salesDao.getSaleById(id);
//            return Response.ok(sale).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity("Error " + e.toString())
//                    .build();
//        }
//    }

    @POST
    public Response createSale(Sale sale) {
        try {
            return Response.ok(salesDao.createSales(sale)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error " + e.toString())
                    .build();
        }
    }

//    @PUT
//    @Path("/{saleId}")
//    public Response editSale(@PathParam("saleId") int id, Sale sale) {
//        try {
//            return Response.ok(salesDao.editSale(id, sale) >= 1 ? sale : "Nothing Edited").build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
//                    .build();
//        }
//    }
//
//    @DELETE
//    @Path("/{saleId}")
//    public Response deleteSale(@PathParam("saleId") int id) {
//        try {
//            return Response.ok(salesDao.deleteSale(id)).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
//                    .build();
//        }
//    }

}

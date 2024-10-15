package supermarket.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import supermarket.dao.DaoDistributor;
import supermarket.dao.AdjustedItemsDao;
import supermarket.dto.ExpiredBatch;
import supermarket.publicUtilities.Utilities;

import java.sql.SQLException;

@Path("adjustedGoods")
public class AdjustmentResource {

    private final AdjustedItemsDao adjustedItemsDao = DaoDistributor.getExpiredItemsDao();

    public AdjustmentResource() throws SQLException {
    }

    @POST
    public Response adjustItems(ExpiredBatch batch){
        try{
            adjustedItemsDao.adjust(batch);
            return Response.ok(Utilities.jsonMessage("Items Adjusted")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Utilities.jsonMessage("Error: "+e.toString()))
                    .build();
        }
    }

    @GET
    public Response getAdjustedItems(){
        try{
            return Response.ok(adjustedItemsDao.getAllAdjustedItems()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Utilities.jsonMessage("Error: "+e.toString()))
                    .build();
        }
    }

}


package supermarket.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import supermarket.dao.SuppliersDao;
import supermarket.dao.DaoDistributor;
import supermarket.models.Supplier;
import supermarket.publicUtilities.Utilities;

import java.util.List;

@Path("/suppliers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SuppliersResource {

    SuppliersDao suppliersDao = DaoDistributor.getSuppliersDao();

    @GET
    public Response getAllSuppliers() {
        try {
            List<Supplier> suppliers = suppliersDao.getAllSuppliers();
            return Response.ok(suppliers).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @GET
    @Path("/{supplierId}")
    public Response getSupplierById(@PathParam("supplierId") int id) {
        try {
            Supplier supplier = suppliersDao.getSupplierById(id);
            return Response.ok(supplier).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Error " + e.toString())
                    .build();
        }
    }

    @POST
    public Response createSupplier(Supplier supplier) {
        try {
            return Response.ok(suppliersDao.addSupplier(supplier)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error " + e.toString())
                    .build();
        }
    }

    @PUT
    @Path("/{supplierId}")
    public Response editSupplier(@PathParam("supplierId") int id, Supplier supplier) {
        try {
            return Response.ok(suppliersDao.editSupplier(id, supplier) >= 1 ? supplier : "Nothing Edited").build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @DELETE
    @Path("/{supplierId}")
    public Response deleteSupplier(@PathParam("supplierId") int id) {
        try {
            return Response.ok(suppliersDao.deleteSupplier(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

}

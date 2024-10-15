package supermarket.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import supermarket.dao.CategoriesDao;
import supermarket.dao.DaoDistributor;
import supermarket.dao.ItemsDao;
import supermarket.models.Category;
import supermarket.models.Item;
import supermarket.publicUtilities.Utilities;

import java.util.List;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriesResource {

    CategoriesDao categoriesDao = DaoDistributor.getCategoriesDao();
    ItemsDao itemsDao = DaoDistributor.getItemsDao();

    @GET
    public Response getAllCategories() {
        try {
            List<Category> categories = categoriesDao.getAllCategories();
            return Response.ok(categories).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @GET
    @Path("/{categoryId}")
    public Response getCategoryById(@PathParam("categoryId") int id) {
        try {
            Category category = categoriesDao.getCategoryById(id);
            return Response.ok(category).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Error " + e.toString())
                    .build();
        }
    }

    @POST
    public Response createCategory(Category category) {
        try {
            return Response.ok(categoriesDao.addCategory(category)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error " + e.toString())
                    .build();
        }
    }

    @PUT
    @Path("/{categoryId}")
    public Response editCategory(@PathParam("categoryId") int id, Category category) {
        try {
            return Response.ok(categoriesDao.editCategory(id, category) >= 1 ? category : "Nothing Edited").build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @DELETE
    @Path("/{categoryId}")
    public Response deleteCategory(@PathParam("categoryId") int id) {
        try {
            return Response.ok(Utilities.jsonMessage("Deleted " + categoriesDao.deleteCategory(id) + " category")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

    @GET
    @Path("/{categoryId}/items")
    public Response getItemsByCategory(@PathParam("categoryId") int categoryID){
        try {
            List<Item> items = itemsDao.getAllItems(categoryID);
            return Response.ok(items).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity(Utilities.jsonMessage("Error: " + e.toString()))
                    .build();
        }
    }

}

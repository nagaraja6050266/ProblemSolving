package supermarket.dao;

import supermarket.database.Database;
import supermarket.dto.SaleDto;
import supermarket.models.LineItem;
import supermarket.models.Sale;
import supermarket.publicUtilities.Utilities;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalesDao {

    private final Connection conn = Database.getConnection();
    private BatchesDao batchesDao = DaoDistributor.getBatchesDao();
    private ItemsDao itemsDao = DaoDistributor.getItemsDao();

    public SalesDao() throws SQLException {
    }

    public List<SaleDto> getAllSales() throws SQLException{
        List<SaleDto> sales=new ArrayList<>();
        String query="select s.salesId,s.date,s.customerId,s.totalAmount,si.itemId,si.quantity,si.amount,c.customerName from sales as s join salesItems as si on si.salesId=s.salesId join customers as c on c.customerId=s.customerId order by salesId";
        PreparedStatement stmt= conn.prepareStatement(query);
        ResultSet rs=stmt.executeQuery();
        int prevId=-1;
        SaleDto currSale=new SaleDto();

        while(rs.next()){
            if(prevId!=rs.getInt("salesId")){
                if(prevId!=-1){
                    sales.add(currSale);
                }
                currSale=new SaleDto(Utilities.createSale(rs),rs.getString("customerName"));
            }
            currSale.addItem(Utilities.createLineItem(rs));
            prevId=rs.getInt("salesId");
        }

        sales.add(currSale);
        return sales;
    }

    public Sale createSales(Sale sale) throws SQLException {

        LocalDate localDate = LocalDate.now();
        sale.setDate(Date.valueOf(localDate));
        float totalAmount = 0;

//      Insert sale table
        sale.setSalesId(insertSales(sale.getCustomerId()));

        List<LineItem> updatedLineItems = new ArrayList<>();
        for (LineItem lineItem : sale.getSalesItems()) {

//          Search in batches table and decrease quantity
            batchesDao.getItemsFromBatch(lineItem.getItemId(), lineItem.getQuantity());
            lineItem.setAmount(itemsDao.getItemById(lineItem.getItemId()).getSellingPrice()*lineItem.getQuantity());

//          insert sale item table
            insertSalesItem(sale.getSalesId(), lineItem.getItemId(), lineItem.getQuantity(), lineItem.getAmount());
            totalAmount += lineItem.getAmount();
            updatedLineItems.add(lineItem);
        }

        System.out.println("total"+totalAmount);
        sale.setSalesItems(updatedLineItems);
        sale.setTotalAmount(totalAmount);
        System.out.println("update status "+updateTotal(sale.getSalesId(),totalAmount));
        return sale;
    }

    private int insertSales(int customerId) throws SQLException {
        String query = "insert into sales (customerId, date) values (?,?)";
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, customerId);
        stmt.setDate(2,Date.valueOf(LocalDate.now()));
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        throw new SQLException("Error inserting into Sale Table");
    }

    private int insertSalesItem(int salesId, int itemId, float quantity, float amount) throws SQLException {
        String query = "insert into salesItems (salesId,itemId,quantity,amount) values(?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, salesId);
        stmt.setInt(2, itemId);
        stmt.setFloat(3, quantity);
        stmt.setFloat(4, amount);
        return stmt.executeUpdate();
    }

    private int updateTotal(int salesId, float totalAmount) throws SQLException {
        String query="update sales set totalAmount=? where salesId=?";
        PreparedStatement stmt=conn.prepareStatement(query);
        stmt.setFloat(1,totalAmount);
        stmt.setInt(2,salesId);
        return stmt.executeUpdate();
    }

}

package supermarket.dto;

import supermarket.models.Sale;

public class SaleDto extends Sale {

    private String customerName;

    public SaleDto(){
        super();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public SaleDto(Sale sale, String customerName) {
        super(sale.getSalesId(),sale.getDate(),sale.getCustomerId(),sale.getTotalAmount());
        this.customerName = customerName;
    }
}

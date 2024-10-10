package supermarket.models;

import java.util.Date;

public class Payment {
    private int invoiceId;
    private float amount;
    private Date date;

    public Payment(){
    }

    public Payment(int invoiceId, float amount, Date date){
        setInvoiceId(invoiceId);
        setAmount(amount);
        setDate(date);
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}

package org.inr.tutorial.messenger.dao;

import org.inr.tutorial.messenger.database.Database;
import org.inr.tutorial.messenger.model.Invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvoicesDao {
    private Map<Integer, Invoice> invoices = Database.getInvoices();

    public Invoice addInvoice(Invoice invoice) {
        invoices.put(invoice.getId(), invoice);
        return invoices.get(invoice.getId());
    }

    public Invoice editInvoice(int invoiceId, Invoice invoice) {
        if (invoices.containsKey(invoiceId)) {
            invoices.put(invoiceId, invoice);
            return invoices.get(invoiceId);
        }
        return null;
    }

    public Invoice getInvoiceById(int invoiceId) {
        return invoices.get(invoiceId);
    }

    public List<Invoice> getAllInvoices() {
        return new ArrayList<>(invoices.values());
    }

    public Invoice deleteInvoice(int invoiceId){
        return invoices.remove(invoiceId);
    }

    public List<Invoice> getCustomerInvoice(int customerId){
        Map<Integer,Invoice> invoices=Database.getInvoices();
        List<Invoice> customerInvoices=new ArrayList<>();
        for(Map.Entry<Integer,Invoice> invoice:invoices.entrySet()){
            if(invoice.getValue().getCustomerId()==customerId){
                customerInvoices.add(invoice.getValue());
            }
        }
        return customerInvoices;
    }
}

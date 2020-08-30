package app.code.service.model;

public class Cost {
    private Double subTotal;
    private Double salesTax;
    private Double totalCost;

    public Cost() {
    }

    public Cost(Double subTotal, Double salesTax, Double totalCost) {
        this.subTotal = subTotal;
        this.salesTax = salesTax;
        this.totalCost = totalCost;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(Double salesTax) {
        this.salesTax = salesTax;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

}

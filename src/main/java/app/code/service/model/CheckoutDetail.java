package app.code.service.model;

import java.util.Objects;
import java.util.Set;

public class CheckoutDetail {

    private Long orderId;
    private Set<Product> products;
    private Cost totalCost;

    public CheckoutDetail() {
    }

    public CheckoutDetail(Long orderId, Set<Product> products, Cost totalCost) {
        this.orderId = orderId;
        this.products = products;
        this.totalCost = totalCost;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Cost getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Cost totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CheckoutDetail other = (CheckoutDetail) obj;
        return Objects.equals(orderId, other.orderId);
    }

}

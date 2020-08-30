package app.code.service.model;

import java.util.Objects;

import app.code.service.enums.CategoryType;

public class Product {

    private Long id;
    private String name;
    private String productCode;
    private Double price;
    private CategoryType categoryType;

    public Product() {
    }

    public Product(Long id, String name, String productCode, Double price, CategoryType categoryType) {
        this.id = id;
        this.name = name;
        this.productCode = productCode;
        this.price = price;
        this.categoryType = categoryType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    @Override
    public String toString() {
        return "Product id:" + getId() + ", Name:" + getName() + ", product code:" + getProductCode() + ", price: " + getPrice() + ", Category:"
                + getCategoryType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
        Product other = (Product) obj;
        return Objects.equals(id, other.id);
    }

}

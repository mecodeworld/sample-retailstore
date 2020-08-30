package app.code.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import app.code.service.api.IProductDataService;
import app.code.service.enums.CategoryType;
import app.code.service.model.Product;

public class ProductDataService implements IProductDataService {

    private static final double DEFAULT_MULTIPLY_VALUE = 5.0;
    private static ProductDataService instance;
    private static Long counter = 1L;
    private static Set<Product> defaultProducts = new HashSet<>();

    // DUMMY DATA
    static {
        Stream.of(CategoryType.values())
                .forEach(categoryType -> {
                    for (int i = 1; i <= 5; i++) {
                        Product product = new Product();
                        product.setId(counter++);
                        product.setName("product-" + categoryType + i);
                        product.setProductCode("ITEM-" + categoryType + i);
                        product.setPrice(i * DEFAULT_MULTIPLY_VALUE);
                        product.setCategoryType(categoryType);

                        defaultProducts.add(product);
                    }
                });
    }

    private ProductDataService() {
        // hidden constructor
    }

    public static ProductDataService getInstance() {
        if (instance == null) {
            instance = new ProductDataService();
        }
        return instance;
    }

    @Override
    public Set<Product> findAllProducts() {
        return defaultProducts;
    }

}

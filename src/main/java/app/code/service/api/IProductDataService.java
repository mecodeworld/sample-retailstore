package app.code.service.api;

import java.util.Set;

import app.code.service.model.Product;

/**
 * The Interface IProductService.
 *
 */
public interface IProductDataService {

    /**
     * Find all products.
     *
     * @return the Set of dummy data.
     */
    Set<Product> findAllProducts();

}

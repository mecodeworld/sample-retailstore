package app.code.service.api;

import java.util.Optional;

import app.code.service.model.Cost;

/**
 * The Interface ISalesService.
 */
public interface ISalesService {

    /**
     * Find total sale.
     *
     * @return the total sale based on successfully checkout products.
     */
    Optional<Cost> findTotalSale();

}

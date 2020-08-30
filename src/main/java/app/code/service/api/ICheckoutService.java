package app.code.service.api;

import java.util.Optional;
import java.util.Set;

import app.code.service.model.CheckoutDetail;

/**
 * The Interface ICheckoutService.
 */
public interface ICheckoutService {

    /**
     * Checkout products.
     *
     * @param products the scanned products
     * @return the checkout detail with orderId and the cost.
     */
    Optional<CheckoutDetail> checkoutProducts(Set<String> products);

    /**
     * Find all checkout history.
     *
     * @return the Set of successfully checkout products detail.
     */
    Set<CheckoutDetail> findAllCheckoutHistory();

}

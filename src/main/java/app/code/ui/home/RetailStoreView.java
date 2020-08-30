package app.code.ui.home;

import java.util.Set;

import app.code.service.model.CheckoutDetail;
import app.code.service.model.Cost;
import app.code.service.model.Product;

public interface RetailStoreView {

    public interface Presenter {

        /**
         * Checkout products.
         *
         * @param productCodes the product codes
         */
        void checkoutProducts(Set<String> productCodes);

        /**
         * Fetch all products.
         */
        void fetchAllProducts();

        /**
         * Fetch total sale.
         */
        void fetchTotalSale();

    }

    /**
     * Show checkout details.
     *
     * @param checkoutDetail the checkout detail
     */
    void showCheckoutDetails(CheckoutDetail checkoutDetail);

    /**
     * Show all products.
     *
     * @param allProducts the all products
     */
    void showAllProducts(Set<Product> allProducts);

    /**
     * Show error message.
     *
     * @param message the message
     */
    void showErrorMessage(String message);

    /**
     * Show total sale.
     *
     * @param totalCost the total cost
     */
    void showTotalSale(Cost totalCost);

    /**
     * Show message.
     *
     * @param message the message
     * @param proceed the proceed
     */
    void showMessage(String message, boolean proceed);
}

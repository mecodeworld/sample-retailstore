package app.code.service.configuration;

import app.code.service.api.ICheckoutService;
import app.code.service.api.IProductDataService;
import app.code.service.api.ISalesService;
import app.code.service.impl.CheckoutService;
import app.code.service.impl.ProductDataService;
import app.code.service.impl.SalesService;

/**
 * This class works like a dependecy manager for SERVICE and it provides the depenedencies of object in loosely coupled way.
 */
public class DependencyHelper {

    public static IProductDataService dataService() {
        return ProductDataService.getInstance();
    }

    public static ICheckoutService checkoutService() {
        return CheckoutService.getInstance();
    }

    public static ISalesService salesService() {
        return SalesService.getInstance();
    }

}

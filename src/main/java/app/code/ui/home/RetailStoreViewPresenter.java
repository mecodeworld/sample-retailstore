package app.code.ui.home;

import java.util.Optional;
import java.util.Set;

import app.code.service.api.ICheckoutService;
import app.code.service.api.IProductDataService;
import app.code.service.api.ISalesService;
import app.code.service.configuration.DependencyHelper;
import app.code.service.model.CheckoutDetail;
import app.code.service.model.Cost;
import app.code.ui.configuration.UiDependencyHelper;

public class RetailStoreViewPresenter implements RetailStoreView.Presenter {

    private RetailStoreView view = UiDependencyHelper.retailStoreView();
    private ICheckoutService checkoutService = DependencyHelper.checkoutService();
    private IProductDataService productDataService = DependencyHelper.dataService();
    private ISalesService salesService = DependencyHelper.salesService();

    @Override
    public void checkoutProducts(Set<String> productCodes) {
        try {
            Optional<CheckoutDetail> checkoutDetail = checkoutService.checkoutProducts(productCodes);
            if (checkoutDetail.isPresent()) {
                view.showCheckoutDetails(checkoutDetail.get());
            }
        }
        catch (Exception e) {
            view.showErrorMessage(e.getMessage());
        }
    }

    @Override
    public void fetchAllProducts() {
        view.showAllProducts(productDataService.findAllProducts());
    }

    @Override
    public void fetchTotalSale() {
        Optional<Cost> totalCost = salesService.findTotalSale();
        if (totalCost.isPresent()) {
            view.showTotalSale(totalCost.get());
        }
        else {
            view.showMessage("No Sale", true);
        }
    }
}

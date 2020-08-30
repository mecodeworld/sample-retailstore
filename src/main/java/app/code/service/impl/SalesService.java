package app.code.service.impl;

import java.util.Optional;
import java.util.Set;

import app.code.service.api.ICheckoutService;
import app.code.service.api.ISalesService;
import app.code.service.configuration.DependencyHelper;
import app.code.service.model.CheckoutDetail;
import app.code.service.model.Cost;

public class SalesService implements ISalesService {

    private static SalesService instance;

    private ICheckoutService checkoutService = DependencyHelper.checkoutService();

    private SalesService() {
    }

    public static SalesService getInstance() {
        if (instance == null) {
            instance = new SalesService();
        }
        return instance;
    }

    @Override
    public Optional<Cost> findTotalSale() {
        Set<CheckoutDetail> allCheckoutHistory = checkoutService.findAllCheckoutHistory();

        if (allCheckoutHistory == null || allCheckoutHistory.isEmpty()) {
            return Optional.empty();
        }

        Double subTotal = allCheckoutHistory.stream()
                .mapToDouble(f -> f.getTotalCost()
                        .getSubTotal())
                .sum();

        Double salesTax = allCheckoutHistory.stream()
                .mapToDouble(f -> f.getTotalCost()
                        .getSalesTax())
                .sum();

        Double total = allCheckoutHistory.stream()
                .mapToDouble(f -> f.getTotalCost()
                        .getTotalCost())
                .sum();

        Cost cost = new Cost();
        cost.setSubTotal(subTotal);
        cost.setSalesTax(salesTax);
        cost.setTotalCost(total);

        return Optional.ofNullable(cost);
    }

}

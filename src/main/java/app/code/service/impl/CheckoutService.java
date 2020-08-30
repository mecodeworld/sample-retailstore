package app.code.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import app.code.service.api.ICheckoutService;
import app.code.service.api.IProductDataService;
import app.code.service.configuration.DependencyHelper;
import app.code.service.enums.CategoryType;
import app.code.service.exception.DataNotFoundException;
import app.code.service.exception.NotValidInputException;
import app.code.service.model.CheckoutDetail;
import app.code.service.model.Cost;
import app.code.service.model.Product;

public class CheckoutService implements ICheckoutService {
    private Long counter = 1L;
    private Set<CheckoutDetail> checkoutHistory = new HashSet<>();

    // Services
    private static CheckoutService instance;
    private IProductDataService productDataService = DependencyHelper.dataService();

    private CheckoutService() {
    }

    public static CheckoutService getInstance() {
        if (instance == null) {
            instance = new CheckoutService();
        }
        return instance;
    }

    @Override
    public Optional<CheckoutDetail> checkoutProducts(Set<String> products) {
        if (products == null || products.isEmpty() || products.stream()
                .anyMatch(f -> f.equals(""))) {
            throw new NotValidInputException("product code list can not be Empty or null");
        }
        Set<Product> allProducts = productDataService.findAllProducts();
        Set<Product> matchedProducts = new HashSet<>();
        for (String productCode : products) {
            allProducts.stream()
                    .filter(f -> f.getProductCode()
                            .equalsIgnoreCase(productCode))
                    .findFirst()
                    .ifPresent(matchedProducts::add);
        }

        if (matchedProducts.size() != products.size()) {
            throw new DataNotFoundException("Productcodes are not matching");
        }

        Map<CategoryType, List<Product>> productByCategory = matchedProducts.stream()
                .collect(Collectors.groupingBy(Product::getCategoryType));

        Double subTotal = 0.0;
        Double salesTax = 0.0;
        for (Entry<CategoryType, List<Product>> value : productByCategory.entrySet()) {
            subTotal = subTotal + value.getValue()
                    .stream()
                    .mapToDouble(Product::getPrice)
                    .sum();

            Double taxByCategory = value.getValue()
                    .stream()
                    .mapToDouble(Product::getPrice)
                    .sum();

            salesTax = salesTax + ((taxByCategory / 100) * value.getKey()
                    .getExtraCharge());
        }

        CheckoutDetail checkoutDetail = new CheckoutDetail(counter++, matchedProducts, new Cost(subTotal, salesTax, (subTotal + salesTax)));
        checkoutHistory.add(checkoutDetail);
        return Optional.ofNullable(checkoutDetail);
    }

    @Override
    public Set<CheckoutDetail> findAllCheckoutHistory() {
        return checkoutHistory;
    }
}

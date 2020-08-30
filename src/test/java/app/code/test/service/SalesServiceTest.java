package app.code.test.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import app.code.service.enums.CategoryType;
import app.code.service.impl.CheckoutService;
import app.code.service.impl.SalesService;
import app.code.service.model.CheckoutDetail;
import app.code.service.model.Cost;
import app.code.service.model.Product;

public class SalesServiceTest {

    @InjectMocks
    private SalesService salesService;

    @Mock
    private CheckoutService checkoutService;

    @Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetInstance() {
        final SalesService instance = SalesService.getInstance();
        Assert.assertNotNull(instance);
    }

    @Test
    public void testEmptyNullfindTotalSale() {
        // Mocking
        Mockito.when(checkoutService.findAllCheckoutHistory())
                .thenReturn(null);
        // Actual call
        Optional<Cost> cost = salesService.findTotalSale();
        // Assertion
        Assert.assertFalse(cost.isPresent());
        // Verify mock
        Mockito.verify(checkoutService, Mockito.times(1))
                .findAllCheckoutHistory();
    }

    @Test
    public void testfindTotalSale() {
        Set<Product> products = new HashSet<>();
        products.add(new Product(1L, "ITEM-A1", "ITEM-A1", 10.0, CategoryType.A));

        Cost totalCost = new Cost();
        totalCost.setSubTotal(10.0);
        totalCost.setSalesTax(1.0);
        totalCost.setTotalCost(11.0);

        Set<CheckoutDetail> allCheckoutHistory = new HashSet<>();
        CheckoutDetail detail = new CheckoutDetail();
        detail.setOrderId(1L);
        detail.setProducts(products);
        detail.setTotalCost(totalCost);
        allCheckoutHistory.add(detail);

        // Mocking
        Mockito.when(checkoutService.findAllCheckoutHistory())
                .thenReturn(allCheckoutHistory);
        // Actual call
        Optional<Cost> cost = salesService.findTotalSale();
        // Assertion
        Assert.assertTrue(cost.isPresent());
        Assert.assertEquals(totalCost.getTotalCost(), cost.get()
                .getTotalCost());
        Assert.assertEquals(totalCost.getSubTotal(), cost.get()
                .getSubTotal());
        Assert.assertEquals(totalCost.getSalesTax(), cost.get()
                .getSalesTax());
        // Verify mock
        Mockito.verify(checkoutService, Mockito.times(1))
                .findAllCheckoutHistory();
    }
}

package app.code.test.service;

import java.util.Arrays;
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
import app.code.service.exception.DataNotFoundException;
import app.code.service.exception.NotValidInputException;
import app.code.service.impl.CheckoutService;
import app.code.service.impl.ProductDataService;
import app.code.service.model.CheckoutDetail;
import app.code.service.model.Product;

public class CheckoutServiceTest {

    @InjectMocks
    private CheckoutService checkoutService;

    @Mock
    private ProductDataService productDataService;

    @Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetInstance() {
        final CheckoutService instance = CheckoutService.getInstance();
        Assert.assertNotNull(instance);
    }

    @Test(expected = NotValidInputException.class)
    public void testInvalidForCheckoutProduct() {
        checkoutService.checkoutProducts(null);
    }

    @Test(expected = DataNotFoundException.class)
    public void testDataNotFoundForCheckoutProduct() {
        Set<Product> allProducts = new HashSet<>();
        allProducts.add(new Product(1L, "ITEM-A1", "ITEM-A1", 10.0, CategoryType.A));

        Mockito.when(productDataService.findAllProducts())
                .thenReturn(allProducts);
        checkoutService.checkoutProducts(new HashSet<>(Arrays.asList("ITEM-A1, ITEM-B1")));
    }

    @Test
    public void testSuccessForCheckoutProduct() {

        // Mocking
        Set<Product> allProducts = new HashSet<>();
        allProducts.add(new Product(1L, "ITEM-A1", "ITEM-A1", 10.0, CategoryType.A));
        Mockito.when(productDataService.findAllProducts())
                .thenReturn(allProducts);

        // Actual call
        Optional<CheckoutDetail> checkoutDetailOp = checkoutService.checkoutProducts(new HashSet<>(Arrays.asList("ITEM-A1")));

        // Assertion
        Assert.assertTrue(checkoutDetailOp.isPresent());
        CheckoutDetail checkoutDetail = checkoutDetailOp.get();

        Assert.assertNotNull(checkoutDetail.getOrderId());
        Assert.assertFalse(checkoutDetail.getProducts()
                .isEmpty());
        Assert.assertEquals(allProducts.iterator()
                .next()
                .getProductCode(),
                checkoutDetail.getProducts()
                        .iterator()
                        .next()
                        .getProductCode());
        Assert.assertEquals(allProducts.iterator()
                .next()
                .getPrice(),
                checkoutDetail.getTotalCost()
                        .getSubTotal());

        // Verify mock
        Mockito.verify(productDataService, Mockito.times(1))
                .findAllProducts();
    }

    @Test
    public void testEmptyListFindAllCheckoutHistory() {
        Set<CheckoutDetail> allCheckoutHistory = checkoutService.findAllCheckoutHistory();
        Assert.assertTrue(allCheckoutHistory.isEmpty());
    }

    @Test
    public void testFindAllCheckoutHistory() {
        // Mocking
        Set<Product> allProducts = new HashSet<>();
        Product product = new Product(1L, "ITEM-A1", "ITEM-A1", 10.0, CategoryType.A);
        allProducts.add(product);

        Mockito.when(productDataService.findAllProducts())
                .thenReturn(allProducts);
        // Create test data
        Optional<CheckoutDetail> checkoutDetail = checkoutService.checkoutProducts(new HashSet<>(Arrays.asList("ITEM-A1")));
        Assert.assertTrue(checkoutDetail.isPresent());

        // Actual call
        Set<CheckoutDetail> allCheckoutHistory = checkoutService.findAllCheckoutHistory();

        // Assertion
        Assert.assertFalse(allCheckoutHistory.isEmpty());
        Assert.assertTrue(allCheckoutHistory.contains(checkoutDetail.get()));

        // Verify mock
        Mockito.verify(productDataService, Mockito.times(1))
                .findAllProducts();
    }

}

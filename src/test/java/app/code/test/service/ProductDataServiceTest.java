package app.code.test.service;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import app.code.service.impl.ProductDataService;
import app.code.service.model.Product;

public class ProductDataServiceTest {

    @InjectMocks
    private ProductDataService productDataService;

    @Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetInstance() {
        final ProductDataService instance = ProductDataService.getInstance();
        Assert.assertNotNull(instance);
    }

    @Test
    public void testFindAllProducts() {
        Set<Product> products = productDataService.findAllProducts();
        Assert.assertFalse(products.isEmpty());
    }

}

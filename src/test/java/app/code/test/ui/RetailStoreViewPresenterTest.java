package app.code.test.ui;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import app.code.service.api.ICheckoutService;
import app.code.service.api.IProductDataService;
import app.code.service.api.ISalesService;
import app.code.service.enums.CategoryType;
import app.code.service.exception.NotValidInputException;
import app.code.service.model.CheckoutDetail;
import app.code.service.model.Cost;
import app.code.service.model.Product;
import app.code.ui.home.RetailStoreViewImpl;
import app.code.ui.home.RetailStoreViewPresenter;

public class RetailStoreViewPresenterTest {

    @InjectMocks
    private RetailStoreViewPresenter presenter;

    @Mock
    private RetailStoreViewImpl view;

    @Mock
    private ICheckoutService checkoutService;

    @Mock
    private IProductDataService productDataService;

    @Mock
    private ISalesService salesService;

    @Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNullReturnCheckoutProducts() {
        // Mocking
        Mockito.when(checkoutService.checkoutProducts(Mockito.anySetOf(String.class)))
                .thenReturn(null);

        // Actual Call
        Set<String> productCodes = new HashSet<>();
        productCodes.add("test-item");
        presenter.checkoutProducts(productCodes);

        // Verifying Mock
        Mockito.verify(checkoutService, Mockito.times(1))
                .checkoutProducts(Mockito.anySetOf(String.class));
        Mockito.verify(view, Mockito.times(0))
                .showCheckoutDetails(Mockito.any(CheckoutDetail.class));

    }

    @Test
    public void testExceptionHandleCheckoutProducts() {
        // Mocking
        String message = "invalid input";
        Mockito.when(checkoutService.checkoutProducts(Mockito.anySetOf(String.class)))
                .thenThrow(new NotValidInputException(message));

        // Actual Call
        Set<String> productCodes = new HashSet<>();
        productCodes.add("test-item");
        presenter.checkoutProducts(productCodes);

        // Verifying Mock
        Mockito.verify(checkoutService, Mockito.times(1))
                .checkoutProducts(Mockito.anySetOf(String.class));
        Mockito.verify(view, Mockito.times(1))
                .showErrorMessage(message);
    }

    @Test
    public void testSuccessCheckoutProducts() {

        Set<Product> products = new HashSet<>();
        products.add(new Product(1L, "ITEM-A1", "test-item", 10.0, CategoryType.A));
        CheckoutDetail checkoutDetail = new CheckoutDetail(1L, products, new Cost(10.0, 2.0, 12.0));
        // Mocking
        Mockito.when(checkoutService.checkoutProducts(Mockito.anySetOf(String.class)))
                .thenReturn(Optional.ofNullable(checkoutDetail));

        // Actual Call
        Set<String> productCodes = new HashSet<>();
        productCodes.add("test-item");
        presenter.checkoutProducts(productCodes);

        // Verifying mock
        Mockito.verify(view, Mockito.times(1))
                .showCheckoutDetails(Mockito.any(CheckoutDetail.class));

    }

    @Test
    public void testFetchAllProducts() {
        Set<Product> products = new HashSet<>();
        products.add(new Product(1L, "ITEM-A1", "test-item", 10.0, CategoryType.A));
        // Mocking
        Mockito.when(productDataService.findAllProducts())
                .thenReturn(products);

        // Actual Call
        presenter.fetchAllProducts();

        // Verifying mock
        Mockito.verify(view, Mockito.times(1))
                .showAllProducts(Mockito.anySetOf(Product.class));

    }

    @Test
    public void testFetchTotalSale() {
        Cost cost = new Cost(10.0, 2.0, 12.0);
        // Mocking
        Mockito.when(salesService.findTotalSale())
                .thenReturn(Optional.ofNullable(cost));
        // Actual Call
        presenter.fetchTotalSale();
        // Verifying mock
        Mockito.verify(view, Mockito.times(1))
                .showTotalSale(Mockito.any(Cost.class));
    }

    @Test
    public void testEmptyReturnFetchTotalSale() {
        Mockito.when(salesService.findTotalSale())
                .thenReturn(Optional.empty());
        // Actual Call
        presenter.fetchTotalSale();
        // Verifying mock
        Mockito.verify(view, Mockito.times(1))
                .showMessage(Mockito.anyString(), Mockito.anyBoolean());
    }

}

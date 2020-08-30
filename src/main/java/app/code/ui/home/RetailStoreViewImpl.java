package app.code.ui.home;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import app.code.service.model.CheckoutDetail;
import app.code.service.model.Cost;
import app.code.service.model.Product;
import app.code.ui.configuration.UiDependencyHelper;

public class RetailStoreViewImpl implements RetailStoreView {

    private static Scanner scanner = UiDependencyHelper.scanner();
    private static RetailStoreView.Presenter presenter = UiDependencyHelper.retailStoreViewPresenter();

    public static void showMenu() throws InputMismatchException {
        System.out.println("MENU:");
        System.out.println("1.Show all products");
        System.out.println("2.Show total sale");
        System.out.println("3.checkout products");
        System.out.println("4.Exit");
        System.out.println("Enter your option: ");
        int menuId = scanner.nextInt();
        switch (menuId) {
            case 1:
                showAllProductView();
                break;
            case 2:
                showTotalSaleView();
                break;
            case 3:
                showCheckoutView();
                break;
            case 4:
                System.out.println("Thank you...!!!");
                break;
            default:
                System.out.println("Invalid input");
                continueQuestion();
                break;
        }
    }

    private static void showTotalSaleView() {
        presenter.fetchTotalSale();

    }

    private static void showAllProductView() {
        presenter.fetchAllProducts();
    }

    private static void showCheckoutView() {
        Set<String> productCodes = new HashSet<>();
        System.out.println("Enter product code by comma separated:");
        scanner.nextLine();
        String productCode = scanner.nextLine();

        String[] input = productCode.trim()
                .split(",");
        for (String str : input) {
            productCodes.add(str.trim());
        }

        System.out.println("DO YOU WANT TO CHECKOUT? Y/N ");
        final String checkout = scanner.next();
        if (checkout != null && checkout.equalsIgnoreCase("y")) {
            presenter.checkoutProducts(productCodes);
        }
    }

    private static void continueQuestion() {
        System.out.println("Do you want to continue y/n?");
        final String input = scanner.next();
        if (input != null && input.equalsIgnoreCase("y")) {
            showMenu();
        }
    }

    @Override
    public void showCheckoutDetails(CheckoutDetail checkoutDetail) {
        System.out.println("Checkout Details:");
        System.out.println("Your Order Id is: " + checkoutDetail.getOrderId());
        System.out.println("Checkout products are: ");

        checkoutDetail.getProducts()
                .forEach(item -> {
                    System.out.println(item.toString() + " Applicable SalesTax: " + +(item.getPrice() / 100) * item.getCategoryType()
                            .getExtraCharge());
                });

        System.out.println("Sub Total: Rs. " + checkoutDetail.getTotalCost()
                .getSubTotal());
        System.out.println("Sales Tax: Rs. " + checkoutDetail.getTotalCost()
                .getSalesTax());
        System.out.println("Total: Rs. " + checkoutDetail.getTotalCost()
                .getTotalCost());
        continueQuestion();
    }

    @Override
    public void showAllProducts(Set<Product> findAllProducts) {
        if (findAllProducts.isEmpty()) {
            System.out.println("There are no products.");
        }
        else {
            System.out.println("All product details:");
            findAllProducts.forEach(System.out::println);
        }
        continueQuestion();
    }

    @Override
    public void showErrorMessage(String message) {
        System.out.println("Following Error occured: " + message);
        continueQuestion();
    }

    @Override
    public void showTotalSale(Cost totalCost) {
        System.out.println("Total Sales:");
        System.out.println("Subtotal Amount: " + totalCost.getSubTotal());
        System.out.println("Total Sales tax: " + totalCost.getSalesTax());
        System.out.println("Total Amount: " + totalCost.getTotalCost());
        continueQuestion();
    }

    @Override
    public void showMessage(String message, boolean proceed) {
        System.out.println(message);
        if (proceed) {
            continueQuestion();
        }
    }

}

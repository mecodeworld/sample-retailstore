package app.code.start;

import java.util.InputMismatchException;

import app.code.ui.home.RetailStoreViewImpl;

/**
 * Entry Point class
 *
 * @author h.kanure
 *
 */
public class RetailStoreApplication {

    public static void main(String[] args) {
        try {
            RetailStoreViewImpl.showMenu();
        }
        catch (InputMismatchException e) {
            System.out.println("Input type is incompatible");
        }

    }
}

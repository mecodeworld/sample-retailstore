package app.code.ui.configuration;

import java.util.Scanner;

import app.code.ui.home.RetailStoreViewImpl;
import app.code.ui.home.RetailStoreViewPresenter;

/**
 * The Class UiDependencyHelper.
 *
 * This class works like a dependecy manager for UI and it provides the depenedencies of object in loosely coupled way.
 */
public class UiDependencyHelper {

    public static Scanner scanner() {
        return new Scanner(System.in);
    }

    public static RetailStoreViewPresenter retailStoreViewPresenter() {
        return new RetailStoreViewPresenter();
    }

    public static RetailStoreViewImpl retailStoreView() {
        return new RetailStoreViewImpl();
    }

}

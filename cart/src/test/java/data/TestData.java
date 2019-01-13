package data;

import com.shopping.cart.enums.DiscountType;
import com.shopping.cart.model.*;
import com.shopping.cart.request.CreateShoppingCartRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Burak Naroglu
 */

public class TestData {

    public static List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Telefon"));
        categories.add(new Category("Aksesuar"));
        return categories;
    }

    public static List<Campaign> getCampaigns() {
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(new Campaign(getCategories().get(0), 20.0, 3, DiscountType.RATE));
        campaigns.add(new Campaign(getCategories().get(1), 50.0, 5, DiscountType.RATE));
        campaigns.add(new Campaign(getCategories().get(1), 5.0, 5, DiscountType.AMOUNT));
        return campaigns;
    }

    public static List<Coupon> getCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(100, 10, DiscountType.RATE));
        return coupons;
    }

    public static ShoppingCart createShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(createProductByTelephoneCategory(), 4);
        return shoppingCart;
    }

    public static Product createProductByTelephoneCategory() {
        return new Product("Apple", 45.0, getCategories().get(0));
    }

    public static Product createProductByAccessoryCategory() {
        return new Product("Apple", 45.0, getCategories().get(1));
    }

    public static List<AppliedDiscount> appliedDiscounts() {
        List<AppliedDiscount> appliedDiscounts = new ArrayList<>();
        appliedDiscounts.add(new AppliedDiscount(13.0));
        appliedDiscounts.add(new AppliedDiscount(11.0));
        return appliedDiscounts;
    }

    public static List<CreateShoppingCartRequest> createShoppingCartRequests() {
        List<CreateShoppingCartRequest> createShoppingCartRequests = new ArrayList<>();
        CreateShoppingCartRequest createShoppingCartRequest = new CreateShoppingCartRequest();
        createShoppingCartRequest.setCategory(getCategories().get(0));
        createShoppingCartRequest.setProductAmount(7);
        createShoppingCartRequest.setProductName("Apple");
        createShoppingCartRequest.setProductPrice(15.0);
        createShoppingCartRequests.add(createShoppingCartRequest);
        createShoppingCartRequest.setCategory(getCategories().get(1));
        createShoppingCartRequest.setProductAmount(7);
        createShoppingCartRequest.setProductName("Telefon");
        createShoppingCartRequest.setProductPrice(5.0);
        createShoppingCartRequests.add(createShoppingCartRequest);
        return createShoppingCartRequests;
    }

}

    
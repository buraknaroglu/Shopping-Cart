package com.shopping.cart.service.impl;

import com.shopping.cart.enums.DiscountType;
import com.shopping.cart.model.Coupon;
import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.service.BaseService;
import com.shopping.cart.service.ICouponService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CouponServiceImpl extends BaseService implements ICouponService {

    public List<Coupon> coupons = new ArrayList<>();

    @PostConstruct
    void init() {
        coupons.add(new Coupon(100, 10, DiscountType.RATE));
    }

    @Override
    public ShoppingCart applyCouponToCart(ShoppingCart shoppingCart) {
        List<Coupon> availableCoupons = new ArrayList<>();
        double cartTotalPrice = shoppingCart.getTotalAmountAfterDiscounts() != 0.0 ? shoppingCart.getTotalAmountAfterDiscounts() : shoppingCart.getTotalAmountBeforeDiscounts();

        coupons.stream().forEach(coupon -> {
            if (cartTotalPrice > coupon.getMinPurchase()) {
                availableCoupons.add(coupon);
            }
        });

        if (availableCoupons.isEmpty()) {
            log.info("Available coupon is empty.");
            return shoppingCart;
        }

        Collections.sort(availableCoupons, (o1, o2) -> (new Double(o2.getPercent()).compareTo(new Double(o1.getPercent()))));
        Coupon bestCoupon = availableCoupons.get(0);

        if (shoppingCart.getCampaignDiscount() > 0) {
            shoppingCart.setTotalAmountAfterDiscounts(shoppingCart.getTotalAmountAfterDiscounts() - (shoppingCart.getTotalAmountAfterDiscounts() * bestCoupon.getPercent() / 100));
            shoppingCart.setCouponDiscount(shoppingCart.getTotalAmountAfterDiscounts() * bestCoupon.getPercent() / 100);
        } else {
            shoppingCart.setTotalAmountAfterDiscounts(shoppingCart.getTotalAmountBeforeDiscounts() - (shoppingCart.getTotalAmountBeforeDiscounts() * bestCoupon.getPercent() / 100));
            shoppingCart.setCouponDiscount(shoppingCart.getTotalAmountBeforeDiscounts() * bestCoupon.getPercent() / 100);
        }
        log.info("Coupon applied to shopping cart. Coupon discount= {}", shoppingCart.getCouponDiscount());

        return shoppingCart;
    }
}

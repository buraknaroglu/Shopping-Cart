package com.shopping.cart.service.impl;

import com.shopping.cart.enums.DiscountType;
import com.shopping.cart.model.AppliedDiscount;
import com.shopping.cart.model.Campaign;
import com.shopping.cart.model.CartLineItem;
import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.service.BaseService;
import com.shopping.cart.service.ICategoryService;
import com.shopping.cart.service.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DiscountServiceImpl extends BaseService implements IDiscountService {

    @Autowired
    private ICategoryService iCategoryService;

    public List<Campaign> campaigns = new ArrayList<>();

    @PostConstruct
    void init() {
        campaigns.add(new Campaign(iCategoryService.findByCategoryName("Telefon"), 20.0, 3, DiscountType.RATE));
        campaigns.add(new Campaign(iCategoryService.findByCategoryName("Aksesuar"), 50.0, 5, DiscountType.RATE));
        campaigns.add(new Campaign(iCategoryService.findByCategoryName("Aksesuar"), 5.0, 5, DiscountType.AMOUNT));
    }

    @Override
    public ShoppingCart applyDiscountToCartLineItem(ShoppingCart shoppingCart) {
        List<AppliedDiscount> appliedAvailableRateDiscounts = new ArrayList<>();
        List<AppliedDiscount> appliedAvailableAmountDiscounts = new ArrayList<>();

        shoppingCart.getCartLineItem().forEach(cartLineItem -> {
            appliedAvailableRateDiscounts.clear();
            appliedAvailableAmountDiscounts.clear();
            AppliedDiscount bestDiscountAmount = null;
            Boolean applyRateDiscount = Boolean.FALSE;

            campaigns.forEach(campaign -> {
                if (cartLineItem.getProduct().getCategory().getTitle().equals(campaign.getCategory().getTitle()) && cartLineItem.getProductAmount() > campaign.getAmount()) { // kategoriyide kontrol et

                    if (DiscountType.RATE.equals(campaign.getDiscountType())) {
                        appliedAvailableRateDiscounts.add(applyDiscount(cartLineItem, campaign, DiscountType.RATE));
                    }

                    if (DiscountType.AMOUNT.equals(campaign.getDiscountType())) {
                        appliedAvailableAmountDiscounts.add(applyDiscount(cartLineItem, campaign, DiscountType.AMOUNT));
                    }
                }
            });

            bestDiscountAmount = getBestDiscountAmount(appliedAvailableRateDiscounts);

            if (bestDiscountAmount != null) {
                cartLineItem.setTotalAmountAfterDiscounts(cartLineItem.getTotalAmountBeforeDiscounts() - bestDiscountAmount.getDiscountPrice());
                cartLineItem.setCampaignDiscount(bestDiscountAmount.getDiscountPrice());
                bestDiscountAmount = null;
                applyRateDiscount = Boolean.TRUE;
                log.info("Rate type discount applied. campaignDiscount= {}", cartLineItem.getCampaignDiscount());
            }

            bestDiscountAmount = getBestDiscountAmount(appliedAvailableAmountDiscounts);

            if (bestDiscountAmount != null) {
                if (applyRateDiscount) {
                    cartLineItem.setTotalAmountAfterDiscounts(cartLineItem.getTotalAmountAfterDiscounts() - bestDiscountAmount.getDiscountPrice());
                    cartLineItem.setCampaignDiscount(cartLineItem.getCampaignDiscount() + bestDiscountAmount.getDiscountPrice());
                } else {
                    cartLineItem.setTotalAmountAfterDiscounts(cartLineItem.getTotalAmountBeforeDiscounts() - bestDiscountAmount.getDiscountPrice());
                    cartLineItem.setCampaignDiscount(bestDiscountAmount.getDiscountPrice());
                }
                log.info("Amount type discount applied. campaignDiscount= {}", cartLineItem.getCampaignDiscount());
            }
        });
        return shoppingCart;
    }

    public AppliedDiscount applyDiscount(CartLineItem cartLineItem, Campaign campaign, DiscountType discountType) {
        AppliedDiscount appliedDiscount = new AppliedDiscount();
        if (DiscountType.RATE.equals(discountType)) {
            appliedDiscount.setDiscountPrice(cartLineItem.getTotalAmountBeforeDiscounts() * campaign.getDiscountValue() / 100);
        } else {
            appliedDiscount.setDiscountPrice(campaign.getDiscountValue());
        }
        return appliedDiscount;
    }

    public AppliedDiscount getBestDiscountAmount(List<AppliedDiscount> appliedDiscounts) {
        if (appliedDiscounts.isEmpty()) {
            log.info("Applied discount is empty.");
            return null;
        }
        Collections.sort(appliedDiscounts, (o1, o2) -> (o2.getDiscountPrice().compareTo(o1.getDiscountPrice())));
        return appliedDiscounts.get(0);
    }
}

package com.Ecommerce.model;

import java.util.Arrays;
import java.util.Optional;

public enum Category{
    ELECTRONICS(1, "Electronic devices and gadgets"),
    COMPUTERS(2, "Laptops, desktops, and computer accessories"),
    SMARTPHONES(3, "Mobile phones and related accessories"),
    FASHION(4, "Clothing, footwear, and fashion accessories"),
    HOME(5, "Home furniture, decor, and household items"),
    KITCHEN(6, "Kitchen appliances and cookware"),
    GROCERY(7, "Food, beverages, and everyday consumables"),
    TOYS(8, "Toys, games, and educational kits for children"),
    BOOKS(9, "Books, eBooks, and publications"),
    BEAUTY(10, "Cosmetics, skincare, and personal care"),
    SPORTS(11, "Sporting goods, fitness, and outdoor equipment"),
    HEALTH(12, "Healthcare products, supplements, and wellness items"),
    AUTOMOTIVE(13, "Car accessories, tools, and automotive parts"),
    OFFICE(14, "Office supplies, equipment, and stationery"),
    PETS(15, "Pet food, accessories, and care products"),
    MUSIC(16, "Musical instruments and audio equipment"),
    VIDEO_GAMES(17, "Consoles, games, and gaming accessories"),
    JEWELRY(18, "Rings, necklaces, watches, and fine jewelry"),
    BABY(19, "Baby clothing, toys, and care products"),
    GARDEN(20, "Gardening tools, plants, and outdoor décor"),
    ART(21, "Art supplies, paintings, and collectibles"),
    TRAVEL(22, "Travel accessories, luggage, and outdoor gear"),
    DIY(23, "Do-it-yourself tools, materials, and craft supplies"),
    PARTY(24, "Party supplies, decorations, and event accessories"),
    PHOTOGRAPHY(25, "Cameras, lenses, and photography accessories"),
    SOFTWARE(26, "Software, apps, and digital solutions"),
    GIFT_CARDS(27, "Gift cards for various stores and services"),
    COLLECTIBLES(28, "Limited edition items, memorabilia, and collectibles"),
    HEALTH_FITNESS(29, "Home workout equipment, wellness accessories"),
    LUXURY(30, "Luxury items, designer brands, and high-end products"),
    HOME_IMPROVEMENT(31, "Tools, hardware, and home renovation supplies"),
    GROCERIES(32, "Fresh produce, snacks, and frozen foods"),
    VOUCHERS(33, "Discount vouchers and special offers"),
    CAMPING(34, "Camping gear, tents, and outdoor equipment"),
    VAPING(35, "Vaping products, e-cigarettes, and accessories"),
    WINE(36, "Wines, spirits, and alcoholic beverages");



    private final int code;
    private final String description;

    Category(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Optional<Category> fromCode(int code) {
        return Arrays.stream(values())
                .filter(c -> c.getCode() == code)
                .findFirst();
    }

    @Override
    public String toString() {
        return description;
    }
}
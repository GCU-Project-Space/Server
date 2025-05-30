package com.example.storeservice.entity;

public enum Category {
    FAST_FOOD("패스트푸드"),
    KOREAN("한식"),
    SNACK("분식"),
    CHICKEN("치킨"),
    SOUP("찜/탕"),
    JAPANESE("일식"),
    MEAT("고기"),
    PORK_CUTLET("돈까스/회"),
    PIZZA("피자"),
    CAFE("카페/디저트"),
    SALAD("샐러드"),
    CHINESE("중식");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
} 
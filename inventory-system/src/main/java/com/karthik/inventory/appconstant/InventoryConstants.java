package com.karthik.inventory.appconstant;

public class InventoryConstants {
    public enum OrderStatus {
        PAID,
        NOT_PAID,
        COMPLETED,
        PENDING;
    }

    public static class ScheduledCronTab {
        public static final String EVERY_DAY_5_00 = "0 00 5 ? * *";
        public static final String EVERY_ONE_HOUR = "0 0 0/1 1/1 * ?";
    }
}

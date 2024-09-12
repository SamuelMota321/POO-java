    package controllers;

    import java.util.ArrayList;
    import java.util.List;

    public class PurchaseController {
        private List<Double> purchases;

        public PurchaseController() {
            purchases = new ArrayList<>();
        }

    public void addPurchase(double amount) {
        purchases.add(amount);
    }

    public double getTotal() {
        double total = 0;
        for (double amount : purchases) {
            total += amount;
        }
        return total;
    }

    public int countPurchasesInRange(double min, double max) {
        int count = 0;
        for (double amount : purchases) {
            if (amount >= min && amount <= max) {
                count++;
            }
        }
        return count;
    }

    public void clearPurchases() {
        purchases.clear();
    }
}

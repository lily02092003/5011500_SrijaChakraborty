/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class ObserverPatternTest {
    public static void main(String[] args) {
        // Create a stock market instance
        StockMarket stockMarket = new StockMarket("ABC Corp", 100.0);

        // Create observer instances
        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        // Register observers
        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        // Change stock price and notify observers
        stockMarket.setStockPrice(105.0);
        stockMarket.setStockPrice(110.0);
    }
}
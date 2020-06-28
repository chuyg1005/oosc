package user;

public class Vip1Customer extends Customer{
    public Vip1Customer(String name, String password) {
        super(name, password);
    }

    /**
     * 使用一元红包
     */
    @Override
    protected double purchase(double price, int amount) {
        return Math.max(price * amount - 1, 0);
    }
}

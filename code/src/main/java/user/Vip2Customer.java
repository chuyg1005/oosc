package user;

public class Vip2Customer extends Customer{
    public Vip2Customer(String name, String password) {
        super(name, password);
    }

    /**
     * 结算价格打九五折
     */
    @Override
    protected double purchase(double price, int amount) {
        return price * amount * 0.95;
    }
}

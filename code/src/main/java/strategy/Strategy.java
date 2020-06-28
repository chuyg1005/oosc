package strategy;

import controller.Common;
import javafx.stage.Stage;
import state.State;

public abstract class Strategy {
    protected Common common;
    protected State state;

    protected Strategy(Common common, State state) {
        this.common = common;
        this.state = state;
    }

    public abstract void login(String name, String password, Stage stage);

    public abstract void register(String name, String password, Stage stage);

    public static Strategy newStrategy(String type, Common common, State state) {
        switch (type) {
            case "顾客":
                return new CustomerStrategy(common, state);
            case "商家":
                return new ProviderStrategy(common, state);
            case "vip1-顾客":
                return new Vip1CustomerStrategy(common, state);
            case "vip2-顾客":
                return new Vip2CustomerStrategy(common, state);
            case "管理员":
                return new ManagerStrategy(common, state);
            default:
                return null;
        }
    }
}

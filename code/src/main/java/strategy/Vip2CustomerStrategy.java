package strategy;

import controller.Common;
import state.State;

public class Vip2CustomerStrategy extends CustomerStrategy {
    protected Vip2CustomerStrategy(Common common, State state) {
        super(common, state);
    }

    @Override
    protected String transName(String name) {
        return "vip2-" + name;
    }

}

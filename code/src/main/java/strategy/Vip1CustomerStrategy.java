package strategy;

import controller.Common;
import state.State;

public class Vip1CustomerStrategy extends CustomerStrategy {
    protected Vip1CustomerStrategy(Common common, State state) {
        super(common, state);
    }

    @Override
    protected String transName(String name) {
        return "vip1-" + name;
    }

}

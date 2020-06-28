package state;

public class LoginState extends State {
    private LoginState() {
        super("/fxml/login.fxml");
    }

    public static State getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final State INSTANCE = new LoginState();
    }
}

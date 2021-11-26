import java.util.regex.Pattern;

public class test {

    public static Boolean check(String password) {
        String username = "asdf12";
        if (password.contains(username)) {
            return false;
        } else {
            return Pattern.matches("^[0-9a-zA-Z]{4,}", password);
        }
    }

    public static void main(String[] args) {
        System.out.println(check("24fdf2f"));
    }
}

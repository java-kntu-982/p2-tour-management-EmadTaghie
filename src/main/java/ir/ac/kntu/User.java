package ir.ac.kntu;

import org.jetbrains.annotations.NotNull;

public class User {
    private String userName;
    private String password;
    private String email;
    private String phoneNum;
    private UserState userState;

    public User(){
        userName = "gust";
        password = "";
        email = "";
        phoneNum = "";
        userState = UserState.Costumer;
    }

    public User(String userName, String password, String email, String phoneNum, UserState userState){
        setUserName(userName);
        setPassword(password);
        setEmail(email);
        setPhoneNum(phoneNum);
        setUserState(userState);
    }

    public void setUserName(@NotNull String userName) {
        if(userName.matches("[a-zA-Z0-9 ]+")) {
            this.userName = userName;
            return;
        }
        System.out.println("Incorrect username");
        this.userName = "";
    }

    public void setPassword(@NotNull String password) {
        if(password.matches("[0-9a-zA-z]+")) {
            this.password = password;
            return;
        }
        System.out.println("Incorrect passWord");
        this.password = "";
    }

    public void setEmail(@NotNull String email) {
        if(email.matches("[0-9a-zA-Z.]+@[a-z]+.com")) {
            this.email = email;
            return;
        }
        System.out.println("Incorrect email");
        this.email = "";
    }

    public void setPhoneNum(@NotNull String phoneNum) {
        if(phoneNum.matches("(09|\\+989)[1-9][0-9]{8}")) {
            this.phoneNum = phoneNum;
            return;
        }
        System.out.println("Incorrect phone number");
        this.phoneNum = "";
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public UserState getUserState() {
        return userState;
    }

    @Override
    public String toString() {
        return "userName: " + userName + "\n" +
                "password: " + password +"\n" +
                "email: " + email + "\n" +
                "phoneNum: " + phoneNum +"\n" +
                "userState: " + userState + "\n";
    }
}

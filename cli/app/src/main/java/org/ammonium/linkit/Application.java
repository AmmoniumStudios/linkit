package org.ammonium.linkit;

import org.ammonium.linkit.util.HttpUtil;
import org.ammonium.linkit.util.json.Body;

public class Application {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        //System.out.println(new Gson().toJson(new Body("SELECT * FROM `links` WHERE `short` = ?", new String[]{"google"})));
        String url = HttpUtil.shortenUrl(new Body("SELECT * FROM `links` WHERE `short` = ?", new String[]{"google"}));
        System.out.println(url);
    }
}

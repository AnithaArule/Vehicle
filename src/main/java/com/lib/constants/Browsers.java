package com.lib.constants;

/**
 * Created by anitha on 02/09/2017.
 */
public enum Browsers {
    FIREFOX,
    CHROME;


    public static Browsers obtainBrowser(String browser) throws IllegalArgumentException {
        for (Browsers b : values()) {
            if (b.toString().equalsIgnoreCase(browser)) {
                return b;
            }
        }
        return FIREFOX;
    }

}

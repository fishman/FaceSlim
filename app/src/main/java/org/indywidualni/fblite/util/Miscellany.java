package org.indywidualni.fblite.util;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

public class Miscellany {

    // url decoder, recreate all the special characters
    public static String urlDecode(String url) {
        return url.replace("%3C", "<").replace("%3E", ">").replace("%23", "#").replace("%25", "%")
                .replace("%7B", "{").replace("%7D", "}").replace("%7C", "|").replace("%5C", "\\")
                .replace("%5E", "^").replace("%7E", "~").replace("%5B", "[").replace("%5D", "]")
                .replace("%60", "`").replace("%3B", ";").replace("%2F", "/").replace("%3F", "?")
                .replace("%3A", ":").replace("%40", "@").replace("%3D", "=").replace("%26", "&")
                .replace("%24", "$").replace("%2B", "+").replace("%22", "\"").replace("%2C", ",")
                .replace("%20", " ");
    }

    // get some information about the device (needed for e-mail signature)
    public static String getDeviceInfo(Activity activity) {
        StringBuilder sb = new StringBuilder();

        try {
            PackageInfo pInfo = activity.getPackageManager().getPackageInfo(
                    activity.getPackageName(), PackageManager.GET_META_DATA);
            sb.append("\nApp Package Name: ").append(activity.getPackageName());
            sb.append("\nApp Version Name: ").append(pInfo.versionName);
            sb.append("\nApp Version Code: ").append(pInfo.versionCode);
        } catch (PackageManager.NameNotFoundException ex) {
            Log.e("Misc: getDeviceInfo", ex.getMessage());
        }

        sb.append("\nOS Version: ").append(System.getProperty("os.version")).append(" (")
                .append(android.os.Build.VERSION.RELEASE).append(")");
        sb.append("\nOS API Level: ").append(android.os.Build.VERSION.SDK_INT);
        sb.append("\nDevice: ").append(android.os.Build.DEVICE);
        sb.append("\nModel: ").append(android.os.Build.MODEL);
        sb.append("\nManufacturer: ").append(android.os.Build.MANUFACTURER);

        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        sb.append("\nScreen: ").append(metrics.heightPixels).append(" &#215; ").append(metrics.widthPixels);
        sb.append("\nLocale: ").append(Locale.getDefault().toString());

        return sb.toString();
    }

}
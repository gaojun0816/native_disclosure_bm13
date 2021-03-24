package lu.uni.jungao.bm13;

import android.app.Activity;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class NativeDelegator {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
//        checkLoadedLibs();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native String sStringFromJNI();
    public native String iStringFromJNI();
    public native void nativeSendSMS(String msg);
    public native String nativeGetIMEI(TelephonyManager tm);
    public native void nativeSinkIMEI(Activity activity);

    public void sinkIMEI(String imei)  {
        Log.d("SINK", imei);
    }
    static private void checkLoadedLibs() {
        try {
            Set<String> libs = new HashSet();
            String mapsFile = "/proc/" + android.os.Process.myPid() + "/maps";
            BufferedReader reader = new BufferedReader(new FileReader(mapsFile));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.endsWith(".so")) {
                    int n = line.lastIndexOf(" ");
                    libs.add(line.substring(n + 1));
                }
            }
//            Log.d("Ldd", libs.size() + " libraries:");
            for (String lib : libs) {
                Log.d("Ldd", lib);
            }
        } catch (FileNotFoundException e) {
            // Do some error handling...
        } catch (IOException e) {
            // Do some error handling...
        }
    }
}

package lu.uni.jungao.bm13;

import android.widget.TextView;

public class MiddleCls {
    public static TextView smsTv;

    public String getNativeStaticString() {
        return NativeDelegator.sStringFromJNI();
    }

    public String getNativeInstanceString() {
        NativeDelegator nd = new NativeDelegator();
        return nd.iStringFromJNI();
    }

    public static void sendPseudoSMS(String msg) {
        smsTv.setText(msg);
    }
}

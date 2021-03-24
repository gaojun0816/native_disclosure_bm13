package lu.uni.jungao.bm13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    TextView tv, imeiTv;
    TelephonyManager tm;
    String imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tv = findViewById(R.id.sample_text);
        imeiTv = findViewById(R.id.show_imei);
        MiddleCls.smsTv = findViewById(R.id.message_text);
    }

    public void showStaticNativeString(View v) {
        tv.setText(NativeDelegator.sStringFromJNI());
    }

    public void showInstanceNativeString(View v) {
        NativeDelegator nd = new NativeDelegator();
        tv.setText(nd.iStringFromJNI());
    }

    public void showStaticNativeWrappedString(View v) {
        MiddleCls mc = new MiddleCls();
        tv.setText(mc.getNativeStaticString());
    }

    public void showInstanceNativeWrappedString(View v) {
        MiddleCls mc = new MiddleCls();
        tv.setText(mc.getNativeInstanceString());
    }

    public void getIMEI(View v) {
        NativeDelegator nd = new NativeDelegator();
        imei = nd.nativeGetIMEI(tm);
//        imei = tm.getImei();
        imeiTv.setText(imei);
    }

    public void sendSms(View v) {
        NativeDelegator nd = new NativeDelegator();
        nd.nativeSendSMS("Pseudo message send via native code!");
    }

    public void sinkIMEI(View v) {
        NativeDelegator nd = new NativeDelegator();
        nd.nativeSinkIMEI(this);
        Toast.makeText(this, "IMEI sent to catlog!", Toast.LENGTH_LONG).show();
    }

}

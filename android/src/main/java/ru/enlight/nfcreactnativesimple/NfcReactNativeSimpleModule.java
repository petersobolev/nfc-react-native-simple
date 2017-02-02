package ru.enlight.nfcreactnativesimple;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableNativeArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import static android.R.attr.data;
import static android.R.attr.defaultValue;
import static android.R.attr.x;
import static com.facebook.common.util.Hex.hexStringToByteArray;

class NfcReactNativeSimpleModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private ReactApplicationContext reactContext;
    private ReadableArray sectores;

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public NfcReactNativeSimpleModule(ReactApplicationContext reactContext) 
    {
        super(reactContext);
        this.reactContext = reactContext;
        this.reactContext.addActivityEventListener(this);
    }

    @Override
    public void onNewIntent(Intent intent) 
    {
	      String action = intent.getAction();
//	    Log.i("!intent! ", action);

	      Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        byte[] id = tag.getId();
        String serialNumber = bytesToHex(id);
        WritableMap idData = Arguments.createMap();
        idData.putString("id", serialNumber);

        sendEvent(this.reactContext, "NFCCardID", idData);

    }//onNewIntent()

    private void sendEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params)
    {
      reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(eventName, params);
    }//sendEvent()


    public static String bytesToHex(byte[] bytes) 
    {
      char[] hexChars = new char[bytes.length * 2];
      for ( int j = 0; j < bytes.length; j++ ) {
          int v = bytes[j] & 0xFF;
          hexChars[j * 2] = hexArray[v >>> 4];
          hexChars[j * 2 + 1] = hexArray[v & 0x0F];
      }
      return new String(hexChars);
    }//bytesToHex()


    @Override
    public void onActivityResult(
      final Activity activity,
      final int requestCode,
      final int resultCode,
      final Intent intent) {
    }

    /**
     * @return the name of this module. This will be the name used to {@code require()} this module
     * from javascript.
     */
    @Override
    public String getName() {
        return "NfcReactNativeSimple";
    }
}

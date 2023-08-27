package org.im97mori.ble.characteristic.u2a22;

import android.os.Build;
import android.os.Parcel;

import org.im97mori.ble.test.TestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertArrayEquals;

@SuppressWarnings({"unused"})
@RunWith(RobolectricTestRunner.class)
@Config(instrumentedPackages = {
        // required to access final members on androidx.loader.content.ModernAsyncTask
        "androidx.loader.content"}
        , sdk = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BootKeyboardInputReportAndroidTest extends TestBase {

    //@formatter:off
    private static final byte[] data_00001;
    static {
        byte[] data = new byte[1];
        data[ 0] = 0x01;
        data_00001 = data;
    }

    private static final byte[] data_00002;
    static {
        byte[] data = new byte[2];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data_00002 = data;
    }
    //@formatter:on

    @Test
    public void test_constructor_00001() {
        byte[] data = getData();

        BootKeyboardInputReportAndroid result1 = new BootKeyboardInputReportAndroid(data);
        assertArrayEquals(data, result1.getBootKeyboardInputReportValue());
    }

    @Test
    public void test_constructor_00002() {
        byte[] data = getData();

        BootKeyboardInputReportAndroid result1 = new BootKeyboardInputReportAndroid(data);
        assertArrayEquals(data, result1.getBootKeyboardInputReportValue());
    }

    @Test
    public void test_parcelable_1_00001() {
        byte[] data = getData();

        BootKeyboardInputReportAndroid result1 = new BootKeyboardInputReportAndroid(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        BootKeyboardInputReportAndroid result2 = BootKeyboardInputReportAndroid.CREATOR.createFromParcel(parcel);
        assertArrayEquals(result1.getBootKeyboardInputReportValue(), result2.getBootKeyboardInputReportValue());
    }

    @Test
    public void test_parcelable_1_00002() {
        byte[] data = getData();

        BootKeyboardInputReportAndroid result1 = new BootKeyboardInputReportAndroid(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        BootKeyboardInputReportAndroid result2 = BootKeyboardInputReportAndroid.CREATOR.createFromParcel(parcel);
        assertArrayEquals(result1.getBootKeyboardInputReportValue(), result2.getBootKeyboardInputReportValue());
    }

    @Test
    public void test_parcelable_2_00001() {
        byte[] data = getData();

        BootKeyboardInputReportAndroid result1 = new BootKeyboardInputReportAndroid(data);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_2_00002() {
        byte[] data = getData();

        BootKeyboardInputReportAndroid result1 = new BootKeyboardInputReportAndroid(data);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_3_00001() {
        byte[] data = getData();

        BootKeyboardInputReportAndroid result1 = new BootKeyboardInputReportAndroid(data);
        BootKeyboardInputReportAndroid result2 = BootKeyboardInputReportAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable_3_00002() {
        byte[] data = getData();

        BootKeyboardInputReportAndroid result1 = new BootKeyboardInputReportAndroid(data);
        BootKeyboardInputReportAndroid result2 = BootKeyboardInputReportAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

}

package org.im97mori.ble.characteristic.u2ab1;

import android.os.Build;
import android.os.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@SuppressWarnings({"unused"})
@RunWith(RobolectricTestRunner.class)
@Config(instrumentedPackages = {
        // required to access final members on androidx.loader.content.ModernAsyncTask
        "androidx.loader.content"}
        , sdk = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class LocalEastCoordinateAndroidTest {

    //@formatter:off
    private static final byte[] data_00001;
    static {
        byte[] data = new byte[2];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data_00001 = data;
    }
    //@formatter:on

    private byte[] getData() {
        int index = -1;
        byte[] data = null;

        StackTraceElement[] stackTraceElementArray = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTraceElementArray.length; i++) {
            StackTraceElement stackTraceElement = stackTraceElementArray[i];
            if ("getData".equals(stackTraceElement.getMethodName())) {
                index = i + 1;
                break;
            }
        }
        if (index >= 0 && index < stackTraceElementArray.length) {
            StackTraceElement stackTraceElement = stackTraceElementArray[index];
            String[] stringArray = stackTraceElement.getMethodName().split("_");
            try {
                data = (byte[]) this.getClass().getDeclaredField("data_" + stringArray[stringArray.length - 1]).get(null);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    @Test
    public void test_constructor_00001() {
        byte[] data = getData();

        LocalEastCoordinateAndroid result1 = new LocalEastCoordinateAndroid(data);
        assertEquals(0x0201, result1.getLocalEastCoordinate());
    }

    @Test
    public void test_constructor_00002() {
        int localEastCoordinate = 1;

        LocalEastCoordinateAndroid result1 = new LocalEastCoordinateAndroid(localEastCoordinate);
        assertEquals(localEastCoordinate, result1.getLocalEastCoordinate());
    }

    @Test
    public void test_parcelable_1_00001() {
        byte[] data = getData();

        LocalEastCoordinateAndroid result1 = new LocalEastCoordinateAndroid(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        LocalEastCoordinateAndroid result2 = LocalEastCoordinateAndroid.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getLocalEastCoordinate(), result2.getLocalEastCoordinate());
    }

    @Test
    public void test_parcelable_2_00001() {
        byte[] data = getData();

        LocalEastCoordinateAndroid result1 = new LocalEastCoordinateAndroid(data);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_3_00001() {
        byte[] data = getData();

        LocalEastCoordinateAndroid result1 = new LocalEastCoordinateAndroid(data);
        LocalEastCoordinateAndroid result2 = LocalEastCoordinateAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

}

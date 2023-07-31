package org.im97mori.ble.characteristic.u2ab0;

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
public class LocalNorthCoordinateAndroidTest {

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

        LocalNorthCoordinateAndroid result1 = new LocalNorthCoordinateAndroid(data);
        assertEquals(0x0201, result1.getLocalNorthCoordinate());
    }

    @Test
    public void test_constructor_00002() {
        int localNorthCoordinate = 1;

        LocalNorthCoordinateAndroid result1 = new LocalNorthCoordinateAndroid(localNorthCoordinate);
        assertEquals(localNorthCoordinate, result1.getLocalNorthCoordinate());
    }

    @Test
    public void test_parcelable_1_00001() {
        byte[] data = getData();

        LocalNorthCoordinateAndroid result1 = new LocalNorthCoordinateAndroid(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        LocalNorthCoordinateAndroid result2 = LocalNorthCoordinateAndroid.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getLocalNorthCoordinate(), result2.getLocalNorthCoordinate());
    }

    @Test
    public void test_parcelable_2_00001() {
        byte[] data = getData();

        LocalNorthCoordinateAndroid result1 = new LocalNorthCoordinateAndroid(data);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_3_00001() {
        byte[] data = getData();

        LocalNorthCoordinateAndroid result1 = new LocalNorthCoordinateAndroid(data);
        LocalNorthCoordinateAndroid result2 = LocalNorthCoordinateAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

}

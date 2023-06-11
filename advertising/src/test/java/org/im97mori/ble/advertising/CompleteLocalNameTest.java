package org.im97mori.ble.advertising;

import static org.im97mori.ble.constants.DataType.COMPLETE_LOCAL_NAME_DATA_TYPE;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import android.os.Build;
import android.os.Parcel;

import org.im97mori.ble.advertising.CompleteLocalNameAndroid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(instrumentedPackages = {
        // required to access final members on androidx.loader.content.ModernAsyncTask
        "androidx.loader.content"}
        , sdk = Build.VERSION_CODES.JELLY_BEAN_MR2)
@SuppressWarnings("unused")
public class CompleteLocalNameTest {

    //@formatter:off
    private static final byte[] data_00001;
    static {
        String name = "complete local name";
        byte[] utf8data = name.getBytes();
        byte[] data = new byte[utf8data.length + 2];
        data[0] = (byte) (utf8data.length + 1);
        data[1] = COMPLETE_LOCAL_NAME_DATA_TYPE;
        System.arraycopy(utf8data, 0, data, 2, utf8data.length);
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
            String[] splitted = stackTraceElement.getMethodName().split("_");
            try {
                data = (byte[]) this.getClass().getDeclaredField("data_" + splitted[splitted.length - 1]).get(null);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    @Test
    public void test_constructor_1_00001() {
        byte[] data = getData();

        CompleteLocalNameAndroid result1 = new CompleteLocalNameAndroid(data, 0, data[0]);
        assertEquals(data[0], result1.getLength());
        assertEquals(COMPLETE_LOCAL_NAME_DATA_TYPE, result1.getDataType());
        assertEquals(new String(data, 2, data.length - 2), result1.getCompleteLocalName());
    }

    @Test
    public void test_constructor_2_00001() {
        byte[] data = getData();

        CompleteLocalNameAndroid result1 = new CompleteLocalNameAndroid(data, 0);
        assertEquals(data[0], result1.getLength());
        assertEquals(COMPLETE_LOCAL_NAME_DATA_TYPE, result1.getDataType());
        assertEquals(new String(data, 2, data.length - 2), result1.getCompleteLocalName());
    }

    @Test
    public void test_constructor_3_00001() {
        byte[] data = getData();

        CompleteLocalNameAndroid result1 = new CompleteLocalNameAndroid(new String(data, 2, data.length - 2));
        assertEquals(data[0], result1.getLength());
        assertEquals(COMPLETE_LOCAL_NAME_DATA_TYPE, result1.getDataType());
        assertEquals(new String(data, 2, data.length - 2), result1.getCompleteLocalName());
    }

    @Test
    public void test_parcelable_1_00001() {
        byte[] data = getData();

        CompleteLocalNameAndroid result1 = new CompleteLocalNameAndroid(data, 0, data[0]);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        CompleteLocalNameAndroid result2 = CompleteLocalNameAndroid.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getLength(), result2.getLength());
        assertEquals(result1.getDataType(), result2.getDataType());
        assertEquals(new String(data, 2, data.length - 2), result1.getCompleteLocalName());
    }
    
    @Test
    public void test_parcelable_2_00001() {
        byte[] data = getData();

        CompleteLocalNameAndroid result1 = new CompleteLocalNameAndroid(data, 0, data[0]);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_3_00001() {
        byte[] data = getData();

        CompleteLocalNameAndroid result1 = new CompleteLocalNameAndroid(data, 0, data[0]);
        CompleteLocalNameAndroid result2 = CompleteLocalNameAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

}
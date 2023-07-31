package org.im97mori.ble.characteristic.u2a03;

import android.os.Build;
import android.os.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(instrumentedPackages = {
        // required to access final members on androidx.loader.content.ModernAsyncTask
        "androidx.loader.content"}
        , sdk = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class ReconnectionAddressAndroidTest {

    @Test
    public void test_constructor001() {
        //@formatter:off
        byte[] data = new byte[6];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;
        data[ 4] = 0x05;
        data[ 5] = 0x06;
        //@formatter:on

        ReconnectionAddressAndroid result1 = new ReconnectionAddressAndroid(data);
        assertEquals(0x060504030201L, result1.getAddress());
    }

    @Test
    public void test_constructor002() {
        //@formatter:off
        byte[] data = new byte[6];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;
        data[ 4] = 0x05;
        data[ 5] = (byte) 0xff;
        //@formatter:on

        ReconnectionAddressAndroid result1 = new ReconnectionAddressAndroid(data);
        assertEquals(0xff0504030201L, result1.getAddress());
    }

    @Test
    public void test_constructor003() {
        long address = 1;

        ReconnectionAddressAndroid result1 = new ReconnectionAddressAndroid(address);
        assertEquals(address, result1.getAddress());
    }

    @Test
    public void test_parcelable001() {
        //@formatter:off
        byte[] data = new byte[6];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;
        data[ 4] = 0x05;
        data[ 5] = 0x06;
        //@formatter:on

        ReconnectionAddressAndroid result1 = new ReconnectionAddressAndroid(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        ReconnectionAddressAndroid result2 = ReconnectionAddressAndroid.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getAddress(), result2.getAddress());
    }

    @Test
    public void test_parcelable002() {
        //@formatter:off
        byte[] data = new byte[6];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;
        data[ 4] = 0x05;
        data[ 5] = 0x06;
        //@formatter:on

        ReconnectionAddressAndroid result1 = new ReconnectionAddressAndroid(data);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable003() {
        //@formatter:off
        byte[] data = new byte[6];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;
        data[ 4] = 0x05;
        data[ 5] = 0x06;
        //@formatter:on

        ReconnectionAddressAndroid result1 = new ReconnectionAddressAndroid(data);
        ReconnectionAddressAndroid result2 = ReconnectionAddressAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}

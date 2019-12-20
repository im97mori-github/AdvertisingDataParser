package org.im97mori.ble.characteristic.uds;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FiveZoneHeartRateLimitsTest {

    @Test
    public void test_constructor001() {
        //@formatter:off
        byte[] data = new byte[4];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        FiveZoneHeartRateLimits result1 = new FiveZoneHeartRateLimits(bluetoothGattCharacteristic);
        assertEquals(0x01, result1.getFiveZoneHeartRateLimitsVeryLightLightLimit());
        assertEquals(0x02, result1.getFiveZoneHeartRateLimitsLightModerateLimit());
        assertEquals(0x03, result1.getFiveZoneHeartRateLimitsModerateHardLimit());
        assertEquals(0x04, result1.getFiveZoneHeartRateLimitsHardMaximumLimit());
    }

    @Test
    public void test_constructor002() {
        //@formatter:off
        byte[] data = new byte[4];
        data[ 0] = (byte) 0xff;
        data[ 1] = (byte) 0xff;
        data[ 2] = (byte) 0xff;
        data[ 3] = (byte) 0xff;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        FiveZoneHeartRateLimits result1 = new FiveZoneHeartRateLimits(bluetoothGattCharacteristic);
        assertEquals(0xff, result1.getFiveZoneHeartRateLimitsVeryLightLightLimit());
        assertEquals(0xff, result1.getFiveZoneHeartRateLimitsLightModerateLimit());
        assertEquals(0xff, result1.getFiveZoneHeartRateLimitsModerateHardLimit());
        assertEquals(0xff, result1.getFiveZoneHeartRateLimitsHardMaximumLimit());
    }

    @Test
    public void test_parcelable001() {
        //@formatter:off
        byte[] data = new byte[4];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        FiveZoneHeartRateLimits result1 = new FiveZoneHeartRateLimits(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        FiveZoneHeartRateLimits result2 = FiveZoneHeartRateLimits.CREATOR.createFromParcel(parcel);

        assertEquals(result2.getFiveZoneHeartRateLimitsVeryLightLightLimit(), result1.getFiveZoneHeartRateLimitsVeryLightLightLimit());
        assertEquals(result2.getFiveZoneHeartRateLimitsLightModerateLimit(), result1.getFiveZoneHeartRateLimitsLightModerateLimit());
        assertEquals(result2.getFiveZoneHeartRateLimitsModerateHardLimit(), result1.getFiveZoneHeartRateLimitsModerateHardLimit());
        assertEquals(result2.getFiveZoneHeartRateLimitsHardMaximumLimit(), result1.getFiveZoneHeartRateLimitsHardMaximumLimit());
    }

    @Test
    public void test_parcelable002() {
        //@formatter:off
        byte[] data = new byte[4];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        FiveZoneHeartRateLimits result1 = new FiveZoneHeartRateLimits(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable003() {
        //@formatter:off
        byte[] data = new byte[4];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        FiveZoneHeartRateLimits result1 = new FiveZoneHeartRateLimits(bluetoothGattCharacteristic);
        FiveZoneHeartRateLimits result2 = FiveZoneHeartRateLimits.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

}

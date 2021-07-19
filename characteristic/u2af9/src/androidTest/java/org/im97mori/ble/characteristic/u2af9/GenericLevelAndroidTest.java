package org.im97mori.ble.characteristic.u2af9;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.BLEUtils;
import org.im97mori.ble.characteristic.core.GenericLevelUtils;
import org.junit.Test;

import static org.im97mori.ble.BLEUtils.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("ConstantConditions")
public class GenericLevelAndroidTest {

    @Test
    public void test_constructor_00001() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = (byte) GenericLevelUtils.GENERIC_LEVEL_VALUE_MINIMUM;
        data[ 1] = (byte) (GenericLevelUtils.GENERIC_LEVEL_VALUE_MINIMUM >> 8);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        GenericLevelAndroid result = new GenericLevelAndroid(bluetoothGattCharacteristic);
        assertEquals(BLEUtils.createUInt16(data, 0), result.getGenericLevel());
    }

    @Test
    public void test_constructor_00002() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = (byte) GenericLevelUtils.GENERIC_LEVEL_VALUE_MAXIMUM;
        data[ 1] = (byte) (GenericLevelUtils.GENERIC_LEVEL_VALUE_MAXIMUM >> 8);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        GenericLevelAndroid result = new GenericLevelAndroid(bluetoothGattCharacteristic);
        assertEquals(BLEUtils.createUInt16(data, 0), result.getGenericLevel());
    }

    @Test
    public void test_constructor_00003() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        GenericLevelAndroid result = new GenericLevelAndroid(bluetoothGattCharacteristic);
        assertEquals(BLEUtils.createUInt16(data, 0), result.getGenericLevel());
    }

    @Test
    public void test_constructor_00101() {
        int genericLevel = GenericLevelUtils.GENERIC_LEVEL_VALUE_MINIMUM;

        GenericLevelAndroid result = new GenericLevelAndroid(genericLevel);
        assertEquals(genericLevel, result.getGenericLevel());
    }

    @Test
    public void test_constructor_00102() {
        int genericLevel = GenericLevelUtils.GENERIC_LEVEL_VALUE_MAXIMUM;

        GenericLevelAndroid result = new GenericLevelAndroid(genericLevel);
        assertEquals(genericLevel, result.getGenericLevel());
    }

    @Test
    public void test_constructor_00103() {
        int genericLevel = 1;

        GenericLevelAndroid result = new GenericLevelAndroid(genericLevel);
        assertEquals(genericLevel, result.getGenericLevel());
    }

    @Test
    public void test_parcelable_00001() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = (byte) GenericLevelUtils.GENERIC_LEVEL_VALUE_MINIMUM;
        data[ 1] = (byte) (GenericLevelUtils.GENERIC_LEVEL_VALUE_MINIMUM >> 8);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        GenericLevelAndroid result1 = new GenericLevelAndroid(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        GenericLevelAndroid result2 = GenericLevelAndroid.CREATOR.createFromParcel(parcel);

        assertEquals(result2.getGenericLevel(), result1.getGenericLevel());
    }

    @Test
    public void test_parcelable_00002() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = (byte) GenericLevelUtils.GENERIC_LEVEL_VALUE_MAXIMUM;
        data[ 1] = (byte) (GenericLevelUtils.GENERIC_LEVEL_VALUE_MAXIMUM >> 8);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        GenericLevelAndroid result1 = new GenericLevelAndroid(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        GenericLevelAndroid result2 = GenericLevelAndroid.CREATOR.createFromParcel(parcel);

        assertEquals(result2.getGenericLevel(), result1.getGenericLevel());
    }

    @Test
    public void test_parcelable_00003() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        GenericLevelAndroid result1 = new GenericLevelAndroid(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        GenericLevelAndroid result2 = GenericLevelAndroid.CREATOR.createFromParcel(parcel);

        assertEquals(result2.getGenericLevel(), result1.getGenericLevel());
    }

    @Test
    public void test_parcelable_00101() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = (byte) GenericLevelUtils.GENERIC_LEVEL_VALUE_MINIMUM;
        data[ 1] = (byte) (GenericLevelUtils.GENERIC_LEVEL_VALUE_MINIMUM >> 8);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        GenericLevelAndroid result1 = new GenericLevelAndroid(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_00102() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = (byte) GenericLevelUtils.GENERIC_LEVEL_VALUE_MAXIMUM;
        data[ 1] = (byte) (GenericLevelUtils.GENERIC_LEVEL_VALUE_MAXIMUM >> 8);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        GenericLevelAndroid result1 = new GenericLevelAndroid(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_00103() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        GenericLevelAndroid result1 = new GenericLevelAndroid(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_00201() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = (byte) GenericLevelUtils.GENERIC_LEVEL_VALUE_MINIMUM;
        data[ 1] = (byte) (GenericLevelUtils.GENERIC_LEVEL_VALUE_MINIMUM >> 8);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        GenericLevelAndroid result1 = new GenericLevelAndroid(bluetoothGattCharacteristic);
        GenericLevelAndroid result2 = GenericLevelAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable_00202() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = (byte) GenericLevelUtils.GENERIC_LEVEL_VALUE_MAXIMUM;
        data[ 1] = (byte) (GenericLevelUtils.GENERIC_LEVEL_VALUE_MAXIMUM >> 8);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        GenericLevelAndroid result1 = new GenericLevelAndroid(bluetoothGattCharacteristic);
        GenericLevelAndroid result2 = GenericLevelAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable_00203() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        GenericLevelAndroid result1 = new GenericLevelAndroid(bluetoothGattCharacteristic);
        GenericLevelAndroid result2 = GenericLevelAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

}

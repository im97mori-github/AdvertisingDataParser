package org.im97mori.ble.characteristic.u2bc3;

import static org.im97mori.ble.BLEUtils.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.BLEUtils;
import org.im97mori.ble.test.TestBase;
import org.junit.Test;

@SuppressWarnings({"unused", "ConstantConditions"})
public class MuteAndroidTest extends TestBase {

    //@formatter:off
    private static final byte[] data_00001;
    static {
        byte[] data = new byte[1];
        data[ 0] = Mute.NOT_MUTED;
        data_00001 = data;
    }

    private static final byte[] data_00002;
    static {
        byte[] data = new byte[1];
        data[ 0] = Mute.MUTED;
        data_00002 = data;
    }

    private static final byte[] data_00003;
    static {
        byte[] data = new byte[1];
        data[ 0] = Mute.DISABLED;
        data_00003 = data;
    }
    //@formatter:on

    @Test
    public void test_constructor_00001() {
        byte[] data = getData();


        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MuteAndroid result1 = new MuteAndroid(bluetoothGattCharacteristic);
        assertEquals(BLEUtils.createUInt8(data, 0), result1.getMute());
        assertTrue(result1.isNotMuted());
        assertFalse(result1.isMuted());
        assertFalse(result1.isDisabled());
    }

    @Test
    public void test_constructor_00002() {
        byte[] data = getData();

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MuteAndroid result1 = new MuteAndroid(bluetoothGattCharacteristic);
        assertEquals(BLEUtils.createUInt8(data, 0), result1.getMute());
        assertFalse(result1.isNotMuted());
        assertTrue(result1.isMuted());
        assertFalse(result1.isDisabled());
    }

    @Test
    public void test_constructor_00003() {
        byte[] data = getData();

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MuteAndroid result1 = new MuteAndroid(bluetoothGattCharacteristic);
        assertEquals(BLEUtils.createUInt8(data, 0), result1.getMute());
        assertFalse(result1.isNotMuted());
        assertFalse(result1.isMuted());
        assertTrue(result1.isDisabled());
    }

    @Test
    public void test_constructor_00101() {
        int mute = Mute.NOT_MUTED;

        MuteAndroid result1 = new MuteAndroid(mute);
        assertEquals(mute, result1.getMute());
        assertTrue(result1.isNotMuted());
        assertFalse(result1.isMuted());
        assertFalse(result1.isDisabled());
    }

    @Test
    public void test_constructor_00102() {
        int mute = Mute.MUTED;

        MuteAndroid result1 = new MuteAndroid(mute);
        assertEquals(mute, result1.getMute());
        assertFalse(result1.isNotMuted());
        assertTrue(result1.isMuted());
        assertFalse(result1.isDisabled());
    }

    @Test
    public void test_constructor_00103() {
        int mute = Mute.DISABLED;

        MuteAndroid result1 = new MuteAndroid(mute);
        assertEquals(mute, result1.getMute());
        assertFalse(result1.isNotMuted());
        assertFalse(result1.isMuted());
        assertTrue(result1.isDisabled());
    }

    @Test
    public void test_parcelable_1_00001() {
        byte[] data = getData();

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MuteAndroid result1 = new MuteAndroid(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        MuteAndroid result2 = MuteAndroid.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getMute(), result2.getMute(), 0);
    }

    @Test
    public void test_parcelable_1_00002() {
        byte[] data = getData();

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MuteAndroid result1 = new MuteAndroid(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        MuteAndroid result2 = MuteAndroid.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getMute(), result2.getMute(), 0);
    }

    @Test
    public void test_parcelable_1_00003() {
        byte[] data = getData();

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MuteAndroid result1 = new MuteAndroid(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        MuteAndroid result2 = MuteAndroid.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getMute(), result2.getMute(), 0);
    }

    @Test
    public void test_parcelable_2_00001() {
        byte[] data = getData();

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MuteAndroid result1 = new MuteAndroid(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_2_00002() {
        byte[] data = getData();

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MuteAndroid result1 = new MuteAndroid(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_2_00003() {
        byte[] data = getData();

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MuteAndroid result1 = new MuteAndroid(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_3_00001() {
        byte[] data = getData();

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MuteAndroid result1 = new MuteAndroid(bluetoothGattCharacteristic);
        MuteAndroid result2 = MuteAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable_3_00002() {
        byte[] data = getData();

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MuteAndroid result1 = new MuteAndroid(bluetoothGattCharacteristic);
        MuteAndroid result2 = MuteAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable_3_00003() {
        byte[] data = getData();

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MuteAndroid result1 = new MuteAndroid(bluetoothGattCharacteristic);
        MuteAndroid result2 = MuteAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

}

package org.im97mori.ble.characteristic.u2b86;

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

@SuppressWarnings({ "unused" })
public class LockCharacteristicAndroidTest extends TestBase {

	//@formatter:off
    private static final byte[] data_00001;
    static {
        byte[] data = new byte[1];
        data[ 0] = LockCharacteristic.UNLOCKED;
        data_00001 = data;
    }

    private static final byte[] data_00002;
    static {
        byte[] data = new byte[1];
        data[ 0] = LockCharacteristic.LOCKED;
        data_00002 = data;
    }
    //@formatter:on

	@Test
	public void test_constructor_00001() {
		byte[] data = getData();

		BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
		bluetoothGattCharacteristic.setValue(data);

		LockCharacteristicAndroid result1 = new LockCharacteristicAndroid(bluetoothGattCharacteristic);
		assertEquals(BLEUtils.createUInt8(data, 0), result1.getSetMemberLock());
		assertTrue(result1.isSetMemberLockUnlocked());
		assertFalse(result1.isSetMemberLockLocked());
	}

	@Test
	public void test_constructor_00002() {
		byte[] data = getData();

		BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
		bluetoothGattCharacteristic.setValue(data);

		LockCharacteristicAndroid result1 = new LockCharacteristicAndroid(bluetoothGattCharacteristic);
		assertEquals(BLEUtils.createUInt8(data, 0), result1.getSetMemberLock());
		assertFalse(result1.isSetMemberLockUnlocked());
		assertTrue(result1.isSetMemberLockLocked());
	}

	@Test
	public void test_constructor_00101() {
		int setMemberLock = LockCharacteristic.UNLOCKED;

		LockCharacteristicAndroid result1 = new LockCharacteristicAndroid(setMemberLock);
		assertEquals(setMemberLock, result1.getSetMemberLock());
		assertTrue(result1.isSetMemberLockUnlocked());
		assertFalse(result1.isSetMemberLockLocked());
	}

	@Test
	public void test_constructor_00102() {
		int setMemberLock = LockCharacteristic.LOCKED;

		LockCharacteristicAndroid result1 = new LockCharacteristicAndroid(setMemberLock);
		assertEquals(setMemberLock, result1.getSetMemberLock());
		assertFalse(result1.isSetMemberLockUnlocked());
		assertTrue(result1.isSetMemberLockLocked());
	}

	@Test
	public void test_parcelable_1_00001() {
		byte[] data = getData();

		BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
		bluetoothGattCharacteristic.setValue(data);

		LockCharacteristicAndroid result1 = new LockCharacteristicAndroid(bluetoothGattCharacteristic);
		Parcel parcel = Parcel.obtain();
		result1.writeToParcel(parcel, 0);
		parcel.setDataPosition(0);
		LockCharacteristicAndroid result2 = LockCharacteristicAndroid.CREATOR.createFromParcel(parcel);
		assertEquals(result1.getSetMemberLock(), result2.getSetMemberLock());
	}

	@Test
	public void test_parcelable_1_00002() {
		byte[] data = getData();

		BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
		bluetoothGattCharacteristic.setValue(data);

		LockCharacteristicAndroid result1 = new LockCharacteristicAndroid(bluetoothGattCharacteristic);
		Parcel parcel = Parcel.obtain();
		result1.writeToParcel(parcel, 0);
		parcel.setDataPosition(0);
		LockCharacteristicAndroid result2 = LockCharacteristicAndroid.CREATOR.createFromParcel(parcel);
		assertEquals(result1.getSetMemberLock(), result2.getSetMemberLock());
	}

	@Test
	public void test_parcelable_2_00001() {
		byte[] data = getData();

		BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
		bluetoothGattCharacteristic.setValue(data);

		LockCharacteristicAndroid result1 = new LockCharacteristicAndroid(bluetoothGattCharacteristic);
		assertArrayEquals(data, result1.getBytes());
	}

	@Test
	public void test_parcelable_2_00002() {
		byte[] data = getData();

		BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
		bluetoothGattCharacteristic.setValue(data);

		LockCharacteristicAndroid result1 = new LockCharacteristicAndroid(bluetoothGattCharacteristic);
		assertArrayEquals(data, result1.getBytes());
	}

	@Test
	public void test_parcelable_3_00001() {
		byte[] data = getData();

		BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
		bluetoothGattCharacteristic.setValue(data);

		LockCharacteristicAndroid result1 = new LockCharacteristicAndroid(bluetoothGattCharacteristic);
		LockCharacteristicAndroid result2 = LockCharacteristicAndroid.CREATOR.createFromByteArray(data);
		assertArrayEquals(result1.getBytes(), result2.getBytes());
	}

	@Test
	public void test_parcelable_3_00002() {
		byte[] data = getData();

		BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
		bluetoothGattCharacteristic.setValue(data);

		LockCharacteristicAndroid result1 = new LockCharacteristicAndroid(bluetoothGattCharacteristic);
		LockCharacteristicAndroid result2 = LockCharacteristicAndroid.CREATOR.createFromByteArray(data);
		assertArrayEquals(result1.getBytes(), result2.getBytes());
	}

}

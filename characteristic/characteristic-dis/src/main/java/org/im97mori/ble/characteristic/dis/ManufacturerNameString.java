package org.im97mori.ble.characteristic.dis;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.ByteArrayInterface;

import static org.im97mori.ble.BLEConstants.CharacteristicUUID.MANUFACTURER_NAME_STRING_CHARACTERISTIC;

/**
 * Manufacturer Name String (Characteristics UUID: 0x2A29)
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ManufacturerNameString implements ByteArrayInterface, Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<ManufacturerNameString> CREATOR = new ByteArrayCreater<ManufacturerNameString>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public ManufacturerNameString createFromParcel(@NonNull Parcel in) {
            return new ManufacturerNameString(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public ManufacturerNameString[] newArray(int size) {
            return new ManufacturerNameString[size];
        }

        /**
         * {@inheritDoc}
         */
        @NonNull
        public ManufacturerNameString createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(MANUFACTURER_NAME_STRING_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new ManufacturerNameString(bluetoothGattCharacteristic);
        }

    };

    /**
     * Manufacturer Name
     */
    private final String mManufacturerName;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x2A29
     */
    public ManufacturerNameString(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] values = bluetoothGattCharacteristic.getValue();
        mManufacturerName = new String(values);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private ManufacturerNameString(@NonNull Parcel in) {
        mManufacturerName = in.readString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(mManufacturerName);
    }

    /**
     * @return Manufacturer Name
     */
    public String getManufacturerName() {
        return mManufacturerName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        return mManufacturerName.getBytes();
    }

}
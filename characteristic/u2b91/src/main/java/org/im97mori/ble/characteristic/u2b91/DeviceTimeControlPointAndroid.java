package org.im97mori.ble.characteristic.u2b91;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreator;

import java.util.Objects;

/**
 * Device Time Control Point (Characteristics UUID: 0x2B91)
 */
// TODO
@SuppressWarnings({"WeakerAccess"})
public class DeviceTimeControlPointAndroid extends DeviceTimeControlPoint implements Parcelable {

    /**
     * @see ByteArrayCreator
     */
    public static final ByteArrayCreator<DeviceTimeControlPointAndroid> CREATOR = new ByteArrayCreator<DeviceTimeControlPointAndroid>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public DeviceTimeControlPointAndroid createFromParcel(@NonNull Parcel in) {
            return new DeviceTimeControlPointAndroid(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public DeviceTimeControlPointAndroid[] newArray(int size) {
            return new DeviceTimeControlPointAndroid[size];
        }

        /**
         * {@inheritDoc}
         */
        @NonNull
        public DeviceTimeControlPointAndroid createFromByteArray(@NonNull byte[] values) {
            return new DeviceTimeControlPointAndroid(values);
        }

    };

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x2B91
     */
    @Deprecated
    public DeviceTimeControlPointAndroid(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(bluetoothGattCharacteristic.getValue());
    }

    /**
     * Constructor from byte array
     *
     * @param values byte array from <a href="https://developer.android.com/reference/android/bluetooth/BluetoothGattCharacteristic#getValue()">BluetoothGattCharacteristic#getValue()</a>
     */
    public DeviceTimeControlPointAndroid(@NonNull byte[] values) {
        super(values);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private DeviceTimeControlPointAndroid(@NonNull Parcel in) {
        super(Objects.requireNonNull(in.createByteArray()));
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
        dest.writeByteArray(getBytes());
    }

}

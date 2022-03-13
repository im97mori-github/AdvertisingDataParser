package org.im97mori.ble.characteristic.u2b85;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreator;

import static org.im97mori.ble.constants.CharacteristicUUID.SIZE_CHARACTERISTIC;

/**
 * Size Characteristic (Characteristics UUID: 0x2B85)
 */
@SuppressWarnings({"WeakerAccess"})
public class SizeCharacteristicAndroid extends SizeCharacteristic implements Parcelable {

    /**
     * @see ByteArrayCreator
     */
    public static final ByteArrayCreator<SizeCharacteristicAndroid> CREATOR = new ByteArrayCreator<SizeCharacteristicAndroid>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public SizeCharacteristicAndroid createFromParcel(@NonNull Parcel in) {
            return new SizeCharacteristicAndroid(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public SizeCharacteristicAndroid[] newArray(int size) {
            return new SizeCharacteristicAndroid[size];
        }

        /**
         * {@inheritDoc}
         */
        @NonNull
        public SizeCharacteristicAndroid createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(SIZE_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new SizeCharacteristicAndroid(bluetoothGattCharacteristic);
        }

    };

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x2B85
     */
    public SizeCharacteristicAndroid(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(bluetoothGattCharacteristic.getValue());
    }

    /**
     * Constructor from parameters
     *
     * @param coordinatedSetSize Coordinated Set Size
     */
    public SizeCharacteristicAndroid(int coordinatedSetSize) {
        super(coordinatedSetSize);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private SizeCharacteristicAndroid(@NonNull Parcel in) {
        //noinspection ConstantConditions
        super(in.createByteArray());
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

package org.im97mori.ble.characteristic.u2a6f;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreator;

import static org.im97mori.ble.constants.CharacteristicUUID.HUMIDITY_CHARACTERISTIC;

/**
 * Humidity (Characteristics UUID: 0x2A6F)
 */
@SuppressWarnings({"WeakerAccess"})
public class HumidityAndroid extends Humidity implements Parcelable {

    /**
     * @see ByteArrayCreator
     */
    public static final ByteArrayCreator<HumidityAndroid> CREATOR = new ByteArrayCreator<HumidityAndroid>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public HumidityAndroid createFromParcel(@NonNull Parcel in) {
            return new HumidityAndroid(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public HumidityAndroid[] newArray(int size) {
            return new HumidityAndroid[size];
        }

        /**
         * {@inheritDoc}
         */
        @NonNull
        public HumidityAndroid createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(HUMIDITY_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new HumidityAndroid(bluetoothGattCharacteristic);
        }

    };

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x2A6F
     */
    public HumidityAndroid(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(bluetoothGattCharacteristic.getValue());
    }

    /**
     * Constructor from parameters
     *
     * @param humidity Humidity
     */
    public HumidityAndroid(int humidity) {
        super(humidity);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private HumidityAndroid(@NonNull Parcel in) {
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

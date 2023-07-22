package org.im97mori.ble.characteristic.u2bf6;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreator;

import static org.im97mori.ble.constants.CharacteristicUUID.ESL_ADDRESS_CHARACTERISTIC;

/**
 * ESL Address (Characteristics UUID: 0x2BF6)
 */
// TODO
@SuppressWarnings({"WeakerAccess"})
public class EslAddressAndroid extends EslAddress implements Parcelable {

    /**
     * @see ByteArrayCreator
     */
    public static final ByteArrayCreator<EslAddressAndroid> CREATOR = new ByteArrayCreator<EslAddressAndroid>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public EslAddressAndroid createFromParcel(@NonNull Parcel in) {
            return new EslAddressAndroid(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public EslAddressAndroid[] newArray(int size) {
            return new EslAddressAndroid[size];
        }

        /**
         * {@inheritDoc}
         */
        @NonNull
        public EslAddressAndroid createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(ESL_ADDRESS_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new EslAddressAndroid(bluetoothGattCharacteristic);
        }

    };

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x2BF6
     */
    public EslAddressAndroid(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(bluetoothGattCharacteristic.getValue());
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private EslAddressAndroid(@NonNull Parcel in) {
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

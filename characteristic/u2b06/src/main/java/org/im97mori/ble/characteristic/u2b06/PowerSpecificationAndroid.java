package org.im97mori.ble.characteristic.u2b06;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreator;

import static org.im97mori.ble.constants.CharacteristicUUID.POWER_SPECIFICATION_CHARACTERISTIC;

/**
 * Power Specification (Characteristics UUID: 0x2B06)
 */
@SuppressWarnings({"WeakerAccess"})
public class PowerSpecificationAndroid extends PowerSpecification implements Parcelable {

    /**
     * @see ByteArrayCreator
     */
    public static final ByteArrayCreator<PowerSpecificationAndroid> CREATOR = new ByteArrayCreator<PowerSpecificationAndroid>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public PowerSpecificationAndroid createFromParcel(@NonNull Parcel in) {
            return new PowerSpecificationAndroid(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public PowerSpecificationAndroid[] newArray(int size) {
            return new PowerSpecificationAndroid[size];
        }

        /**
         * {@inheritDoc}
         */
        @NonNull
        public PowerSpecificationAndroid createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(POWER_SPECIFICATION_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new PowerSpecificationAndroid(bluetoothGattCharacteristic);
        }

    };

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x2B06
     */
    public PowerSpecificationAndroid(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(bluetoothGattCharacteristic.getValue());
    }

    /**
     * Constructor from parameters
     *
     * @param minimumPowerValue Minimum Power Value
     * @param typicalPowerValue Typical Power Value
     * @param maximumPowerValue Maximum Power Value
     */
    public PowerSpecificationAndroid(int minimumPowerValue, int typicalPowerValue, int maximumPowerValue) {
        super(minimumPowerValue, typicalPowerValue, maximumPowerValue);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private PowerSpecificationAndroid(@NonNull Parcel in) {
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

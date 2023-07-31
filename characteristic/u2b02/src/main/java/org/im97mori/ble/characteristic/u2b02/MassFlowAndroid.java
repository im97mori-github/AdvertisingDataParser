package org.im97mori.ble.characteristic.u2b02;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreator;

import java.util.Objects;

/**
 * Mass Flow (Characteristics UUID: 0x2B02)
 */
@SuppressWarnings({"WeakerAccess"})
public class MassFlowAndroid extends MassFlow implements Parcelable {

    /**
     * @see ByteArrayCreator
     */
    public static final ByteArrayCreator<MassFlowAndroid> CREATOR = new ByteArrayCreator<MassFlowAndroid>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public MassFlowAndroid createFromParcel(@NonNull Parcel in) {
            return new MassFlowAndroid(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public MassFlowAndroid[] newArray(int size) {
            return new MassFlowAndroid[size];
        }

        /**
         * {@inheritDoc}
         */
        @NonNull
        public MassFlowAndroid createFromByteArray(@NonNull byte[] values) {
            return new MassFlowAndroid(values);
        }

    };

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x2B02
     */
    @Deprecated
    public MassFlowAndroid(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(bluetoothGattCharacteristic.getValue());
    }

    /**
     * Constructor from byte array
     *
     * @param values byte array from <a href="https://developer.android.com/reference/android/bluetooth/BluetoothGattCharacteristic#getValue()">BluetoothGattCharacteristic#getValue()</a>
     */
    public MassFlowAndroid(@NonNull byte[] values) {
        super(values);
    }

    /**
     * Constructor from parameters
     *
     * @param massFlow Mass Flow
     */
    public MassFlowAndroid(int massFlow) {
        super(massFlow);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private MassFlowAndroid(@NonNull Parcel in) {
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

package org.im97mori.ble.characteristic.u2af1;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreator;

import java.util.Objects;

/**
 * Electric Current Statistics (Characteristics UUID: 0x2AF1)
 */
@SuppressWarnings({"WeakerAccess"})
public class ElectricCurrentStatisticsAndroid extends ElectricCurrentStatistics implements Parcelable {

    /**
     * @see ByteArrayCreator
     */
    public static final ByteArrayCreator<ElectricCurrentStatisticsAndroid> CREATOR = new ByteArrayCreator<ElectricCurrentStatisticsAndroid>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public ElectricCurrentStatisticsAndroid createFromParcel(@NonNull Parcel in) {
            return new ElectricCurrentStatisticsAndroid(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public ElectricCurrentStatisticsAndroid[] newArray(int size) {
            return new ElectricCurrentStatisticsAndroid[size];
        }

        /**
         * {@inheritDoc}
         */
        @NonNull
        public ElectricCurrentStatisticsAndroid createFromByteArray(@NonNull byte[] values) {
            return new ElectricCurrentStatisticsAndroid(values);
        }

    };

    /**
     * Constructor from parameters
     *
     * @param averageElectricCurrentValue           Average Electric Current Value
     * @param standardDeviationElectricCurrentValue Standard Deviation Electric Current Value
     * @param minimumElectricCurrentValue           Minimum Electric Current Value
     * @param maximumElectricCurrentValue           Maximum Electric Current Value
     */
    public ElectricCurrentStatisticsAndroid(int averageElectricCurrentValue, int standardDeviationElectricCurrentValue, int minimumElectricCurrentValue, int maximumElectricCurrentValue) {
        super(averageElectricCurrentValue, standardDeviationElectricCurrentValue, minimumElectricCurrentValue, maximumElectricCurrentValue);
    }

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x2AF1
     */
    @Deprecated
    public ElectricCurrentStatisticsAndroid(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(bluetoothGattCharacteristic.getValue());
    }

    /**
     * Constructor from byte array
     *
     * @param values byte array from <a href="https://developer.android.com/reference/android/bluetooth/BluetoothGattCharacteristic#getValue()">BluetoothGattCharacteristic#getValue()</a>
     */
    public ElectricCurrentStatisticsAndroid(@NonNull byte[] values) {
        super(values);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private ElectricCurrentStatisticsAndroid(@NonNull Parcel in) {
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

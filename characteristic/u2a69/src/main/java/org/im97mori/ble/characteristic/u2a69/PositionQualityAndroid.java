package org.im97mori.ble.characteristic.u2a69;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreator;

import static org.im97mori.ble.constants.CharacteristicUUID.POSITION_QUALITY_CHARACTERISTIC;

/**
 * Position Quality (Characteristics UUID: 0x2A69)
 */
@SuppressWarnings({"WeakerAccess"})
public class PositionQualityAndroid extends PositionQuality implements Parcelable {

    /**
     * @see ByteArrayCreator
     */
    public static final ByteArrayCreator<PositionQualityAndroid> CREATOR = new ByteArrayCreator<PositionQualityAndroid>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public PositionQualityAndroid createFromParcel(@NonNull Parcel in) {
            return new PositionQualityAndroid(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public PositionQualityAndroid[] newArray(int size) {
            return new PositionQualityAndroid[size];
        }

        /**
         * {@inheritDoc}
         */
        @NonNull
        public PositionQualityAndroid createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(POSITION_QUALITY_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new PositionQualityAndroid(bluetoothGattCharacteristic);
        }

    };

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x2A69
     */
    public PositionQualityAndroid(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(bluetoothGattCharacteristic.getValue());
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private PositionQualityAndroid(@NonNull Parcel in) {
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

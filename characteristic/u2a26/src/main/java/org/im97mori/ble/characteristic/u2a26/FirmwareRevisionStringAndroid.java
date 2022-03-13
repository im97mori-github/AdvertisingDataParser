package org.im97mori.ble.characteristic.u2a26;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreator;

import static org.im97mori.ble.constants.CharacteristicUUID.FIRMWARE_REVISION_STRING_CHARACTERISTIC;

/**
 * Firmware Revision String (Characteristics UUID: 0x2A26)
 */
@SuppressWarnings({"WeakerAccess"})
public class FirmwareRevisionStringAndroid extends FirmwareRevisionString implements Parcelable {

    /**
     * @see ByteArrayCreator
     */
    public static final ByteArrayCreator<FirmwareRevisionStringAndroid> CREATOR = new ByteArrayCreator<FirmwareRevisionStringAndroid>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public FirmwareRevisionStringAndroid createFromParcel(@NonNull Parcel in) {
            return new FirmwareRevisionStringAndroid(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public FirmwareRevisionStringAndroid[] newArray(int size) {
            return new FirmwareRevisionStringAndroid[size];
        }

        /**
         * {@inheritDoc}
         */
        @NonNull
        public FirmwareRevisionStringAndroid createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(FIRMWARE_REVISION_STRING_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new FirmwareRevisionStringAndroid(bluetoothGattCharacteristic);
        }

    };

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x2A26
     */
    public FirmwareRevisionStringAndroid(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(bluetoothGattCharacteristic.getValue());
    }

    /**
     * Constructor from parameters
     *
     * @param firmwareRevision Firmware Revision
     */
    public FirmwareRevisionStringAndroid(@NonNull String firmwareRevision) {
        super(firmwareRevision);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private FirmwareRevisionStringAndroid(@NonNull Parcel in) {
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

package org.im97mori.ble.characteristic.u2bd7;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreator;
import org.im97mori.ble.characteristic.core.IEEE_11073_20601_SFLOAT;

import static org.im97mori.ble.constants.CharacteristicUUID.PARTICULATE_MATTER_PM10_CONCENTRATION_CHARACTERISTIC;

/**
 * Particulate Matter - PM10 Concentration (Characteristics UUID: 0x2BD7)
 */
@SuppressWarnings({"WeakerAccess"})
public class ParticulateMatterPm10ConcentrationAndroid extends ParticulateMatterPm10Concentration implements Parcelable {

    /**
     * @see ByteArrayCreator
     */
    public static final ByteArrayCreator<ParticulateMatterPm10ConcentrationAndroid> CREATOR = new ByteArrayCreator<ParticulateMatterPm10ConcentrationAndroid>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public ParticulateMatterPm10ConcentrationAndroid createFromParcel(@NonNull Parcel in) {
            return new ParticulateMatterPm10ConcentrationAndroid(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public ParticulateMatterPm10ConcentrationAndroid[] newArray(int size) {
            return new ParticulateMatterPm10ConcentrationAndroid[size];
        }

        /**
         * {@inheritDoc}
         */
        @NonNull
        public ParticulateMatterPm10ConcentrationAndroid createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(PARTICULATE_MATTER_PM10_CONCENTRATION_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new ParticulateMatterPm10ConcentrationAndroid(bluetoothGattCharacteristic);
        }

    };

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x2BD7
     */
    public ParticulateMatterPm10ConcentrationAndroid(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(bluetoothGattCharacteristic.getValue());
    }

    /**
     * Constructor from parameters
     *
     * @param particulateMatterPm10Concentration Particulate Matter - PM10
     *                                           Concentration
     */
    public ParticulateMatterPm10ConcentrationAndroid(@NonNull IEEE_11073_20601_SFLOAT particulateMatterPm10Concentration) {
        super(particulateMatterPm10Concentration);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private ParticulateMatterPm10ConcentrationAndroid(@NonNull Parcel in) {
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

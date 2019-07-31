package org.im97mori.ble.task;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import android.os.Message;
import android.text.format.DateUtils;

import org.im97mori.ble.BLEConnection;
import org.im97mori.ble.TaskHandler;

import java.util.UUID;

import static org.im97mori.ble.BLEConstants.ErrorCodes.UNKNOWN;

/**
 * Read descriptor task
 */
@SuppressWarnings("JavadocReference")
public class ReadDescriptorTask extends AbstractBLETask {

    /**
     * Default timeout(millis) for read:5sec
     */
    public static final long TIMEOUT_MILLIS = DateUtils.SECOND_IN_MILLIS * 5;

    /**
     * create read descriptor message
     *
     * @param characteristicUUID target characteristic UUID
     * @param descriptorUUID     target descriptor UUID
     * @param obj                instance for {@link android.os.Handler#removeCallbacksAndMessages(Object)}
     * @return read descriptor {@link Message} instance
     */
    public static Message createReadDescriptorMessage(UUID characteristicUUID, UUID descriptorUUID, Object obj) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_CHARACTERISTIC_UUID, characteristicUUID);
        bundle.putSerializable(KEY_DESCRIPTOR_UUID, descriptorUUID);
        bundle.putInt(KEY_NEXT_PROGRESS, PROGRESS_DESCRIPTOR_READ_START);
        Message message = new Message();
        message.setData(bundle);
        message.obj = obj;
        return message;
    }

    /**
     * create read descriptor finished message
     *
     * @param characteristicUUID target characteristic UUID
     * @param descriptorUUID     target descriptor UUID
     * @param values             {@link BluetoothGattDescriptor#getValue()}
     * @return read descriptor finished {@link Message} instance
     */
    public static Message createReadDescriptorFinishedMessage(UUID characteristicUUID, UUID descriptorUUID, byte[] values) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_CHARACTERISTIC_UUID, characteristicUUID);
        bundle.putSerializable(KEY_DESCRIPTOR_UUID, descriptorUUID);
        bundle.putByteArray(KEY_VALUES, values);
        bundle.putInt(KEY_NEXT_PROGRESS, PROGRESS_DESCRIPTOR_READ_SUCCESS);
        Message message = new Message();
        message.setData(bundle);
        return message;
    }

    /**
     * create read descriptor finished message
     *
     * @param characteristicUUID target characteristic UUID
     * @param descriptorUUID     target descriptor UUID
     * @param status             {@link android.bluetooth.BluetoothGattCallback#onDescriptorRead(BluetoothGatt, BluetoothGattDescriptor, int)} 3rd parameter
     * @return read descriptor error {@link Message} instance
     */
    public static Message createReadDescriptorErrorMessage(UUID characteristicUUID, UUID descriptorUUID, int status) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_CHARACTERISTIC_UUID, characteristicUUID);
        bundle.putSerializable(KEY_DESCRIPTOR_UUID, descriptorUUID);
        bundle.putInt(KEY_STATUS, status);
        bundle.putInt(KEY_NEXT_PROGRESS, PROGRESS_DESCRIPTOR_READ_ERROR);
        Message message = new Message();
        message.setData(bundle);
        return message;
    }

    /**
     * task target {@link BLEConnection} instance
     */
    private final BLEConnection mBLEConnection;

    /**
     * task target {@link BluetoothGatt} instance
     */
    private final BluetoothGatt mBluetoothGatt;

    /**
     * task target {@link TaskHandler} instance
     */
    private final TaskHandler mTaskHandler;

    /**
     * task target service {@link UUID}
     */
    private final UUID mServiceUUID;

    /**
     * task target characteristic {@link UUID}
     */
    private final UUID mCharacteristicUUID;

    /**
     * task target descriptor {@link UUID}
     */
    private final UUID mDescriptorUUID;

    /**
     * timeout(millis)
     */
    private final long mTimeout;

    /**
     * @param bleConnection                     task target {@link BLEConnection} instance
     * @param bluetoothGatt                     task target {@link TaskHandler} instance
     * @param taskHandler                       task target {@link BluetoothGatt} instance
     * @param serviceUUID                       task target service {@link UUID}
     * @param characteristicUUID                task target characteristic {@link UUID}
     * @param clientCharacteristicConfiguration task target notification setting
     * @param timeout                           timeout(millis)
     */
    public ReadDescriptorTask(BLEConnection bleConnection, BluetoothGatt bluetoothGatt, TaskHandler taskHandler, UUID serviceUUID, UUID characteristicUUID, UUID descriptorUUID, long timeout) {
        mBLEConnection = bleConnection;
        mBluetoothGatt = bluetoothGatt;
        mTaskHandler = taskHandler;
        mServiceUUID = serviceUUID;
        mCharacteristicUUID = characteristicUUID;
        mDescriptorUUID = descriptorUUID;
        mTimeout = timeout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doProcess(Message message) {
        Bundle bundle = message.getData();
        UUID characteristicUUID = (UUID) bundle.getSerializable(KEY_CHARACTERISTIC_UUID);
        int nextProgress = bundle.getInt(KEY_NEXT_PROGRESS);

        // timeout
        if (this == message.obj && PROGRESS_TIMEOUT == nextProgress) {
            mBLEConnection.getBLECallback().onDescriptorReadTimeout(mBLEConnection.getBluetoothDevice(), mCharacteristicUUID, mDescriptorUUID, mTimeout);
            mCurrentProgress = PROGRESS_TIMEOUT;
        } else if (PROGRESS_INIT == mCurrentProgress) {
            // current:init, next:read start
            if (message.obj == this && PROGRESS_DESCRIPTOR_READ_START == nextProgress) {
                BluetoothGattService bluetoothGattService = mBluetoothGatt.getService(mServiceUUID);
                if (bluetoothGattService != null) {
                    BluetoothGattCharacteristic bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(mCharacteristicUUID);
                    if (bluetoothGattCharacteristic != null) {
                        BluetoothGattDescriptor bluetoothGattDescriptor = bluetoothGattCharacteristic.getDescriptor(mDescriptorUUID);
                        if (bluetoothGattDescriptor != null) {
                            // read descriptor
                            if (mBluetoothGatt.readDescriptor(bluetoothGattDescriptor)) {

                                // set timeout message
                                mTaskHandler.sendProcessingMessage(createTimeoutMessage(mCharacteristicUUID, this), mTimeout);
                            } else {
                                nextProgress = PROGRESS_FINISHED;
                                mBLEConnection.getBLECallback().onDescriptorReadFailed(mBLEConnection.getBluetoothDevice(), mCharacteristicUUID, mDescriptorUUID, UNKNOWN);
                            }
                        }
                    }
                }
                mCurrentProgress = nextProgress;
            }
        } else if (PROGRESS_DESCRIPTOR_READ_START == mCurrentProgress) {
            if (mCharacteristicUUID.equals(characteristicUUID)) {
                // current:read start, next:read success
                if (PROGRESS_DESCRIPTOR_READ_SUCCESS == nextProgress) {
                    mBLEConnection.getBLECallback().onDescriptorReadSuccess(mBLEConnection.getBluetoothDevice(), mCharacteristicUUID, mDescriptorUUID, bundle.getByteArray(KEY_VALUES));
                } else if (PROGRESS_DESCRIPTOR_READ_ERROR == nextProgress) {
                    // current:read start, next:read error
                    mBLEConnection.getBLECallback().onDescriptorReadFailed(mBLEConnection.getBluetoothDevice(), mCharacteristicUUID, mDescriptorUUID, bundle.getInt(KEY_STATUS));
                }

                mCurrentProgress = PROGRESS_FINISHED;
                // remove timeout message
                mTaskHandler.removeCallbacksAndMessages(this);
            }
        }

        return PROGRESS_FINISHED == mCurrentProgress || PROGRESS_TIMEOUT == mCurrentProgress;
    }
}

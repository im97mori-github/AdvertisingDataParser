package org.im97mori.ble.task;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import android.os.Message;
import android.text.format.DateUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.BLEConnection;
import org.im97mori.ble.BLELogUtils;
import org.im97mori.ble.TaskHandler;

import java.util.List;
import java.util.UUID;

import static org.im97mori.ble.constants.ErrorCodeAndroid.BUSY;
import static org.im97mori.ble.constants.ErrorCodeAndroid.CANCEL;
import static org.im97mori.ble.constants.ErrorCodeAndroid.UNKNOWN;

/**
 * Read characteristic task
 * <p>
 * for central role
 */
public class ReadCharacteristicTask extends AbstractBLETask {

    /**
     * Default timeout(millis) for read characteristic:30sec
     */
    public static final long TIMEOUT_MILLIS = DateUtils.SECOND_IN_MILLIS * 30;

    /**
     * create read characteristic success message
     *
     * @param serviceUUID              target service UUID
     * @param serviceInstanceId        task target service incetanceId {@link BluetoothGattService#getInstanceId()}
     * @param characteristicUUID       target characteristic UUID
     * @param characteristicInstanceId task target characteristic incetanceId {@link BluetoothGattCharacteristic#getInstanceId()}
     * @param values                   {@link BluetoothGattCharacteristic#getValue()}
     * @return read characteristic success {@link Message} instance
     */
    @NonNull
    public static Message createReadCharacteristicSuccessMessage(@NonNull UUID serviceUUID, int serviceInstanceId, @NonNull UUID characteristicUUID, int characteristicInstanceId, @NonNull byte[] values) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_SERVICE_UUID, serviceUUID);
        bundle.putInt(KEY_SERVICE_INSTANCE_ID, serviceInstanceId);
        bundle.putSerializable(KEY_CHARACTERISTIC_UUID, characteristicUUID);
        bundle.putInt(KEY_CHARACTERISTIC_INSTANCE_ID, characteristicInstanceId);
        bundle.putByteArray(KEY_VALUES, values);
        bundle.putInt(KEY_NEXT_PROGRESS, PROGRESS_CHARACTERISTIC_READ_SUCCESS);
        Message message = new Message();
        message.setData(bundle);
        return message;
    }

    /**
     * create read characteristic error message
     *
     * @param serviceUUID              target service UUID
     * @param serviceInstanceId        task target service incetanceId {@link BluetoothGattService#getInstanceId()}
     * @param characteristicUUID       target characteristic UUID
     * @param characteristicInstanceId task target characteristic incetanceId {@link BluetoothGattCharacteristic#getInstanceId()}
     * @param status                   {@link android.bluetooth.BluetoothGattCallback#onCharacteristicRead(BluetoothGatt, BluetoothGattCharacteristic, int)} 3rd parameter
     * @return read characteristic error {@link Message} instance
     */
    @NonNull
    public static Message createReadCharacteristicErrorMessage(@NonNull UUID serviceUUID, int serviceInstanceId, @NonNull UUID characteristicUUID, int characteristicInstanceId, int status) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_SERVICE_UUID, serviceUUID);
        bundle.putInt(KEY_SERVICE_INSTANCE_ID, serviceInstanceId);
        bundle.putSerializable(KEY_CHARACTERISTIC_UUID, characteristicUUID);
        bundle.putInt(KEY_CHARACTERISTIC_INSTANCE_ID, characteristicInstanceId);
        bundle.putInt(KEY_STATUS, status);
        bundle.putInt(KEY_NEXT_PROGRESS, PROGRESS_CHARACTERISTIC_READ_ERROR);
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
     * task target service incetanceId {@link BluetoothGattService#getInstanceId()}
     */
    private Integer mServiceInstanceId;

    /**
     * task target characteristic {@link UUID}
     */
    private final UUID mCharacteristicUUID;

    /**
     * task target characteristic incetanceId {@link BluetoothGattCharacteristic#getInstanceId()}
     */
    private Integer mCharacteristicInstanceId;

    /**
     * timeout(millis)
     */
    private final long mTimeout;

    /**
     * callback argument
     */
    private final Bundle mArgument;

    /**
     * @param bleConnection            task target {@link BLEConnection} instance
     * @param bluetoothGatt            task target {@link BluetoothGatt} instance
     * @param taskHandler              task target {@link TaskHandler} instance
     * @param serviceUUID              task target service {@link UUID}
     * @param serviceInstanceId        task target service incetanceId {@link BluetoothGattService#getInstanceId()}
     * @param characteristicUUID       task target characteristic {@link UUID}
     * @param characteristicInstanceId task target characteristic incetanceId {@link BluetoothGattCharacteristic#getInstanceId()}
     * @param timeout                  timeout(millis)
     * @param argument                 callback argument
     */
    public ReadCharacteristicTask(@NonNull BLEConnection bleConnection
            , @NonNull BluetoothGatt bluetoothGatt
            , @NonNull TaskHandler taskHandler
            , @NonNull UUID serviceUUID
            , @Nullable Integer serviceInstanceId
            , @NonNull UUID characteristicUUID
            , @Nullable Integer characteristicInstanceId
            , long timeout
            , @NonNull Bundle argument) {
        mBLEConnection = bleConnection;
        mBluetoothGatt = bluetoothGatt;
        mServiceUUID = serviceUUID;
        mServiceInstanceId = serviceInstanceId;
        mCharacteristicUUID = characteristicUUID;
        mCharacteristicInstanceId = characteristicInstanceId;
        mTaskHandler = taskHandler;
        mTimeout = timeout;
        mArgument = argument;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Message createInitialMessage() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_SERVICE_UUID, mServiceUUID);
        bundle.putSerializable(KEY_CHARACTERISTIC_UUID, mCharacteristicUUID);
        bundle.putInt(KEY_NEXT_PROGRESS, PROGRESS_CHARACTERISTIC_READ_START);
        Message message = new Message();
        message.setData(bundle);
        message.obj = this;
        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doProcess(@NonNull Message message) {
        Bundle bundle = message.getData();
        if (bundle.containsKey(KEY_NEXT_PROGRESS)) {
            UUID serviceUUID = (UUID) bundle.getSerializable(KEY_SERVICE_UUID);
            int serviceInstanceId = bundle.getInt(KEY_SERVICE_INSTANCE_ID);
            UUID characteristicUUID = (UUID) bundle.getSerializable(KEY_CHARACTERISTIC_UUID);
            int characteristicInstanceId = bundle.getInt(KEY_CHARACTERISTIC_INSTANCE_ID);
            int nextProgress = bundle.getInt(KEY_NEXT_PROGRESS);

            // timeout
            if (this == message.obj && PROGRESS_TIMEOUT == nextProgress) {
                mBLEConnection.getBLECallback().onCharacteristicReadTimeout(getTaskId(), mBLEConnection.getBluetoothDevice(), mServiceUUID, mServiceInstanceId, mCharacteristicUUID, mCharacteristicInstanceId, mTimeout, mArgument);
                mCurrentProgress = nextProgress;
            } else if (PROGRESS_INIT == mCurrentProgress) {
                // current:init, next:read characteristic start
                if (this == message.obj && PROGRESS_CHARACTERISTIC_READ_START == nextProgress) {

                    BluetoothGattCharacteristic bluetoothGattCharacteristic = null;
                    boolean result = false;
                    BluetoothGattService bluetoothGattService = null;
                    if (mServiceInstanceId == null) {
                        bluetoothGattService = mBluetoothGatt.getService(mServiceUUID);
                    } else {
                        // multiple service
                        List<BluetoothGattService> serviceList = mBluetoothGatt.getServices();
                        for (BluetoothGattService targetBluetoothGattService : serviceList) {
                            if (mServiceUUID.equals(targetBluetoothGattService.getUuid()) && mServiceInstanceId == targetBluetoothGattService.getInstanceId()) {
                                bluetoothGattService = targetBluetoothGattService;
                                break;
                            }
                        }
                    }
                    if (bluetoothGattService != null) {
                        if (mCharacteristicInstanceId == null) {
                            bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(mCharacteristicUUID);
                        } else {
                            // multiple characteristic
                            List<BluetoothGattCharacteristic> characteristicList = bluetoothGattService.getCharacteristics();
                            for (BluetoothGattCharacteristic targetBluetoothGattCharacteristic : characteristicList) {
                                if (mCharacteristicUUID.equals(targetBluetoothGattCharacteristic.getUuid()) && mCharacteristicInstanceId == targetBluetoothGattCharacteristic.getInstanceId()) {
                                    bluetoothGattCharacteristic = targetBluetoothGattCharacteristic;
                                    break;
                                }
                            }
                        }
                        if (bluetoothGattCharacteristic != null) {
                            // read characteristic
                            mServiceInstanceId = bluetoothGattService.getInstanceId();
                            mCharacteristicInstanceId = bluetoothGattCharacteristic.getInstanceId();
                            try {
                                result = mBluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
                            } catch (Exception e) {
                                BLELogUtils.stackLog(e);
                            }
                        }
                    }

                    if (result) {
                        // set timeout message
                        mTaskHandler.sendProcessingMessage(createTimeoutMessage(this), mTimeout);
                    } else {
                        if (bluetoothGattCharacteristic == null) {
                            nextProgress = PROGRESS_FINISHED;
                            mBLEConnection.getBLECallback().onCharacteristicReadFailed(getTaskId(), mBLEConnection.getBluetoothDevice(), mServiceUUID, mServiceInstanceId, mCharacteristicUUID, mCharacteristicInstanceId, UNKNOWN, mArgument);
                        } else {
                            nextProgress = PROGRESS_BUSY;
                            mBLEConnection.getBLECallback().onCharacteristicReadFailed(getTaskId(), mBLEConnection.getBluetoothDevice(), mServiceUUID, mServiceInstanceId, mCharacteristicUUID, mCharacteristicInstanceId, BUSY, mArgument);
                        }
                    }
                    mCurrentProgress = nextProgress;
                }
            } else if (PROGRESS_CHARACTERISTIC_READ_START == mCurrentProgress) {
                if (mServiceUUID.equals(serviceUUID) && mServiceInstanceId == serviceInstanceId && mCharacteristicUUID.equals(characteristicUUID) && mCharacteristicInstanceId == characteristicInstanceId) {
                    // current:read characteristic start, next:read characteristic success
                    if (PROGRESS_CHARACTERISTIC_READ_SUCCESS == nextProgress) {
                        byte[] value = bundle.getByteArray(KEY_VALUES);
                        if (value == null) {
                            mBLEConnection.getBLECallback().onCharacteristicReadFailed(getTaskId(), mBLEConnection.getBluetoothDevice(), mServiceUUID, mServiceInstanceId, mCharacteristicUUID, mCharacteristicInstanceId, UNKNOWN, mArgument);
                        } else {
                            mBLEConnection.getBLECallback().onCharacteristicReadSuccess(getTaskId(), mBLEConnection.getBluetoothDevice(), mServiceUUID, mServiceInstanceId, mCharacteristicUUID, mCharacteristicInstanceId, value, mArgument);
                        }
                    } else if (PROGRESS_CHARACTERISTIC_READ_ERROR == nextProgress) {
                        // current:read characteristic start, next:read characteristic error
                        mBLEConnection.getBLECallback().onCharacteristicReadFailed(getTaskId(), mBLEConnection.getBluetoothDevice(), mServiceUUID, mServiceInstanceId, mCharacteristicUUID, mCharacteristicInstanceId, bundle.getInt(KEY_STATUS), mArgument);
                    }
                    mCurrentProgress = PROGRESS_FINISHED;
                    // remove timeout message
                    mTaskHandler.removeCallbacksAndMessages(this);
                }
            }
        }

        return PROGRESS_FINISHED == mCurrentProgress || PROGRESS_BUSY == mCurrentProgress || PROGRESS_TIMEOUT == mCurrentProgress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBusy() {
        return PROGRESS_BUSY == mCurrentProgress || PROGRESS_TIMEOUT == mCurrentProgress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancel() {
        mTaskHandler.removeCallbacksAndMessages(this);
        mCurrentProgress = PROGRESS_FINISHED;
        mBLEConnection.getBLECallback().onCharacteristicReadFailed(getTaskId(), mBLEConnection.getBluetoothDevice(), mServiceUUID, mServiceInstanceId, mCharacteristicUUID, mCharacteristicInstanceId, CANCEL, mArgument);
    }

}

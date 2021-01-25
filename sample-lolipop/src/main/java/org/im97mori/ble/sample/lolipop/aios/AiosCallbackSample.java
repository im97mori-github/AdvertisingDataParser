package org.im97mori.ble.sample.lolipop.aios;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.le.AdvertiseSettings;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.BLECallback;
import org.im97mori.ble.BLEServerConnection;
import org.im97mori.ble.BLEUtils;
import org.im97mori.ble.MockData;
import org.im97mori.ble.characteristic.u2a56.DigitalAndroid;
import org.im97mori.ble.characteristic.u2a58.AnalogAndroid;
import org.im97mori.ble.characteristic.u2a5a.AggregateAndroid;
import org.im97mori.ble.descriptor.u2900.CharacteristicExtendedPropertiesAndroid;
import org.im97mori.ble.descriptor.u2901.CharacteristicUserDescriptionAndroid;
import org.im97mori.ble.descriptor.u2902.ClientCharacteristicConfigurationAndroid;
import org.im97mori.ble.descriptor.u2904.CharacteristicPresentationFormatAndroid;
import org.im97mori.ble.descriptor.u2906.ValidRangeAndroid;
import org.im97mori.ble.descriptor.u2909.NumberOfDigitalsAndroid;
import org.im97mori.ble.descriptor.u290a.ValueTriggerSettingAndroid;
import org.im97mori.ble.descriptor.u290e.TimeTriggerSettingAndroid;
import org.im97mori.ble.sample.lolipop.SampleCallback;
import org.im97mori.ble.service.aios.central.AutomationIOServiceCallback;
import org.im97mori.ble.service.aios.peripheral.AutomationIOServiceMockCallback;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class AiosCallbackSample extends AutomationIOServiceMockCallback implements AutomationIOServiceCallback, BLECallback {

    public static class Builder extends AutomationIOServiceMockCallback.Builder<AiosCallbackSample> {

        private final SampleCallback mSampleCallback;

        public Builder(SampleCallback sampleCallback) {
            mSampleCallback = sampleCallback;
        }

        @NonNull
        @Override
        public AiosCallbackSample build() {
            return new AiosCallbackSample(createMockData(), mSampleCallback);
        }
    }

    private final SimpleDateFormat format = new SimpleDateFormat("MM/dd HH:mm:ss", Locale.US);

    private final SampleCallback mSampleCallback;

    AiosCallbackSample(@NonNull MockData mockData, SampleCallback sampleCallback) {
        super(mockData, false);
        mSampleCallback = sampleCallback;
    }

    private void callback(Object... logs) {
        int index = 0;
        StackTraceElement[] stackTraceElementArray = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTraceElementArray.length; i++) {
            StackTraceElement stackTraceElement = stackTraceElementArray[i];
            if (this.getClass().getName().equals(stackTraceElement.getClassName())
                    && "callback".equals(stackTraceElement.getMethodName())) {
                index = i + 1;
                break;
            }
        }

        String now;
        synchronized (format) {
            now = format.format(new Date());
            format.notifyAll();
        }
        if (logs.length == 0) {
            mSampleCallback.onCallbacked(Pair.create(now, stackTraceElementArray[index].getMethodName()));
        } else {
            mSampleCallback.onCallbacked(Pair.create(now, stackTraceElementArray[index].getMethodName() + '\n' + TextUtils.join("\n", logs)));
        }
    }

    @Override
    public void onBLEConnected(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @Nullable Bundle argument) {
        callback(argument);
    }

    @Override
    public void onBLEConnectFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status, argument);
    }

    @Override
    public void onBLEConnectTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @Nullable Bundle argument) {
        callback(argument);
    }

    @Override
    public void onBLEDisconnected(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status, argument);
    }

    @Override
    public void onDiscoverServiceSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull List<BluetoothGattService> serviceList, @Nullable Bundle argument) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < serviceList.size(); i++) {
            BluetoothGattService service = serviceList.get(i);
            List<BluetoothGattCharacteristic> characteristicList = service.getCharacteristics();
            if (characteristicList.isEmpty()) {
                writeServiceList(service.getUuid(), 0, null, 0, null, sb);
            } else {
                for (int j = 0; j < characteristicList.size(); j++) {
                    BluetoothGattCharacteristic characteristic = characteristicList.get(j);
                    List<BluetoothGattDescriptor> descriptorList = characteristic.getDescriptors();
                    if (descriptorList.isEmpty()) {
                        writeServiceList(service.getUuid(), j, characteristic.getUuid(), 0, null, sb);
                    } else {
                        for (int k = 0; k < descriptorList.size(); k++) {
                            BluetoothGattDescriptor descriptor = descriptorList.get(k);
                            writeServiceList(service.getUuid(), j, characteristic.getUuid(), k, descriptor.getUuid(), sb);
                        }
                    }
                }
            }
        }
        callback(sb, argument);
    }

    private void writeServiceList(@NonNull UUID serviceUUID, int characteristicLineNumber, @Nullable UUID characteristicUUID, int descriptorLineNumber, @Nullable UUID descriptorUUID, @NonNull StringBuilder sb) {
        if (characteristicLineNumber == 0) {
            sb.append(serviceUUID.toString().substring(4, 8));
        } else {
            sb.append("    ");
        }
        sb.append('\t');

        if (characteristicUUID != null) {
            if (descriptorLineNumber == 0) {
                sb.append(characteristicUUID.toString().substring(4, 8));
            } else {
                sb.append("    ");
            }
            sb.append('\t');

            if (descriptorUUID != null) {
                sb.append(descriptorUUID.toString().substring(4, 8));
            }
        }
        sb.append('\n');
    }

    @Override
    public void onDiscoverServiceFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status, argument);
    }

    @Override
    public void onDiscoverServiceTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout, argument);
    }

    @Override
    public void onRequestMtuSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int mtu, @Nullable Bundle argument) {
        callback(mtu, argument);
    }

    @Override
    public void onRequestMtuFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status, argument);
    }

    @Override
    public void onRequestMtuTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout, argument);
    }

    @Override
    public void onCharacteristicReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @NonNull byte[] values, @Nullable Bundle argument) {
        callback(characteristicUUID, Arrays.toString(values), argument);
    }

    @Override
    public void onCharacteristicReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, int status, Bundle argument) {
        callback(status, argument);
    }

    @Override
    public void onCharacteristicReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, long timeout, @Nullable Bundle argument) {
        callback(characteristicUUID, timeout, argument);
    }

    @Override
    public void onCharacteristicWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @NonNull byte[] values, @Nullable Bundle argument) {
        callback(characteristicUUID, Arrays.toString(values), argument);
    }

    @Override
    public void onCharacteristicWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, int status, @Nullable Bundle argument) {
        callback(characteristicUUID, status, argument);
    }

    @Override
    public void onCharacteristicWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, long timeout, @Nullable Bundle argument) {
        callback(characteristicUUID, timeout, argument);
    }

    @Override
    public void onDescriptorReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @NonNull UUID descriptorUUID, @NonNull byte[] values, @Nullable Bundle argument) {
        callback(characteristicUUID, descriptorUUID, Arrays.toString(values), argument);
    }

    @Override
    public void onDescriptorReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @NonNull UUID descriptorUUID, int status, @Nullable Bundle argument) {
        callback(characteristicUUID, descriptorUUID, status, argument);
    }

    @Override
    public void onDescriptorReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @NonNull UUID descriptorUUID, long timeout, @Nullable Bundle argument) {
        callback(characteristicUUID, descriptorUUID, timeout, argument);
    }

    @Override
    public void onDescriptorWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @NonNull UUID descriptorUUID, @NonNull byte[] values, @Nullable Bundle argument) {
        callback(characteristicUUID, descriptorUUID, Arrays.toString(values), argument);
    }

    @Override
    public void onDescriptorWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @NonNull UUID descriptorUUID, int status, @Nullable Bundle argument) {
        callback(characteristicUUID, descriptorUUID, status, argument);
    }

    @Override
    public void onDescriptorWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @NonNull UUID descriptorUUID, long timeout, @Nullable Bundle argument) {
        callback(characteristicUUID, descriptorUUID, timeout, argument);
    }

    @Override
    public void onCharacteristicNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @NonNull byte[] values) {
        callback(serviceUUID, characteristicUUID, Arrays.toString(values));
    }

    @Override
    public void onReadPhySuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int txPhy, int rxPhy, @Nullable Bundle argument) {
        callback(txPhy, rxPhy, argument);
    }

    @Override
    public void onReadPhyFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status, argument);
    }

    @Override
    public void onReadPhyTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout, argument);
    }

    @Override
    public void onSetPreferredPhySuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int txPhy, int rxPhy, int phyOptions, @Nullable Bundle argument) {
        callback(txPhy, rxPhy, phyOptions, argument);
    }

    @Override
    public void onSetPreferredPhyFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status, argument);
    }

    @Override
    public void onSetPreferredPhyTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout, argument);
    }

    @Override
    public void onReadRemoteRssiSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int rssi, @Nullable Bundle argument) {
        callback(rssi, argument);
    }

    @Override
    public void onReadRemoteRssiFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status, argument);
    }

    @Override
    public void onReadRemoteRssiTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout, argument);
    }

    @Override
    public void onBeginReliableWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @Nullable Bundle argument) {
        callback(argument);
    }

    @Override
    public void onBeginReliableWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status, argument);
    }

    @Override
    public void onExecuteReliableWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @Nullable Bundle argument) {
        callback(argument);
    }

    @Override
    public void onExecuteReliableWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status, argument);
    }

    @Override
    public void onExecuteReliableWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout, argument);
    }

    @Override
    public void onAbortReliableWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @Nullable Bundle argument) {
        callback(argument);
    }

    @Override
    public void onAbortReliableWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status, argument);
    }

    @Override
    public void onAbortReliableWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout, argument);
    }

    @Override
    public void onServerStarted() {
        callback();
    }

    @Override
    public void onServerStopped() {
        callback();
        super.onServerStopped();
    }

    @Override
    public void onDeviceConnected(@NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothDevice device) {
        callback(device);
        super.onDeviceConnected(bleServerConnection, device);
    }

    @Override
    public void onDeviceDisconnected(@NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothDevice device) {
        callback(device);
        super.onDeviceDisconnected(bleServerConnection, device);
    }

    @Override
    public boolean onServiceAddSuccess(@NonNull Integer taskId, @NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothGattService bluetoothGattService, @Nullable Bundle argument) {
        callback(bluetoothGattService.getUuid());
        return super.onServiceAddSuccess(taskId, bleServerConnection, bluetoothGattService, argument);
    }

    @Override
    public void onServiceAddFailed(@NonNull Integer taskId, @NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothGattService bluetoothGattService, int status, @Nullable Bundle argument) {
        callback(argument);
    }

    @Override
    public void onServiceAddTimeout(@NonNull Integer taskId, @NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothGattService bluetoothGattService, long timeout, @Nullable Bundle argument) {
        callback(argument);
    }

    @Override
    public void onServiceRemoveSuccess(@NonNull Integer taskId, @NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothGattService bluetoothGattService, @Nullable Bundle argument) {
        callback(argument);
        super.onServiceRemoveSuccess(taskId, bleServerConnection, bluetoothGattService, argument);
    }

    @Override
    public void onServiceRemoveFailed(@NonNull Integer taskId, @NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothGattService bluetoothGattService, int status, @Nullable Bundle argument) {
        callback(argument);
    }

    @Override
    public void onServiceRemoveTimeout(@NonNull Integer taskId, @NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothGattService bluetoothGattService, long timeout, @Nullable Bundle argument) {
        callback(argument);
    }

    @Override
    public boolean onCharacteristicReadRequest(@NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothDevice device, int requestId, int offset, @NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean force) {
        callback(device, bluetoothGattCharacteristic.getUuid());
        return super.onCharacteristicReadRequest(bleServerConnection, device, requestId, offset, bluetoothGattCharacteristic, force);
    }

    @Override
    public boolean onCharacteristicWriteRequest(@NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothDevice device, int requestId, @NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean preparedWrite, boolean responseNeeded, int offset, @NonNull byte[] value, boolean force) {
        callback(device, bluetoothGattCharacteristic.getUuid());
        return super.onCharacteristicWriteRequest(bleServerConnection, device, requestId, bluetoothGattCharacteristic, preparedWrite, responseNeeded, offset, value, force);
    }

    @Override
    public boolean onDescriptorReadRequest(@NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothDevice device, int requestId, int offset, @NonNull BluetoothGattDescriptor bluetoothGattDescriptor, boolean force) {
        callback(device, bluetoothGattDescriptor.getUuid());
        return super.onDescriptorReadRequest(bleServerConnection, device, requestId, offset, bluetoothGattDescriptor, false);
    }

    @Override
    public boolean onDescriptorWriteRequest(@NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothDevice device, int requestId, @NonNull BluetoothGattDescriptor bluetoothGattDescriptor, boolean preparedWrite, boolean responseNeeded, int offset, @NonNull byte[] value, boolean force) {
        callback(device, bluetoothGattDescriptor.getUuid());
        return super.onDescriptorWriteRequest(bleServerConnection, device, requestId, bluetoothGattDescriptor, preparedWrite, responseNeeded, offset, value, force);
    }

    @Override
    public void onNotificationSuccess(@NonNull Integer taskId, @NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothDevice device, @NonNull UUID serviceUUID, int serviceInstanceId, @NonNull UUID characteristicUUID, int characteristicInstanceId, @NonNull byte[] value, @Nullable Bundle argument) {
        super.onNotificationSuccess(taskId, bleServerConnection, device, serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, value, argument);
        callback(device, serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, Arrays.toString(value), argument);
    }

    @Override
    public void onNotificationFailed(@NonNull Integer taskId, @NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothDevice device, @NonNull UUID serviceUUID, int serviceInstanceId, @NonNull UUID characteristicUUID, int characteristicInstanceId, int status, @Nullable Bundle argument) {
        super.onNotificationFailed(taskId, bleServerConnection, device, serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, status, argument);
        callback(device, serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, status, argument);
    }

    @Override
    public void onNotificationTimeout(@NonNull Integer taskId, @NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothDevice device, @NonNull UUID serviceUUID, int serviceInstanceId, @NonNull UUID characteristicUUID, int characteristicInstanceId, long timeout, @Nullable Bundle argument) {
        super.onNotificationTimeout(taskId, bleServerConnection, device, serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, timeout, argument);
        callback(device, serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, timeout, argument);
    }

    @Override
    public boolean onExecuteWrite(@NonNull BLEServerConnection bleServerConnection, @NonNull BluetoothDevice device, int requestId, boolean execute, boolean force) {
        callback(device, execute);
        return super.onExecuteWrite(bleServerConnection, device, requestId, execute, force);
    }

    @Override
    public void onAdvertisingStartSuccess(@NonNull AdvertiseSettings advertiseSettings) {
        callback(advertiseSettings);
    }

    @Override
    public void onAdvertisingStartFailed(Integer errorCode) {
        callback(errorCode);
    }

    @Override
    public void onAdvertisingFinished() {
        callback();
    }

    @Override
    public boolean isFallback() {
        callback();
        return super.isFallback();
    }

    @Override
    public void setup(@NonNull BLEServerConnection bleServerConnection) {
        callback();
        super.setup(bleServerConnection);
    }

    @Override
    public void tearDown(@NonNull BLEServerConnection bleServerConnection) {
        callback();
        super.tearDown(bleServerConnection);
    }

    @Override
    public void onDigitalReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull DigitalAndroid digitalAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, Arrays.toString(digitalAndroid.getDigital()));
    }

    @Override
    public void onDigitalReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull DigitalAndroid digitalAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, Arrays.toString(digitalAndroid.getDigital()));
    }

    @Override
    public void onDigitalWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalWriteWithNoResponseSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull DigitalAndroid digitalAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, Arrays.toString(digitalAndroid.getDigital()));
    }

    @Override
    public void onDigitalWriteWithNoResponseFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalWriteWithNoResponseTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalClientCharacteristicConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull ClientCharacteristicConfigurationAndroid clientCharacteristicConfigurationAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, Arrays.toString(clientCharacteristicConfigurationAndroid.getProperties()));
    }

    @Override
    public void onDigitalClientCharacteristicConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalClientCharacteristicConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalNotifyStartSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId);
    }

    @Override
    public void onDigitalNotifyStartFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalNotifyStartTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalNotifyStopSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId);
    }

    @Override
    public void onDigitalNotifyStopFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalNotifyStopTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalIndicateStartSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId);
    }

    @Override
    public void onDigitalIndicateStartFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalIndicateStartTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalIndicateStopSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId);
    }

    @Override
    public void onDigitalIndicateStopFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalIndicateStopTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalCharacteristicPresentationFormatReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull CharacteristicPresentationFormatAndroid characteristicPresentationFormatAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, Arrays.toString(characteristicPresentationFormatAndroid.getDescription()));
    }

    @Override
    public void onDigitalCharacteristicPresentationFormatReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalCharacteristicPresentationFormatReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalCharacteristicUserDescriptionReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull CharacteristicUserDescriptionAndroid characteristicUserDescriptionAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, characteristicUserDescriptionAndroid.getUserDescription());
    }

    @Override
    public void onDigitalCharacteristicUserDescriptionReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalCharacteristicUserDescriptionReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalCharacteristicUserDescriptionWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull CharacteristicUserDescriptionAndroid characteristicUserDescriptionAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, characteristicUserDescriptionAndroid.getUserDescription());
    }

    @Override
    public void onDigitalCharacteristicUserDescriptionWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalCharacteristicUserDescriptionWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalCharacteristicExtendedPropertiesReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull CharacteristicExtendedPropertiesAndroid characteristicExtendedPropertiesAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, characteristicExtendedPropertiesAndroid.isPropertiesReliableWriteEnabled(), characteristicExtendedPropertiesAndroid.isPropertiesWritableAuxiliariesEnabled());
    }

    @Override
    public void onDigitalCharacteristicExtendedPropertiesReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalCharacteristicExtendedPropertiesReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalValueTriggerSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull ValueTriggerSettingAndroid valueTriggerSettingAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, valueTriggerSettingAndroid.getCondition());
    }

    @Override
    public void onDigitalValueTriggerSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalValueTriggerSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalValueTriggerSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull ValueTriggerSettingAndroid valueTriggerSettingAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, valueTriggerSettingAndroid.getCondition());
    }

    @Override
    public void onDigitalValueTriggerSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalValueTriggerSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalTimeTriggerSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull TimeTriggerSettingAndroid timeTriggerSettingAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeTriggerSettingAndroid.getCondition());
    }

    @Override
    public void onDigitalTimeTriggerSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalTimeTriggerSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalTimeTriggerSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull TimeTriggerSettingAndroid timeTriggerSettingAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeTriggerSettingAndroid.getCondition());
    }

    @Override
    public void onDigitalTimeTriggerSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalTimeTriggerSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onDigitalNumberOfDigitalsReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull NumberOfDigitalsAndroid numberOfDigitalsAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, numberOfDigitalsAndroid.getNoOfDigitals());
    }

    @Override
    public void onDigitalNumberOfDigitalsReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onDigitalNumberOfDigitalsReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull AnalogAndroid analogAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, BLEUtils.createUInt16(analogAndroid.getAnalog(), 0));
    }

    @Override
    public void onAnalogReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull AnalogAndroid analogAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, BLEUtils.createUInt16(analogAndroid.getAnalog(), 0));
    }

    @Override
    public void onAnalogWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogWriteWithNoResponseSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull AnalogAndroid analogAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, BLEUtils.createUInt16(analogAndroid.getAnalog(), 0));
    }

    @Override
    public void onAnalogWriteWithNoResponseFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogWriteWithNoResponseTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogClientCharacteristicConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull ClientCharacteristicConfigurationAndroid clientCharacteristicConfigurationAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, Arrays.toString(clientCharacteristicConfigurationAndroid.getProperties()));
    }

    @Override
    public void onAnalogClientCharacteristicConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogClientCharacteristicConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogNotifyStartSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId);
    }

    @Override
    public void onAnalogNotifyStartFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogNotifyStartTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogNotifyStopSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId);
    }

    @Override
    public void onAnalogNotifyStopFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogNotifyStopTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogIndicateStartSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId);
    }

    @Override
    public void onAnalogIndicateStartFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogIndicateStartTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogIndicateStopSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId);
    }

    @Override
    public void onAnalogIndicateStopFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogIndicateStopTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogCharacteristicPresentationFormatReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull CharacteristicPresentationFormatAndroid characteristicPresentationFormatAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, BLEUtils.createUInt16(characteristicPresentationFormatAndroid.getDescription(), 0));
    }

    @Override
    public void onAnalogCharacteristicPresentationFormatReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogCharacteristicPresentationFormatReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogCharacteristicUserDescriptionReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull CharacteristicUserDescriptionAndroid characteristicUserDescriptionAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, characteristicUserDescriptionAndroid.getUserDescription());
    }

    @Override
    public void onAnalogCharacteristicUserDescriptionReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogCharacteristicUserDescriptionReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogCharacteristicUserDescriptionWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull CharacteristicUserDescriptionAndroid characteristicUserDescriptionAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, characteristicUserDescriptionAndroid.getUserDescription());
    }

    @Override
    public void onAnalogCharacteristicUserDescriptionWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogCharacteristicUserDescriptionWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogCharacteristicExtendedPropertiesReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull CharacteristicExtendedPropertiesAndroid characteristicExtendedPropertiesAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, Arrays.toString(characteristicExtendedPropertiesAndroid.getProperties()));
    }

    @Override
    public void onAnalogCharacteristicExtendedPropertiesReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogCharacteristicExtendedPropertiesReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogValueTriggerSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull ValueTriggerSettingAndroid valueTriggerSettingAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, valueTriggerSettingAndroid.getCondition());
    }

    @Override
    public void onAnalogValueTriggerSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogValueTriggerSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogValueTriggerSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull ValueTriggerSettingAndroid valueTriggerSettingAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, valueTriggerSettingAndroid.getCondition());
    }

    @Override
    public void onAnalogValueTriggerSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogValueTriggerSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogTimeTriggerSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull TimeTriggerSettingAndroid timeTriggerSettingAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeTriggerSettingAndroid.getCondition());
    }

    @Override
    public void onAnalogTimeTriggerSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogTimeTriggerSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogTimeTriggerSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull TimeTriggerSettingAndroid timeTriggerSettingAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeTriggerSettingAndroid.getCondition());
    }

    @Override
    public void onAnalogTimeTriggerSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogTimeTriggerSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAnalogValidRangeReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull ValidRangeAndroid validRangeAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, validRangeAndroid.getLowerInclusiveValueUint16(), validRangeAndroid.getUpperInclusiveValueUint16());
    }

    @Override
    public void onAnalogValidRangeReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, status);
    }

    @Override
    public void onAnalogValidRangeReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Integer index, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, timeout);
    }

    @Override
    public void onAggregateReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @NonNull AggregateAndroid aggregateAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, Arrays.toString(aggregateAndroid.getBytes()));
    }

    @Override
    public void onAggregateReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, status);
    }

    @Override
    public void onAggregateReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, timeout);
    }

    @Override
    public void onAggregateClientCharacteristicConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @NonNull ClientCharacteristicConfigurationAndroid clientCharacteristicConfigurationAndroid, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, Arrays.toString(clientCharacteristicConfigurationAndroid.getProperties()));
    }

    @Override
    public void onAggregateClientCharacteristicConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, status);
    }

    @Override
    public void onAggregateClientCharacteristicConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, timeout);
    }

    @Override
    public void onAggregateNotifyStartSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId);
    }

    @Override
    public void onAggregateNotifyStartFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, status);
    }

    @Override
    public void onAggregateNotifyStartTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, timeout);
    }

    @Override
    public void onAggregateNotifyStopSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId);
    }

    @Override
    public void onAggregateNotifyStopFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, status);
    }

    @Override
    public void onAggregateNotifyStopTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, timeout);
    }

    @Override
    public void onAggregateIndicateStartSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId);
    }

    @Override
    public void onAggregateIndicateStartFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, status);
    }

    @Override
    public void onAggregateIndicateStartTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, timeout);
    }

    @Override
    public void onAggregateIndicateStopSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId);
    }

    @Override
    public void onAggregateIndicateStopFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, int status, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, status);
    }

    @Override
    public void onAggregateIndicateStopTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @Nullable Integer serviceInstanceId, @NonNull UUID characteristicUUID, @Nullable Integer characteristicInstanceId, long timeout, @Nullable Bundle argument) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, timeout);
    }

    @Override
    public void onDigitalNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull DigitalAndroid digitalAndroid) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, Arrays.toString(digitalAndroid.getDigital()));
    }

    @Override
    public void onDigitalIndicated(@NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull DigitalAndroid digitalAndroid) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, Arrays.toString(digitalAndroid.getDigital()));
    }

    @Override
    public void onAnalogNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull AnalogAndroid analogAndroid) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, BLEUtils.createUInt16(analogAndroid.getAnalog(), 0));
    }

    @Override
    public void onAnalogIndicated(@NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @Nullable Integer index, @NonNull AnalogAndroid analogAndroid) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, index, BLEUtils.createUInt16(analogAndroid.getAnalog(), 0));
    }

    @Override
    public void onAggregateNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @NonNull AggregateAndroid aggregateAndroid) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, Arrays.toString(aggregateAndroid.getBytes()));
    }

    @Override
    public void onAggregateIndicated(@NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull Integer serviceInstanceId, @NonNull UUID characteristicUUID, @NonNull Integer characteristicInstanceId, @NonNull AggregateAndroid aggregateAndroid) {
        callback(serviceUUID, serviceInstanceId, characteristicUUID, characteristicInstanceId, Arrays.toString(aggregateAndroid.getBytes()));
    }

}

package org.im97mori.ble.service.ias.peripheral;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.BLEServerCallback;
import org.im97mori.ble.characteristic.u2a06.AlertLevel;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.im97mori.ble.BLEConstants.CharacteristicUUID.ALERT_LEVEL_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.ServiceUUID.IMMEDIATE_ALERT_SERVICE;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ImmediateAlertServiceMockCallbackBuilderTest {

    @Test
    public void test_addAlertLevel_00001() {
        Exception exception = null;
        try {
            new ImmediateAlertServiceMockCallback.Builder<>().build();
        } catch (RuntimeException e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals("no Alert Level data", exception.getMessage());
    }

    @Test
    public void test_addAlertLevel_00002() {
        AlertLevel alertLevel = new AlertLevel(AlertLevel.ALERT_LEVEL_HIGH_ALERT);
        final AtomicReference<BluetoothGattService> bluetoothGattServiceAtomicReference = new AtomicReference<>();
        MockBLEServerConnection mockBLEServerConnection = new MockBLEServerConnection() {
            @Override
            public synchronized Integer createAddServiceTask(@NonNull BluetoothGattService bluetoothGattService, long timeout, @Nullable Bundle argument, @Nullable BLEServerCallback bleServerCallback) {
                bluetoothGattServiceAtomicReference.set(bluetoothGattService);
                return null;
            }
        };
        ImmediateAlertServiceMockCallback callback = new ImmediateAlertServiceMockCallback.Builder<>().addAlertLevel(alertLevel.getAlertLevel()).build();
        callback.setup(mockBLEServerConnection);
        BluetoothGattService bluetoothGattService = bluetoothGattServiceAtomicReference.get();

        assertEquals(IMMEDIATE_ALERT_SERVICE, bluetoothGattService.getUuid());
        BluetoothGattCharacteristic bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(ALERT_LEVEL_CHARACTERISTIC);
        assertNotNull(bluetoothGattCharacteristic);
        assertArrayEquals(bluetoothGattCharacteristic.getValue(), alertLevel.getBytes());
    }

    @Test
    public void test_addAlertLevel_00003() {
        AlertLevel alertLevel = new AlertLevel(AlertLevel.ALERT_LEVEL_HIGH_ALERT);
        final AtomicReference<BluetoothGattService> bluetoothGattServiceAtomicReference = new AtomicReference<>();
        MockBLEServerConnection mockBLEServerConnection = new MockBLEServerConnection() {
            @Override
            public synchronized Integer createAddServiceTask(@NonNull BluetoothGattService bluetoothGattService, long timeout, @Nullable Bundle argument, @Nullable BLEServerCallback bleServerCallback) {
                bluetoothGattServiceAtomicReference.set(bluetoothGattService);
                return null;
            }
        };
        ImmediateAlertServiceMockCallback.Builder<ImmediateAlertServiceMockCallback> builder = new ImmediateAlertServiceMockCallback.Builder<>();
        builder.addAlertLevel(alertLevel);
        ImmediateAlertServiceMockCallback callback = builder.build();
        callback.setup(mockBLEServerConnection);
        BluetoothGattService bluetoothGattService = bluetoothGattServiceAtomicReference.get();

        assertEquals(IMMEDIATE_ALERT_SERVICE, bluetoothGattService.getUuid());
        BluetoothGattCharacteristic bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(ALERT_LEVEL_CHARACTERISTIC);
        assertNotNull(bluetoothGattCharacteristic);
        assertArrayEquals(bluetoothGattCharacteristic.getValue(), alertLevel.getBytes());
    }

    @Test
    public void test_addAlertLevel_00004() {
        AlertLevel alertLevel = new AlertLevel(AlertLevel.ALERT_LEVEL_HIGH_ALERT);
        final AtomicReference<BluetoothGattService> bluetoothGattServiceAtomicReference = new AtomicReference<>();
        MockBLEServerConnection mockBLEServerConnection = new MockBLEServerConnection() {
            @Override
            public synchronized Integer createAddServiceTask(@NonNull BluetoothGattService bluetoothGattService, long timeout, @Nullable Bundle argument, @Nullable BLEServerCallback bleServerCallback) {
                bluetoothGattServiceAtomicReference.set(bluetoothGattService);
                return null;
            }
        };
        ImmediateAlertServiceMockCallback.Builder<ImmediateAlertServiceMockCallback> builder = new ImmediateAlertServiceMockCallback.Builder<>();
        builder.addAlertLevel(alertLevel.getBytes());
        ImmediateAlertServiceMockCallback callback = builder.build();
        callback.setup(mockBLEServerConnection);
        BluetoothGattService bluetoothGattService = bluetoothGattServiceAtomicReference.get();

        assertEquals(IMMEDIATE_ALERT_SERVICE, bluetoothGattService.getUuid());
        BluetoothGattCharacteristic bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(ALERT_LEVEL_CHARACTERISTIC);
        assertNotNull(bluetoothGattCharacteristic);
        assertArrayEquals(bluetoothGattCharacteristic.getValue(), alertLevel.getBytes());
    }


    @Test
    public void test_addAlertLevel_00005() {
        AlertLevel alertLevel = new AlertLevel(AlertLevel.ALERT_LEVEL_HIGH_ALERT);
        final AtomicReference<BluetoothGattService> bluetoothGattServiceAtomicReference = new AtomicReference<>();
        MockBLEServerConnection mockBLEServerConnection = new MockBLEServerConnection() {
            @Override
            public synchronized Integer createAddServiceTask(@NonNull BluetoothGattService bluetoothGattService, long timeout, @Nullable Bundle argument, @Nullable BLEServerCallback bleServerCallback) {
                bluetoothGattServiceAtomicReference.set(bluetoothGattService);
                return null;
            }
        };
        ImmediateAlertServiceMockCallback.Builder<ImmediateAlertServiceMockCallback> builder = new ImmediateAlertServiceMockCallback.Builder<>();
        builder.addAlertLevel(0, 0, alertLevel.getBytes());
        ImmediateAlertServiceMockCallback callback = builder.build();
        callback.setup(mockBLEServerConnection);
        BluetoothGattService bluetoothGattService = bluetoothGattServiceAtomicReference.get();

        assertEquals(IMMEDIATE_ALERT_SERVICE, bluetoothGattService.getUuid());
        BluetoothGattCharacteristic bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(ALERT_LEVEL_CHARACTERISTIC);
        assertNotNull(bluetoothGattCharacteristic);
        assertArrayEquals(bluetoothGattCharacteristic.getValue(), alertLevel.getBytes());
    }

    @Test
    public void test_removeAlertLevel_00001() {
        AlertLevel alertLevel = new AlertLevel(AlertLevel.ALERT_LEVEL_HIGH_ALERT);
        Exception exception = null;
        try {
            new ImmediateAlertServiceMockCallback.Builder<>()
                    .addAlertLevel(alertLevel)
                    .removeAlertLevel()
                    .build();
        } catch (RuntimeException e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals("no Alert Level data", exception.getMessage());
    }

}

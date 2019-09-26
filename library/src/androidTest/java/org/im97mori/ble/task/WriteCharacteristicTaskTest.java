package org.im97mori.ble.task;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Bundle;
import android.os.Message;

import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WriteCharacteristicTaskTest {

    @Test
    public void test_createInitialMessage001() {
        UUID serviceUUID = UUID.randomUUID();
        UUID characteristicUUID = UUID.randomUUID();
        WriteCharacteristicTask task = new WriteCharacteristicTask(null, null, null, serviceUUID, characteristicUUID, null, BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT, WriteCharacteristicTask.TIMEOUT_MILLIS, null);
        Message message = task.createInitialMessage();

        assertNotNull(message);
        Bundle bundle = message.getData();
        assertNotNull(bundle);
        assertTrue(bundle.containsKey(AbstractBLETask.KEY_SERVICE_UUID));
        assertEquals(serviceUUID, bundle.getSerializable(AbstractBLETask.KEY_SERVICE_UUID));
        assertTrue(bundle.containsKey(AbstractBLETask.KEY_CHARACTERISTIC_UUID));
        assertEquals(characteristicUUID, bundle.getSerializable(AbstractBLETask.KEY_CHARACTERISTIC_UUID));
        assertTrue(bundle.containsKey(AbstractBLETask.KEY_NEXT_PROGRESS));
        assertEquals(AbstractBLETask.PROGRESS_CHARACTERISTIC_WRITE_START, bundle.getInt(AbstractBLETask.KEY_NEXT_PROGRESS));
        assertEquals(task, message.obj);
    }

    @Test
    public void test_createWriteCharacteristicFinishedMessage001() {
        UUID serviceUUID = UUID.randomUUID();
        UUID characteristicUUID = UUID.randomUUID();
        byte[] original = new byte[0];
        Message message = WriteCharacteristicTask.createWriteCharacteristicFinishedMessage(serviceUUID, characteristicUUID, original);

        assertNotNull(message);
        Bundle bundle = message.getData();
        assertNotNull(bundle);
        assertTrue(bundle.containsKey(AbstractBLETask.KEY_SERVICE_UUID));
        assertEquals(serviceUUID, bundle.getSerializable(AbstractBLETask.KEY_SERVICE_UUID));
        assertTrue(bundle.containsKey(AbstractBLETask.KEY_CHARACTERISTIC_UUID));
        assertEquals(characteristicUUID, bundle.getSerializable(AbstractBLETask.KEY_CHARACTERISTIC_UUID));
        assertTrue(bundle.containsKey(AbstractBLETask.KEY_VALUES));
        assertArrayEquals(original, bundle.getByteArray(AbstractBLETask.KEY_VALUES));
        assertTrue(bundle.containsKey(AbstractBLETask.KEY_NEXT_PROGRESS));
        assertEquals(AbstractBLETask.PROGRESS_CHARACTERISTIC_WRITE_SUCCESS, bundle.getInt(AbstractBLETask.KEY_NEXT_PROGRESS));
    }

    @Test
    public void test_createWriteCharacteristicErrorMessage001() {
        UUID serviceUUID = UUID.randomUUID();
        UUID characteristicUUID = UUID.randomUUID();
        int status = new Random().nextInt();
        Message message = WriteCharacteristicTask.createWriteCharacteristicErrorMessage(serviceUUID, characteristicUUID, status);

        assertNotNull(message);
        Bundle bundle = message.getData();
        assertNotNull(bundle);
        assertTrue(bundle.containsKey(AbstractBLETask.KEY_SERVICE_UUID));
        assertEquals(serviceUUID, bundle.getSerializable(AbstractBLETask.KEY_SERVICE_UUID));
        assertTrue(bundle.containsKey(AbstractBLETask.KEY_CHARACTERISTIC_UUID));
        assertEquals(characteristicUUID, bundle.getSerializable(AbstractBLETask.KEY_CHARACTERISTIC_UUID));
        assertTrue(bundle.containsKey(AbstractBLETask.KEY_STATUS));
        assertEquals(status, bundle.getInt(AbstractBLETask.KEY_STATUS));
        assertTrue(bundle.containsKey(AbstractBLETask.KEY_NEXT_PROGRESS));
        assertEquals(AbstractBLETask.PROGRESS_CHARACTERISTIC_WRITE_ERROR, bundle.getInt(AbstractBLETask.KEY_NEXT_PROGRESS));
    }

}

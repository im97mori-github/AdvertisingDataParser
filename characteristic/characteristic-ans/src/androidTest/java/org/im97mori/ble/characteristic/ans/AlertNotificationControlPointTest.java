package org.im97mori.ble.characteristic.ans;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AlertNotificationControlPointTest {

    @Test
    public void test_constructor001() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_ENABLE_NEW_IMCOMING_ALERT_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_SIMPLE_ALERT;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertEquals(AlertNotificationControlPoint.COMMAND_ID_ENABLE_NEW_IMCOMING_ALERT_NOTIFICATION, result1.getCommandId());
        assertTrue(result1.isCommandIdEnableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdEnableUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdDisableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdDisableeUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdNotifyNewIncomingAlertImmediately());
        assertFalse(result1.isCommandIdNotifyUnreadCategoryStatusImmediately());
        assertEquals(AlertCategoryIdUtils.CATEGORY_ID_SIMPLE_ALERT, result1.getCategoryId());
    }

    @Test
    public void test_constructor002() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_ENABLE_UNREAD_CATEGORY_STATUS_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_EMAIL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertEquals(AlertNotificationControlPoint.COMMAND_ID_ENABLE_UNREAD_CATEGORY_STATUS_NOTIFICATION, result1.getCommandId());
        assertFalse(result1.isCommandIdEnableNewIncomingAlertNotification());
        assertTrue(result1.isCommandIdEnableUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdDisableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdDisableeUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdNotifyNewIncomingAlertImmediately());
        assertFalse(result1.isCommandIdNotifyUnreadCategoryStatusImmediately());
        assertEquals(AlertCategoryIdUtils.CATEGORY_ID_EMAIL, result1.getCategoryId());
    }

    @Test
    public void test_constructor003() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_DISABLE_NEW_IMCOMING_ALERT_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_NEWS;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertEquals(AlertNotificationControlPoint.COMMAND_ID_DISABLE_NEW_IMCOMING_ALERT_NOTIFICATION, result1.getCommandId());
        assertFalse(result1.isCommandIdEnableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdEnableUnreadCategoryStatusNotification());
        assertTrue(result1.isCommandIdDisableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdDisableeUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdNotifyNewIncomingAlertImmediately());
        assertFalse(result1.isCommandIdNotifyUnreadCategoryStatusImmediately());
        assertEquals(AlertCategoryIdUtils.CATEGORY_ID_NEWS, result1.getCategoryId());
    }

    @Test
    public void test_constructor004() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_DISABLE_UNREAD_CATEGORY_STATUS_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_CALL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertEquals(AlertNotificationControlPoint.COMMAND_ID_DISABLE_UNREAD_CATEGORY_STATUS_NOTIFICATION, result1.getCommandId());
        assertFalse(result1.isCommandIdEnableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdEnableUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdDisableNewIncomingAlertNotification());
        assertTrue(result1.isCommandIdDisableeUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdNotifyNewIncomingAlertImmediately());
        assertFalse(result1.isCommandIdNotifyUnreadCategoryStatusImmediately());
        assertEquals(AlertCategoryIdUtils.CATEGORY_ID_CALL, result1.getCategoryId());
    }

    @Test
    public void test_constructor005() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_NEW_INCOMING_ALERT_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_MISSED_CALL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertEquals(AlertNotificationControlPoint.COMMAND_ID_NOTIFY_NEW_INCOMING_ALERT_IMMEDIATELY, result1.getCommandId());
        assertFalse(result1.isCommandIdEnableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdEnableUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdDisableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdDisableeUnreadCategoryStatusNotification());
        assertTrue(result1.isCommandIdNotifyNewIncomingAlertImmediately());
        assertFalse(result1.isCommandIdNotifyUnreadCategoryStatusImmediately());
        assertEquals(AlertCategoryIdUtils.CATEGORY_ID_MISSED_CALL, result1.getCategoryId());
    }

    @Test
    public void test_constructor006() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_SMS_MMS;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertEquals(AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY, result1.getCommandId());
        assertFalse(result1.isCommandIdEnableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdEnableUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdDisableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdDisableeUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdNotifyNewIncomingAlertImmediately());
        assertTrue(result1.isCommandIdNotifyUnreadCategoryStatusImmediately());
        assertEquals(AlertCategoryIdUtils.CATEGORY_ID_SMS_MMS, result1.getCategoryId());
    }

    @Test
    public void test_constructor007() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_VOICE_MAIL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertEquals(AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY, result1.getCommandId());
        assertFalse(result1.isCommandIdEnableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdEnableUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdDisableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdDisableeUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdNotifyNewIncomingAlertImmediately());
        assertTrue(result1.isCommandIdNotifyUnreadCategoryStatusImmediately());
        assertEquals(AlertCategoryIdUtils.CATEGORY_ID_VOICE_MAIL, result1.getCategoryId());
    }

    @Test
    public void test_constructor008() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_SCHEDULE;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertEquals(AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY, result1.getCommandId());
        assertFalse(result1.isCommandIdEnableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdEnableUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdDisableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdDisableeUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdNotifyNewIncomingAlertImmediately());
        assertTrue(result1.isCommandIdNotifyUnreadCategoryStatusImmediately());
        assertEquals(AlertCategoryIdUtils.CATEGORY_ID_SCHEDULE, result1.getCategoryId());
    }

    @Test
    public void test_constructor009() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_HIGH_PRIORITIZED_ALERT;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertEquals(AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY, result1.getCommandId());
        assertFalse(result1.isCommandIdEnableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdEnableUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdDisableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdDisableeUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdNotifyNewIncomingAlertImmediately());
        assertTrue(result1.isCommandIdNotifyUnreadCategoryStatusImmediately());
        assertEquals(AlertCategoryIdUtils.CATEGORY_ID_HIGH_PRIORITIZED_ALERT, result1.getCategoryId());
    }

    @Test
    public void test_constructor010() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_INSTANT_MESSAGE;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertEquals(AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY, result1.getCommandId());
        assertFalse(result1.isCommandIdEnableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdEnableUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdDisableNewIncomingAlertNotification());
        assertFalse(result1.isCommandIdDisableeUnreadCategoryStatusNotification());
        assertFalse(result1.isCommandIdNotifyNewIncomingAlertImmediately());
        assertTrue(result1.isCommandIdNotifyUnreadCategoryStatusImmediately());
        assertEquals(AlertCategoryIdUtils.CATEGORY_ID_INSTANT_MESSAGE, result1.getCategoryId());
    }

    @Test
    public void test_parcelable001() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_ENABLE_NEW_IMCOMING_ALERT_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_SIMPLE_ALERT;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getCommandId(), result2.getCommandId());
        assertEquals(result1.getCategoryId(), result2.getCategoryId());
    }

    @Test
    public void test_parcelable002() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_ENABLE_UNREAD_CATEGORY_STATUS_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_EMAIL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getCommandId(), result2.getCommandId());
        assertEquals(result1.getCategoryId(), result2.getCategoryId());
    }

    @Test
    public void test_parcelable003() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_DISABLE_NEW_IMCOMING_ALERT_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_NEWS;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getCommandId(), result2.getCommandId());
        assertEquals(result1.getCategoryId(), result2.getCategoryId());
    }

    @Test
    public void test_parcelable004() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_DISABLE_UNREAD_CATEGORY_STATUS_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_CALL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getCommandId(), result2.getCommandId());
        assertEquals(result1.getCategoryId(), result2.getCategoryId());
    }

    @Test
    public void test_parcelable005() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_NEW_INCOMING_ALERT_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_MISSED_CALL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getCommandId(), result2.getCommandId());
        assertEquals(result1.getCategoryId(), result2.getCategoryId());
    }

    @Test
    public void test_parcelable006() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_SMS_MMS;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getCommandId(), result2.getCommandId());
        assertEquals(result1.getCategoryId(), result2.getCategoryId());
    }

    @Test
    public void test_parcelable007() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_VOICE_MAIL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getCommandId(), result2.getCommandId());
        assertEquals(result1.getCategoryId(), result2.getCategoryId());
    }

    @Test
    public void test_parcelable008() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_SCHEDULE;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getCommandId(), result2.getCommandId());
        assertEquals(result1.getCategoryId(), result2.getCategoryId());
    }

    @Test
    public void test_parcelable009() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_HIGH_PRIORITIZED_ALERT;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getCommandId(), result2.getCommandId());
        assertEquals(result1.getCategoryId(), result2.getCategoryId());
    }

    @Test
    public void test_parcelable010() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_ENABLE_UNREAD_CATEGORY_STATUS_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_EMAIL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromParcel(parcel);
        assertEquals(result1.getCommandId(), result2.getCommandId());
        assertEquals(result1.getCategoryId(), result2.getCategoryId());
    }

    @Test
    public void test_parcelable101() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_ENABLE_NEW_IMCOMING_ALERT_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_SIMPLE_ALERT;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable102() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_ENABLE_UNREAD_CATEGORY_STATUS_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_EMAIL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable103() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_DISABLE_NEW_IMCOMING_ALERT_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_NEWS;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable104() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_DISABLE_UNREAD_CATEGORY_STATUS_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_CALL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable105() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_NEW_INCOMING_ALERT_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_MISSED_CALL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable106() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_SMS_MMS;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable107() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_VOICE_MAIL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable108() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_SCHEDULE;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable109() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_HIGH_PRIORITIZED_ALERT;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable110() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_ENABLE_UNREAD_CATEGORY_STATUS_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_EMAIL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable201() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_ENABLE_NEW_IMCOMING_ALERT_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_SIMPLE_ALERT;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable202() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_ENABLE_UNREAD_CATEGORY_STATUS_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_EMAIL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable203() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_DISABLE_NEW_IMCOMING_ALERT_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_NEWS;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable204() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_DISABLE_UNREAD_CATEGORY_STATUS_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_CALL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable205() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_NEW_INCOMING_ALERT_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_MISSED_CALL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable206() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_SMS_MMS;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable207() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_VOICE_MAIL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable208() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_SCHEDULE;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable209() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_NOTIFY_UNREAD_CATEGORY_STATUS_IMMEDIATELY;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_HIGH_PRIORITIZED_ALERT;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable210() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = AlertNotificationControlPoint.COMMAND_ID_ENABLE_UNREAD_CATEGORY_STATUS_NOTIFICATION;
        data[ 1] = AlertCategoryIdUtils.CATEGORY_ID_EMAIL;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AlertNotificationControlPoint result1 = new AlertNotificationControlPoint(bluetoothGattCharacteristic);
        AlertNotificationControlPoint result2 = AlertNotificationControlPoint.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

}

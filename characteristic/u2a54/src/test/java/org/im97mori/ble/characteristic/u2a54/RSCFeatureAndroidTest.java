package org.im97mori.ble.characteristic.u2a54;

import android.os.Build;
import android.os.Parcel;

import org.im97mori.ble.test.TestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SuppressWarnings({"unused", "ConstantConditions"})
@RunWith(RobolectricTestRunner.class)
@Config(instrumentedPackages = {
        // required to access final members on androidx.loader.content.ModernAsyncTask
        "androidx.loader.content"}
        , sdk = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class RSCFeatureAndroidTest extends TestBase {

    //@formatter:off
    private static final byte[] data_00001;
    static {
        byte[] data = new byte[2];
        int flag = RSCFeature.RSC_FEATURE_INSTANTANEOUS_STRIDE_LENGTH_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_TOTAL_DISTANCE_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_WALKING_OR_RUNNING_STATUS_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_CALIBRATION_PROCEDURE_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_MULTIPLE_SENSOR_LOCATIONS_SUPPORTED_FALSE;
        data[ 0] = (byte) flag;
        data[ 1] = (byte) (flag >> 8);
        data_00001 = data;
    }

    private static final byte[] data_00002;
    static {
        byte[] data = new byte[2];
        int flag = RSCFeature.RSC_FEATURE_INSTANTANEOUS_STRIDE_LENGTH_MEASUREMENT_SUPPORTED_TRUE
                | RSCFeature.RSC_FEATURE_TOTAL_DISTANCE_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_WALKING_OR_RUNNING_STATUS_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_CALIBRATION_PROCEDURE_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_MULTIPLE_SENSOR_LOCATIONS_SUPPORTED_FALSE;
        data[ 0] = (byte) flag;
        data[ 1] = (byte) (flag >> 8);
        data_00002 = data;
    }

    private static final byte[] data_00101;
    static {
        byte[] data = new byte[2];
        int flag = RSCFeature.RSC_FEATURE_INSTANTANEOUS_STRIDE_LENGTH_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_TOTAL_DISTANCE_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_WALKING_OR_RUNNING_STATUS_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_CALIBRATION_PROCEDURE_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_MULTIPLE_SENSOR_LOCATIONS_SUPPORTED_FALSE;
        data[ 0] = (byte) flag;
        data[ 1] = (byte) (flag >> 8);
        data_00101 = data;
    }

    private static final byte[] data_00102;
    static {
        byte[] data = new byte[2];
        int flag = RSCFeature.RSC_FEATURE_INSTANTANEOUS_STRIDE_LENGTH_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_TOTAL_DISTANCE_MEASUREMENT_SUPPORTED_TRUE
                | RSCFeature.RSC_FEATURE_WALKING_OR_RUNNING_STATUS_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_CALIBRATION_PROCEDURE_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_MULTIPLE_SENSOR_LOCATIONS_SUPPORTED_FALSE;
        data[ 0] = (byte) flag;
        data[ 1] = (byte) (flag >> 8);
        data_00102 = data;
    }

    private static final byte[] data_00201;
    static {
        byte[] data = new byte[2];
        int flag = RSCFeature.RSC_FEATURE_INSTANTANEOUS_STRIDE_LENGTH_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_TOTAL_DISTANCE_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_WALKING_OR_RUNNING_STATUS_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_CALIBRATION_PROCEDURE_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_MULTIPLE_SENSOR_LOCATIONS_SUPPORTED_FALSE;
        data[ 0] = (byte) flag;
        data[ 1] = (byte) (flag >> 8);
        data_00201 = data;
    }

    private static final byte[] data_00202;
    static {
        byte[] data = new byte[2];
        int flag = RSCFeature.RSC_FEATURE_INSTANTANEOUS_STRIDE_LENGTH_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_TOTAL_DISTANCE_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_WALKING_OR_RUNNING_STATUS_SUPPORTED_TRUE
                | RSCFeature.RSC_FEATURE_CALIBRATION_PROCEDURE_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_MULTIPLE_SENSOR_LOCATIONS_SUPPORTED_FALSE;
        data[ 0] = (byte) flag;
        data[ 1] = (byte) (flag >> 8);
        data_00202 = data;
    }

    private static final byte[] data_00301;
    static {
        byte[] data = new byte[2];
        int flag = RSCFeature.RSC_FEATURE_INSTANTANEOUS_STRIDE_LENGTH_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_TOTAL_DISTANCE_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_WALKING_OR_RUNNING_STATUS_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_CALIBRATION_PROCEDURE_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_MULTIPLE_SENSOR_LOCATIONS_SUPPORTED_FALSE;
        data[ 0] = (byte) flag;
        data[ 1] = (byte) (flag >> 8);
        data_00301 = data;
    }

    private static final byte[] data_00302;
    static {
        byte[] data = new byte[2];
        int flag = RSCFeature.RSC_FEATURE_INSTANTANEOUS_STRIDE_LENGTH_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_TOTAL_DISTANCE_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_WALKING_OR_RUNNING_STATUS_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_CALIBRATION_PROCEDURE_SUPPORTED_TRUE
                | RSCFeature.RSC_FEATURE_MULTIPLE_SENSOR_LOCATIONS_SUPPORTED_FALSE;
        data[ 0] = (byte) flag;
        data[ 1] = (byte) (flag >> 8);
        data_00302 = data;
    }

    private static final byte[] data_00401;
    static {
        byte[] data = new byte[2];
        int flag = RSCFeature.RSC_FEATURE_INSTANTANEOUS_STRIDE_LENGTH_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_TOTAL_DISTANCE_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_WALKING_OR_RUNNING_STATUS_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_CALIBRATION_PROCEDURE_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_MULTIPLE_SENSOR_LOCATIONS_SUPPORTED_FALSE;
        data[ 0] = (byte) flag;
        data[ 1] = (byte) (flag >> 8);
        data_00401 = data;
    }

    private static final byte[] data_00402;
    static {
        byte[] data = new byte[2];
        int flag = RSCFeature.RSC_FEATURE_INSTANTANEOUS_STRIDE_LENGTH_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_TOTAL_DISTANCE_MEASUREMENT_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_WALKING_OR_RUNNING_STATUS_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_CALIBRATION_PROCEDURE_SUPPORTED_FALSE
                | RSCFeature.RSC_FEATURE_MULTIPLE_SENSOR_LOCATIONS_SUPPORTED_TRUE;
        data[ 0] = (byte) flag;
        data[ 1] = (byte) (flag >> 8);
        data_00402 = data;
    }
    //@formatter:on

    @Test
    public void test_constructor_00001() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getRscFeature());
        assertTrue(result1.isRscFeatureInstantaneousStrideLengthMeasurementNotSupported());
        assertFalse(result1.isRscFeatureInstantaneousStrideLengthMeasurementSupported());
    }

    @Test
    public void test_constructor_00002() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getRscFeature());
        assertFalse(result1.isRscFeatureInstantaneousStrideLengthMeasurementNotSupported());
        assertTrue(result1.isRscFeatureInstantaneousStrideLengthMeasurementSupported());
    }

    @Test
    public void test_constructor_00101() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getRscFeature());
        assertTrue(result1.isRscFeatureTotalDistanceMeasurementNotSupported());
        assertFalse(result1.isRscFeatureTotalDistanceMeasurementSupported());
    }

    @Test
    public void test_constructor_00102() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getRscFeature());
        assertFalse(result1.isRscFeatureTotalDistanceMeasurementNotSupported());
        assertTrue(result1.isRscFeatureTotalDistanceMeasurementSupported());
    }

    @Test
    public void test_constructor_00201() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getRscFeature());
        assertTrue(result1.isRscFeatureWalkingOrRunningStatusNotSupported());
        assertFalse(result1.isRscFeatureWalkingOrRunningStatusSupported());
    }

    @Test
    public void test_constructor_00202() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getRscFeature());
        assertFalse(result1.isRscFeatureWalkingOrRunningStatusNotSupported());
        assertTrue(result1.isRscFeatureWalkingOrRunningStatusSupported());
    }

    @Test
    public void test_constructor_00301() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getRscFeature());
        assertTrue(result1.isRscFeatureCalibrationProcedureNotSupported());
        assertFalse(result1.isRscFeatureCalibrationProcedureSupported());
    }

    @Test
    public void test_constructor_00302() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getRscFeature());
        assertFalse(result1.isRscFeatureCalibrationProcedureNotSupported());
        assertTrue(result1.isRscFeatureCalibrationProcedureSupported());
    }

    @Test
    public void test_constructor_00401() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getRscFeature());
        assertTrue(result1.isRscFeatureMultipleSensorLocationsNotSupported());
        assertFalse(result1.isRscFeatureMultipleSensorLocationsSupported());
    }

    @Test
    public void test_constructor_00402() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getRscFeature());
        assertFalse(result1.isRscFeatureMultipleSensorLocationsNotSupported());
        assertTrue(result1.isRscFeatureMultipleSensorLocationsSupported());
    }

    @Test
    public void test_parcelable_1_00001() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromParcel(parcel);
        assertArrayEquals(result1.getRscFeature(), result2.getRscFeature());
    }

    @Test
    public void test_parcelable_1_00002() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromParcel(parcel);
        assertArrayEquals(result1.getRscFeature(), result2.getRscFeature());
    }

    @Test
    public void test_parcelable_1_00101() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromParcel(parcel);
        assertArrayEquals(result1.getRscFeature(), result2.getRscFeature());
    }

    @Test
    public void test_parcelable_1_00102() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromParcel(parcel);
        assertArrayEquals(result1.getRscFeature(), result2.getRscFeature());
    }

    @Test
    public void test_parcelable_1_00201() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromParcel(parcel);
        assertArrayEquals(result1.getRscFeature(), result2.getRscFeature());
    }

    @Test
    public void test_parcelable_1_00202() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromParcel(parcel);
        assertArrayEquals(result1.getRscFeature(), result2.getRscFeature());
    }

    @Test
    public void test_parcelable_1_00301() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromParcel(parcel);
        assertArrayEquals(result1.getRscFeature(), result2.getRscFeature());
    }

    @Test
    public void test_parcelable_1_00302() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromParcel(parcel);
        assertArrayEquals(result1.getRscFeature(), result2.getRscFeature());
    }

    @Test
    public void test_parcelable_1_00401() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromParcel(parcel);
        assertArrayEquals(result1.getRscFeature(), result2.getRscFeature());
    }

    @Test
    public void test_parcelable_1_00402() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromParcel(parcel);
        assertArrayEquals(result1.getRscFeature(), result2.getRscFeature());
    }

    @Test
    public void test_parcelable_2_00001() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_2_00002() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_2_00101() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_2_00102() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_2_00201() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_2_00202() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_2_00301() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_2_00302() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_2_00401() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_2_00402() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test_parcelable_3_00001() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable_3_00002() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable_3_00101() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable_3_00102() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable_3_00201() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable_3_00202() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable_3_00301() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable_3_00302() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable_3_00401() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test_parcelable_3_00402() {
        byte[] data = getData();

        RSCFeatureAndroid result1 = new RSCFeatureAndroid(data);
        RSCFeatureAndroid result2 = RSCFeatureAndroid.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

}

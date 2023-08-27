package org.im97mori.ble.advertising.filter;

import static org.im97mori.ble.constants.DataType.ADVERTISING_INTERVAL_LONG_DATA_TYPE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.os.Build;

import org.im97mori.ble.advertising.AdvertisingDataParser;
import org.im97mori.ble.advertising.AdvertisingIntervalLongAndroid;
import org.im97mori.ble.test.TestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;

@RunWith(RobolectricTestRunner.class)
@Config(instrumentedPackages = {
        // required to access final members on androidx.loader.content.ModernAsyncTask
        "androidx.loader.content"}
        , sdk = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class AdvertisingIntervalLongFilterTest extends TestBase {

    @Test
    public void test_001() {
        byte[] actualData = new byte[0];

        AdvertisingDataParser parser = new AdvertisingDataParser.Builder(true).build();
        AdvertisingDataParser.AdvertisingDataParseResult result = parser.parse(actualData);
        AdvertisingDataFilter<AdvertisingDataParser.AdvertisingDataParseResult> filter = new AdvertisingIntervalLongFilter(null);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void test_002() {
        byte[] expectData = new byte[5];
        expectData[0] = 4;
        expectData[1] = ADVERTISING_INTERVAL_LONG_DATA_TYPE;
        expectData[2] = 0;
        expectData[3] = 0;
        expectData[4] = 0;

        byte[] actualData = new byte[0];

        AdvertisingDataParser parser = new AdvertisingDataParser.Builder(true).build();
        AdvertisingDataParser.AdvertisingDataParseResult result = parser.parse(actualData);
        AdvertisingDataFilter<AdvertisingDataParser.AdvertisingDataParseResult> filter = new AdvertisingIntervalLongFilter(AdvertisingIntervalLongAndroid.CREATOR.createFromByteArray(expectData));
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void test_003() {
        byte[] actualData = new byte[5];
        actualData[0] = 4;
        actualData[1] = ADVERTISING_INTERVAL_LONG_DATA_TYPE;
        actualData[2] = 0;
        actualData[3] = 0;
        actualData[4] = 0;

        AdvertisingDataParser parser = new AdvertisingDataParser.Builder(true).build();
        AdvertisingDataParser.AdvertisingDataParseResult result = parser.parse(actualData);
        AdvertisingDataFilter<AdvertisingDataParser.AdvertisingDataParseResult> filter = new AdvertisingIntervalLongFilter(null);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void test_004() {
        byte[] expectData = new byte[5];
        expectData[0] = 4;
        expectData[1] = ADVERTISING_INTERVAL_LONG_DATA_TYPE;
        expectData[2] = 0;
        expectData[3] = 0;
        expectData[4] = 0;

        byte[] actualData = Arrays.copyOf(expectData, expectData.length);

        AdvertisingDataParser parser = new AdvertisingDataParser.Builder(true).build();
        AdvertisingDataParser.AdvertisingDataParseResult result = parser.parse(actualData);
        AdvertisingDataFilter<AdvertisingDataParser.AdvertisingDataParseResult> filter = new AdvertisingIntervalLongFilter(AdvertisingIntervalLongAndroid.CREATOR.createFromByteArray(expectData));
        assertTrue(filter.isMatched(result));
    }
}

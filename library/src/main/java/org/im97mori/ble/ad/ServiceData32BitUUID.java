package org.im97mori.ble.ad;

import android.bluetooth.le.ScanRecord;

import java.util.UUID;

import static org.im97mori.ble.ad.AdvertisingDataConstants.AdvertisingDataTypes.DATA_TYPE_SERVICE_DATA_32_BIT_UUID;
import static org.im97mori.ble.ad.AdvertisingDataConstants.BASE_UUID;

/**
 * <p>
 * Service Data - 32-bit UUID
 * <p>
 * https://www.bluetooth.com/specifications/assigned-numbers/generic-access-profile/
 * </p>
 */
public class ServiceData32BitUUID extends AbstractAdvertisingData {

    /**
     * UUID
     */
    private final UUID mUuid;

    /**
     * byte array of Additional service data
     */
    private final byte[] mAdditionalServiceData;

    /**
     * Constructor for Service Data - 32-bit UUID
     *
     * @param data   byte array from {@link ScanRecord#getBytes()}
     * @param offset data offset
     * @param length 1st octed of Advertising Data
     */
    ServiceData32BitUUID(byte[] data, int offset, int length) {
        super(length);

        long target = data[offset + 2] & 0xff;
        target |= (data[offset + 3] & 0xff) << 8;
        target |= (data[offset + 4] & 0xff) << 16;
        target |= (data[offset + 5] & 0xff) << 24;
        target = target << 32;
        mUuid = new UUID(BASE_UUID.getMostSignificantBits() | target, BASE_UUID.getLeastSignificantBits());

        // Additional service data
        mAdditionalServiceData = new byte[length - 5];
        if (mAdditionalServiceData.length > 0) {
            System.arraycopy(data, offset + 6, mAdditionalServiceData, 0, length - 5);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDataType() {
        return DATA_TYPE_SERVICE_DATA_32_BIT_UUID;
    }

    /**
     * @return {@link UUID}
     */
    public UUID getUuid() {
        return mUuid;
    }

    /**
     * @return byte array of Manufacturer Specific Data
     */
    public byte[] getAdditionalServiceData() {
        return mAdditionalServiceData;
    }

}

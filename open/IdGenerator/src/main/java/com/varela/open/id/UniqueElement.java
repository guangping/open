package com.varela.open.id;

import org.apache.commons.lang3.time.FastDateFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * UniqueElement
 *
 * @author
 * @date
 */
public class UniqueElement implements Serializable {

    private long timestamp;

    private Date timestampDate;

    private long bizId;

    private long machineId;

    private long stateId;

    private long sequence;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getBizId() {
        return bizId;
    }

    public void setBizId(long bizId) {
        this.bizId = bizId;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public Date getTimestampDate() {
        return timestampDate;
    }

    public void setTimestampDate(Date timestampDate) {
        this.timestampDate = timestampDate;
    }

    @Override
    public String toString() {
        FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss SSS");
        return "uniqueid {timestampDate=" + format.format(timestampDate) + ", timestamp=" + timestamp + ", bizId="
                + bizId + ", machineId=" + machineId + ", stateId=" + stateId + ", sequence=" + sequence + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (bizId ^ (bizId >>> 32));
        result = prime * result + (int) (machineId ^ (machineId >>> 32));
        result = prime * result + (int) (sequence ^ (sequence >>> 32));
        result = prime * result + (int) (stateId ^ (stateId >>> 32));
        result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UniqueElement other = (UniqueElement) obj;
        if (bizId != other.bizId)
            return false;
        if (machineId != other.machineId)
            return false;
        if (sequence != other.sequence)
            return false;
        if (stateId != other.stateId)
            return false;
        if (timestamp != other.timestamp)
            return false;
        return true;
    }
}

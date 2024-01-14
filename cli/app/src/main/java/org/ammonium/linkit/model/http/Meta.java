package org.ammonium.linkit.model.http;

import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("served_by")
    private String servedBy;

    @SerializedName("duration")
    private double duration;

    @SerializedName("changes")
    private int changes;

    @SerializedName("last_row_id")
    private int lastRowId;

    @SerializedName("changed_db")
    private boolean changed;

    @SerializedName("size_after")
    private int sizeAfter;

    @SerializedName("rows_read")
    private int rowsRead;

    @SerializedName("rows_written")
    private int rowsWritten;

    public String getServedBy() {
        return servedBy;
    }

    public void setServedBy(String servedBy) {
        this.servedBy = servedBy;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getChanges() {
        return changes;
    }

    public void setChanges(int changes) {
        this.changes = changes;
    }

    public int getLastRowId() {
        return lastRowId;
    }

    public void setLastRowId(int lastRowId) {
        this.lastRowId = lastRowId;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public int getSizeAfter() {
        return sizeAfter;
    }

    public void setSizeAfter(int sizeAfter) {
        this.sizeAfter = sizeAfter;
    }

    public int getRowsRead() {
        return rowsRead;
    }

    public void setRowsRead(int rowsRead) {
        this.rowsRead = rowsRead;
    }

    public int getRowsWritten() {
        return rowsWritten;
    }

    public void setRowsWritten(int rowsWritten) {
        this.rowsWritten = rowsWritten;
    }
}

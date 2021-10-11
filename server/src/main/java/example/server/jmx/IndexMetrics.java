package example.server.jmx;

import java.beans.ConstructorProperties;

public class IndexMetrics {

  private final long numKeys;
  
  private final long numValues;
  
  private final long numUses;
  
  private final long numUpdates;
  
  private final int readLockCount;
  
  @ConstructorProperties({"numKeys", "numValues", "numUses", "numUpdates", "readLockCount"})
  public IndexMetrics(long numKeys, long numValues, long numUses, long numUpdates, int readLockCount) {
    this.numKeys = numKeys;
    this.numValues = numValues;
    this.numUses = numUses;
    this.numUpdates = numUpdates;
    this.readLockCount = readLockCount;
  }
  
  public long getNumKeys() {
    return this.numKeys;
  }
  
  public long getNumValues() {
    return this.numValues;
  }
  
  public long getNumUses() {
    return this.numUses;
  }
  
  public long getNumUpdates() {
    return this.numUpdates;
  }
  
  public int getReadLockCount() {
    return this.readLockCount;
  }
}
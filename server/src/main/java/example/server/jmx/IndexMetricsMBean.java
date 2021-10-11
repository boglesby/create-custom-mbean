package example.server.jmx;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Region;

import org.apache.geode.cache.query.Index;
import org.apache.geode.cache.query.IndexStatistics;

public class IndexMetricsMBean implements IndexMetricsMXBean {

  public long fetchNumKeys(String regionName, String indexName) {
    IndexStatistics statistics = getIndexStatistics(regionName, indexName);
    return statistics.getNumberOfKeys();
  }

  public long fetchNumValues(String regionName, String indexName) {
    IndexStatistics statistics = getIndexStatistics(regionName, indexName);
    return statistics.getNumberOfValues();
  }

  public long fetchNumUses(String regionName, String indexName) {
    IndexStatistics statistics = getIndexStatistics(regionName, indexName);
    return statistics.getTotalUses();
  }

  public long fetchNumUpdates(String regionName, String indexName) {
    IndexStatistics statistics = getIndexStatistics(regionName, indexName);
    return statistics.getNumUpdates();
  }

  public int fetchReadLockCount(String regionName, String indexName) {
    IndexStatistics statistics = getIndexStatistics(regionName, indexName);
    return statistics.getReadLockCount();
  }
  
  public IndexMetrics fetchIndexMetrics(String regionName, String indexName) {
    IndexStatistics statistics = getIndexStatistics(regionName, indexName);
    long numKeys = statistics.getNumberOfKeys();
    long numValues = statistics.getNumberOfValues();
    long numUses = statistics.getTotalUses();
    long numUpdates = statistics.getNumUpdates();
    int readLockCount = statistics.getReadLockCount();
    return new IndexMetrics(numKeys, numValues, numUses, numUpdates, readLockCount);
  }
  
  private IndexStatistics getIndexStatistics(String regionName, String indexName) {
    Cache cache = CacheFactory.getAnyInstance();
    Region<?,?> region = cache.getRegion(regionName);
    Index index = cache.getQueryService().getIndex(region, indexName);
    return index.getStatistics();
  }
}

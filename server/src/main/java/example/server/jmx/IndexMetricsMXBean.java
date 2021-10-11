package example.server.jmx;

public interface IndexMetricsMXBean {

  long fetchNumKeys(String regionName, String indexName);

  long fetchNumValues(String regionName, String indexName);

  long fetchNumUses(String regionName, String indexName);

  long fetchNumUpdates(String regionName, String indexName);

  int fetchReadLockCount(String regionName, String indexName);
  
  IndexMetrics fetchIndexMetrics(String regionName, String indexName);
}
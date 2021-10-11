package example.server.callback;

import example.server.jmx.IndexMetricsMBean;
import example.server.jmx.IndexMetricsMXBean;
import org.apache.geode.cache.Cache;
import org.apache.geode.cache.util.CacheListenerAdapter;
import org.apache.geode.management.ManagementService;

import javax.management.ObjectName;
import java.util.Properties;

public class IndexMetricsMXBeanInitializer extends CacheListenerAdapter {

  public static final String INDEX_METRICS_MBEAN_OBJECT_NAME = "GemFire:type=Member,name=IndexMetrics";

  @Override
  public void initialize(Cache cache, Properties properties) {
    ManagementService service = ManagementService.getManagementService(cache);
    IndexMetricsMXBean bean = new IndexMetricsMBean();
    try {
      ObjectName beanObjectName = ObjectName.getInstance(INDEX_METRICS_MBEAN_OBJECT_NAME);
      ObjectName gemfireObjectName = service.registerMBean(bean, beanObjectName);
      service.federate(gemfireObjectName, IndexMetricsMXBean.class, false);
      cache.getLogger().info("Registered custom MXBean with objectName=" + gemfireObjectName);
    } catch (Exception e) {
      cache.getLogger().warning("Caught exception attempting to register custom MXBean due to:", e);
    }
  }
}

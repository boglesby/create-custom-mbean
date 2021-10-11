package example.server.function;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;
import org.apache.geode.cache.query.Index;
import org.apache.geode.cache.query.IndexStatistics;

import java.util.Arrays;

public class GetIndexReadLockCountFunction implements Function<Object[]> {

  @Override
  public void execute(FunctionContext<Object[]> context) {
    Object[] arguments = context.getArguments();
    context.getCache().getLogger().info("Executing function id=" + getId() + "; arguments=" + Arrays.toString(arguments));
    Region<?,?> region = context.getCache().getRegion((String) arguments[0]);
    Index index = context.getCache().getQueryService().getIndex(region, (String) arguments[1]);
    IndexStatistics statistics = index.getStatistics();
    int readLockCount = statistics.getReadLockCount();
    context.getResultSender().lastResult(readLockCount);
  }

  @Override
  public String getId() {
    return getClass().getSimpleName();
  }
}

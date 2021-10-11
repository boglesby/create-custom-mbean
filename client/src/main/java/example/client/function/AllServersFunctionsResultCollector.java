package example.client.function;

import org.apache.geode.cache.execute.FunctionException;
import org.apache.geode.cache.execute.ResultCollector;
import org.apache.geode.distributed.DistributedMember;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component("allServersResultCollector")
public class AllServersFunctionsResultCollector implements ResultCollector<Integer,Set<Map<String,Integer>>> {

  private final Map<String, Integer> serverResults = new ConcurrentHashMap<>();

  private Set<Map<String,Integer>> finalResults;

  @Override
  public Set<Map<String,Integer>> getResult() throws FunctionException {
    // See https://jira.spring.io/browse/DATAGEODE-295
    return this.finalResults;
  }

  @Override
  public Set<Map<String,Integer>> getResult(long timeout, TimeUnit unit) throws FunctionException, InterruptedException {
    return getResult();
  }

  @Override
  public void addResult(DistributedMember memberID, Integer result) {
    this.serverResults.put(memberID.getName(), result);
  }

  @Override
  public void endResults() {
    // This method is called once the function has completed running. Since the
    // ResultCollector is reused, the intermediate results need to be cleared
    // for the next execution.
    Map<String,Integer> map = new HashMap<>(this.serverResults);
    this.finalResults = Collections.singleton(map);
    clearResults();
  }

  @Override
  public void clearResults() {
    this.serverResults.clear();
  }
}

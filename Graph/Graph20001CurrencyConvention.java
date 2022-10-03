package Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Question https://leetcode.com/discuss/interview-question/1335225/Find-best-exchange-rate-from-currency1-to-currency2/1009696
 * Paramenters:
 *
 * array of currency conversion rates. E.g. ['USD', 'GBP', 0.77] which means 1 USD is equal to 0.77 GBP
 * an array containing a 'from' currency and a 'to' currency
 * Given the above parameters, find the conversion rate that maps to the 'from' currency to the 'to' currency.
 * Your return value should be a number.
 *
 * Example:
 * You are given the following parameters:
 *
 * Rates: ['USD', 'JPY', 110] ['US', 'AUD', 1.45] ['JPY', 'GBP', 0.0070]
 * To/From currency ['GBP', 'AUD']
 * Find the rate for the 'To/From' curency. In this case, the correct result is 1.89.
 */
public class Graph20001CurrencyConvention {
    /**
     * Time: 35min Oct 2022
     * Crib answer from https://leetcode.com/problems/evaluate-division/solutions/841211/evaluate-division/
     */
    class PathSearchInGraph {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

            // Build the graph
            HashMap<String, HashMap<String, Double>> graph = new HashMap<>();
            for (int i = 0; i < equations.size(); ++i) {

                String divident = equations.get(i).get(0);
                String divisor = equations.get(i).get(1);
                Double quotient = values[i];
                if (!graph.containsKey(divident)) {
                    graph.put(divident, new HashMap<>());
                }
                if (!graph.containsKey(divisor)) {
                    graph.put(divisor, new HashMap<>());
                }
                graph.get(divident).put(divisor, quotient);
                graph.get(divisor).put(divident, 1/quotient);
            }

            // Get result
            final int N = queries.size();
            double[] result = new double[N];
            Set<String> visited = new HashSet<>();
            for (int i = 0; i < N; ++i) {
                List<String> query = queries.get(i);
                String divident = query.get(0);
                String divisor = query.get(1);
                if (!graph.containsKey(divident) || !graph.containsKey(divisor)) {
                    result[i] = -1;
                } else if (divident.equals(divisor)) {
                    result[i] = 1;
                } else {
                    result[i] = dfs(graph, divident, divisor, 1.0, visited); // to verify
                }
            }
            return result;
        }
        private Double dfs (HashMap<String, HashMap<String, Double>> graph, String divident, String divisor, Double value, Set<String> visited) {
            HashMap<String, Double> adjacents = graph.get(divident);
            Double result = -1.0;
            visited.add(divident);
            if (adjacents.containsKey(divisor)) {
                result = value * adjacents.get(divisor);
            } else {
                for (String nextNode : adjacents.keySet()) {
                    if (visited.contains(nextNode)) {
                        continue;
                    }
                    Double val = adjacents.get(nextNode);
                    result = dfs(graph, nextNode, divisor, value * val, visited);
                    if (result != -1.0) {
                        break;
                    }
                }
            }
            // No result find with current node. Remove from visited set
            visited.remove(divident);
            return result;
        }
    }
}

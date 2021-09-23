package com.raymond.trimble;

import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class GraphUtils {

    static DefaultDirectedGraph addEdge(Graph<Cluster, DefaultEdge> graph, Cluster v1, Cluster v2) throws CycleException {
            DefaultDirectedGraph newGraph = (DefaultDirectedGraph) ((DefaultDirectedGraph)graph).clone();
             newGraph.addEdge(v1, v2);
             CycleDetector cycleDetector = new CycleDetector(newGraph);
             if(cycleDetector.detectCycles() == false) {
                 return newGraph;
             } else {
                 throw new CycleException();
             }

    }
}

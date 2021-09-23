package com.raymond.trimble;

import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Graph<Cluster, DefaultEdge> directedGraph =
                new DefaultDirectedGraph<Cluster, DefaultEdge>(DefaultEdge.class);

        Resource generalLabour = new Resource("General Labour", "General Labour", 30, "/hr");
        Resource brickLayer = new Resource("Brick Layer", "Brick Layer", 65, "/hr");
        Resource plasterer = new Resource("Plasterer", "Plasterer", 45, "/hr");
        Resource supervisor = new Resource("Supervisor", "Supervisor", 75, "/hr");
        Resource siteAgent = new Resource("Site Agent", "Site Agent", 200, "/hr");

        ClusterResource genLabourCR = new ClusterResource(generalLabour, 8);
        ClusterResource brickLayerCR = new ClusterResource(brickLayer, 8);
        ClusterResource plastererCR = new ClusterResource(plasterer, 8);
        ClusterResource supervisorCR = new ClusterResource(supervisor, 8);
        ClusterResource siteAgentCR = new ClusterResource(siteAgent, 8);


        Cluster wallBuildingTeam = new Cluster(Arrays.asList(genLabourCR, brickLayerCR, plastererCR));
        Cluster excavationTeam = new Cluster(Arrays.asList(genLabourCR, supervisorCR));
        Cluster houseBuildingTeam = new Cluster(Arrays.asList(siteAgentCR));

        // Edge weighting can be used if a cluster should have a transform factor.
        directedGraph.addVertex(wallBuildingTeam);
        directedGraph.addVertex(excavationTeam);
        directedGraph.addVertex(houseBuildingTeam);

        try {
            directedGraph = GraphUtils.addEdge(directedGraph, houseBuildingTeam, wallBuildingTeam);
            System.out.println("Added wall building team to house building team");
        } catch (CycleException exception) {
            System.out.println("Failed to add edge from wall building team to house building team");
        }

        try {
            directedGraph = GraphUtils.addEdge(directedGraph, houseBuildingTeam, excavationTeam);
            System.out.println("Added excavation team to house building team");
        } catch (CycleException exception) {
            System.out.println("Failed to add edge from wall building team to house building team");
        }

        try {
            directedGraph = GraphUtils.addEdge(directedGraph, excavationTeam, houseBuildingTeam);
        } catch (CycleException exception) {
            System.out.println("Failed to add edge from wall building team to house building team, cycle detected");
        }


        BreadthFirstIterator<Cluster, DefaultEdge> bfsIter = new BreadthFirstIterator(directedGraph);

        double rate = 0;
        while(bfsIter.hasNext()) {
            Cluster c = bfsIter.next();
            double clusterRate = c.getRate();
            rate = rate + clusterRate;
        }

        System.out.println("Rate: " + rate);

        CycleDetector cycleDetector = new CycleDetector(directedGraph);

        boolean hasCycles = cycleDetector.detectCycles();

        System.out.println("Graph has cycles: " + hasCycles);
    }
}

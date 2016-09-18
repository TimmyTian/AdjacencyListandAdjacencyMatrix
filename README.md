# AdjacencyListandAdjacencyMatrix

## Algorithm and Analysis Assignment 1

In this assignment we were asked to model adjacency list and adjacency matrix also finding the shortest distance between 2 nodes. The shortest distance and path from a node to another was calculated using Djikstra Algorithmn for both Adjacency List and Adjacency Matrix.


## Testing this script

1) Clone this repository on to your machine 

```console
git clone https://github.com/antodoms/AdjacencyListandAdjacencyMatrix
```

2) navigate to src folder 
```console
cd AdjacencyListandAdjacencyMatrix/src
```

3) Run the python scripts:
```console
python assign1TestScript.py -v . adjlist testing/tests/test1.in
python assign1TestScript.py -v . adjmat testing/tests/test1.in

python assign1TestScript.py -v . adjlist testing/tests/test2.in -f facebook_combined.txt
python assign1TestScript.py -v . adjmat testing/tests/test2.in -f facebook_combined.txt

python assign1TestScript.py -v . adjlist testing/tests/test3.in -f test3LoadedGraph.txt
python assign1TestScript.py -v . adjmat testing/tests/test3.in -f test3LoadedGraph.txt
```

## Test Script Generator Class

Test Script were generated using a custom generator class to make the density based report for the assignment. DataGenerator class can be found in the src folder.

This is how you generate a Test Script according to your density requirement:
```java
DataGenerator(int length, int no_of_add_vertex,
                         int no_of_add_edges,
                         int no_of_remove_vertex,
                         int no_of_remove_edges,
                         int no_of_shortest_path,
                         int no_of_neighbours)
```

Consider you want a graph with 1000 vertices and 100% density (ie, each vertice is connected to every other vertice) , and you want to remove 100 vertices and 200 edges also calculating 50 hortest path and 50 neighbours then the object has to be initialised like below:
```java
 DataGenerator dataGenerator = new DataGenerator(1000,(1000*1000-1000), 100,200,50,50);
```
Similarily, for generating a 50% density based graph you could use the object like below:
```java
 DataGenerator dataGenerator = new DataGenerator(1000,(1000*1000-1000)*0.5, 100,200,50,50);
```

## Colaborators

- Anto Dominic (Student)
- Rashiv Romeo Bhusal (Student)
- Marked by Jeffrey Chan (Lecturer)

## License

- Modified by Anto Dominic
- Copyright © 2016 released under the MIT license.

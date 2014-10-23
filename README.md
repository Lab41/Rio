Rio
=========
<small>Gephi + Blueprints = Scalable Graph Visualizations</small>

  - [Blueprints] is a collection of interfaces and test suites for a standardized property graph data model
  - [Gephi] is an interactive visualization and exploration platform for graphs
  - Rio is the connective tissue between the two

Installation
--------------

```sh
# prerequisites
sudo apt-get install --assume-yes maven
# <manually install JDK7>: download and install from http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html"

# build v0.0.1 of gephi graphstore (omit once gephi publishes to mvn repo)
git clone git@github.com:gephi/graphstore.git
cd graphstore/store
mvn package
# <manually add jar>: ensure target/graphstore-0.1.1-SNAPSHOT.jar is in classpath for OS or IDE (i.e. IntelliJ/Eclipse)

# clone project
git clone git@github.com:LAB41/rio.git rio
cd rio
mvn install

# verify tests
# <manually run> in IDE: src/test/java/org.lab41.bptutorial.TinkerTestSuite
```


Goals
--------------
1. **Blueprints Implementation**: Enable Blueprints-enabled applications to interact with Gephi's storage backend
2. **Blueprints Ouplementation**: Enable Gephi to interact with Blueprints-enabled storage backends
3. **Visualization Plugin**: Develop a Gephi plugin to connect to Blueprints storage backend(s)

Progress
--------------
1. Implementation:
    - &#10004; Graph
    - &#10004; Element
    - &#10004; Node
    - &#10004; Edge
    - &#x2717; Manual Indexing (unsupported by Gephi's public APIs)
2. Ouplementation:
    - Design under review
3. Visualization
    - Design pending completion of Ouplementation

Contributers
--------------
- [Abhinav Ganesh], lead maintainer
- [Kyle Foster], developer

License
----

MIT


[Blueprints]: https://github.com/tinkerpop/blueprints/wiki
[Gephi]: http://gephi.github.io/
[Abhinav Ganesh]: https://github.com/aganeshlab41
[Kyle Foster]: https://github.com/kylef-lab41

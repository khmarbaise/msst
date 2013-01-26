Maven Subversion Scripting Tool
===============================

- List with repositories
  inclusive modules

  like:

	xyz
 	  +--- module-1
	  +--- module-2
	  +--- module-3

	abc
	  +--- module-1
	  +--- module-2
	  +--- module-3



	<repositories>
	  <repository>
	    <id>xyz</id>
	    <modules>
	      <module>module-1</module>
	      <module>module-2</module>
	      <module>module-3</module>
	    </modules>
	  </repository>
	  <repository>
	    <id>abc</id>
	    <modules>
	      <module>module-1</module>
	      <module>module-2</module>
	      <module>module-3</module>
	    </modules>
	  </repository>
	</repositories>


  Might be simpler in [yaml](http://yaml.org) format:

	repositories:
		repository:
			id: xyz
			modules:
				module: module-1
				module: module-2
				module: module-3
		repository:
			id: abc
			modules:
				module: module-1
				module: module-2
				module: module-3


Simpler Usage on a "application" level.

  Define an application like app1

  app1
     +-- xyz
          +--- module-1
          +--- module-2
     +-- abc
          +--- module-3


	<applications>
	  <repositories>
	    <repository>
	      <id>xyz</id>
	      <modules>
	        <module>module-1</module>
	        <module>module-2</module>
	      </modules>
	    </repository>
	    <repository>
	      <id>abc</id>
	      <modules>
	        <module>module-3</module>
	      </modules>
	    </repository>
	  </repositories>
	</applications>


  what about yaml format might be simpler to handle in particular for human beings.


	applications:
		repositories:
			repository:
				id: xyz
				modules:
					module: module-1
					module: module-2
					module: module-3
			repository:
				id: abc
				modules:
					module: module-1
					module: module-2
					module: module-3



 
- Basic Command


  msst branching --branch BRANCH-NAME --branch-version=2.3-SNAPSHOT repo/module repo/module etc.

  msst branching --branch BRANCH-NAME --branch-version=2.3-SNAPSHOT @repository-module.lst


Execution Block for a single module must be repeated for every module:

  1. Checkout repo/module

        svn checkout URL/repo/module/trunk workspace/repo/module/trunk

  2. Create Branch

        mvn release:branch -DbranchName=BRANCH-NAME -DupdateBranchVersion=true -DreleaseVersion=2.3-SNAPSHOT

     (2. Release)
  3. Create Jenkins Jobs based on the branches



The execution block can be parallelized.


There exist a [maven-invoker](http://maven.apache.org/shared/maven-invoker/) furthermore we need an
svn-invoker


   msst reintegrate --branch BRANCH-NAME repo/module repo/module etc.


        svn checkout URL/repo/module/trunk
        svn merge --accept ? --reintegrate /RL/repo/module/branches/BRANCH-NAME

          => without conflicts.
              svn commit -m"- xxxx"
          => with conflicts.
              Leave unchanged for manual solution.
              May be move to a particular location
              Check if the conflicts are only in the pom.xml files
              in the version tag? => solution use the one from the BRANCH-NAME.



   msst synchronize --branch BRANCH-NAME repo/module repo/module etc.

          
        svn checkout URL/repo/module/branches/BRANCH-NAME
        svn merge --accept ?  URL/repo/module/trunk

          => without conflicts.
              svn commit -m"- xxxx"
          => with conflicts.
              Leave unchanged for manual solution.
              May be move to a particular location


   msst rebase BRANCH-NAME repo/module repo/module etc.

          


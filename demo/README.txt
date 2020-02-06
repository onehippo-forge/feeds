Running locally
===============

This project uses the Maven Cargo plugin to run the CMS and site locally in Tomcat.
From the project root folder, execute:

  $ mvn clean install
  $ mvn -P cargo.run

Access the CMS at http://localhost:8080/cms, and the site at http://localhost:8080/site
Logs are located in target/tomcat6x/logs

Building distribution
=====================

To build a Tomcat distribution tarball containing only deployable artifacts:

  $ mvn clean install
  $ mvn -P dist

See also src/main/assembly/distribution.xml if you need to customize the distribution

Using JRebel
============

Set the environment variable REBEL_HOME to the directory containing jrebel.jar.

Build with:

  $ mvn clean install -Djrebel

Start with:

  $ mvn -P cargo.run -Djrebel

Best Practice for development
============

Use the option -Drepo.path=/some/path/to/repository during start up. This will avoid
your repository to be cleared when you do a mvn clean. 

For example start your project with: 

$ mvn -P cargo.run -Drepo.path=/home/usr/tmp/repo

or with jrebel:

$ mvn -P cargo.run -Drepo.path=/home/usr/tmp/repo -Djrebel

Hot deploy
==========

To hot deploy, redeploy or undeploy the CMS or site:

  $ cd cms (or site)
  $ mvn cargo:redeploy (or cargo:undeploy, or cargo:deploy)
  
Automatic Export
================

Automatic export of repository changes to the filesystem is turned on by default. To control this behavior, log into
<http://localhost:8080/cms/console> and press the "Enable/Disable Auto Export" button at the top right. To set this
as the default for your project edit the file
./repository-data/application/src/main/resources/hcm-config/configuration/modules/autoexport-module.yaml


Building Distributions
======================

To build Tomcat distribution tarballs:

    mvn clean verify
    mvn -P dist
      or
    mvn -P dist-with-development-data

The `dist` profile will produce in the /target directory a distribution tarball, containing the main deployable wars and
shared libraries.

The `dist-with-development-data` profile will produce a distribution-with-development-data tarball, also containing the
repository-data-development jar in the shared/lib directory. This kind of distribution is meant to be used for
deployments to development environments, for instance local deployments or deployments to a continuous integration (CI)
system. (Initially, this module contains only "author" and "editor" example users for use in testing. Other data must be
placed in this module explicitly by developers, for demo or testing purposes, etc.)

See also src/main/assembly/*.xml if you need to customize the distributions.


Distributing Additional Site Projects
=====================================

Note that if your organization is using multiple site projects, you must configure the assembly of a distribution to
include all of the separate site webapps for deployment. This project is designed for stand-alone use and does not
automatically include any additional, externally-maintained site webapps.


Running the brXM Project in a Docker Container
======================

To run the brXM project in a docker container, you must install the project, build the docker image and run the docker
image respectively.

First install the project:

    mvn clean install

Then build the brXM docker image:

    mvn -Pdocker.build

This maven profile will create a docker image and add it to the local docker registry. The new image will be tagged
as org.example/feedsdemo:5.0.1-SNAPSHOT

To run the image with in-memory h2 database:

    mvn -Pdocker.run


Changes to your repository are automatically exported to filesystem during local development, to disable this feature, 
log into the console and press "disable auto export".

Monitoring with JMX Console
===========================
You may run the following command:

  $ jconsole 
 
Now open the local process org.apache.catalina.startyp.Bootstrap start
  

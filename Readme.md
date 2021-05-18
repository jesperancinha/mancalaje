# Kalah-Coffee-Mugs

---

[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Kalah%20Coffee%20Mugs&color=informational)](https://github.com/jesperancinha/mancalaje)
[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/mancalaje.svg)](#)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

[![CircleCI](https://circleci.com/gh/jesperancinha/mancalaje.svg?style=svg)](https://circleci.com/gh/jesperancinha/mancalaje)
[![Build Status](https://travis-ci.com/jesperancinha/mancalaje.svg?branch=master)](https://travis-ci.com/jesperancinha/mancalaje)
[![Build status](https://ci.appveyor.com/api/projects/status/rd3x0g0muu0se6ed?svg=true)](https://ci.appveyor.com/project/jesperancinha/mancalaje)
[![mancalaje](https://github.com/jesperancinha/mancalaje/actions/workflows/mancalaje.yml/badge.svg)](https://github.com/jesperancinha/mancalaje/actions/workflows/mancalaje.yml)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f30b682f67e44391a922f62ada6b2f85)](https://www.codacy.com/app/jofisaes/mancalaje?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/mancalaje&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/eaf7a2e0-ed2c-47fb-9323-2051db72c171)](https://codebeat.co/projects/github-com-jesperancinha-mancalaje-master)
[![BCH compliance](https://bettercodehub.com/edge/badge/jesperancinha/mancalaje?branch=master)](https://bettercodehub.com/results/jesperancinha/mancalaje)
[![Known Vulnerabilities](https://snyk.io/test/github/jesperancinha/mancalaje/badge.svg)](https://snyk.io/test/github/jesperancinha/mancalaje)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/f30b682f67e44391a922f62ada6b2f85)](https://www.codacy.com/gh/jesperancinha/mancalaje/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/mancalaje&utm_campaign=Badge_Coverage)
[![codecov](https://codecov.io/gh/jesperancinha/mancalaje/branch/master/graph/badge.svg?token=FytnZVKZcG)](https://codecov.io/gh/jesperancinha/mancalaje)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/mancalaje/badge.svg?branch=master)](https://coveralls.io/github/jesperancinha/mancalaje?branch=master)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/mancalaje.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/mancalaje.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/mancalaje.svg)](#)

---

# Technologies used

---

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/java-50.png "Java")](https://www.oracle.com/nl/java/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/lombok-50.png "Lombok")](https://projectlombok.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-50.png "Spring Framework")](https://spring.io/projects/spring-framework)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-boot-50.png "Spring Boot")](https://spring.io/projects/spring-boot)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/angular-50.png "Angular")](https://angular.io/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/react-50.png "React")](https://reactjs.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/typescript-50.png "Typescript")](https://www.typescriptlang.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/openshift-50.png "Openshift")](https://www.openshift.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/minikube-50.png "Minikube")](https://github.com/kubernetes/minikube)
---

## Project status

<i>Under construction</i>

## Introduction for the introduction

This game is a slight change from he typical Kalah game, where in this case I'll be using coffe mugs instead of stones. This way, more original will rules will be added. Stay tuned...

☕️

## Introduction

According to [Ultraboard Games](https://www.ultraboardgames.com/mancala/history.php) the mancala game has existed for about 7000 years. This means that since the middle neolithic phase of History, mankind has been playing this game.

This project is also the official support project of my article on medium:

[Deploying Mancala with Minikube](https://medium.com/swlh/deploying-mancala-with-minikube-4bc09a15a405)

## Installation notes

```bash
yarn install
npm audit fix
npm install coveralls --save-dev
```

## Docker Compose

I have provided a docker-compose environment that will run this application in your docker-machine addess.

Make sure that the docker-machine is correctly installed and that you set your environment to your machine.

All that needs top be done is to call the build.sh script with the name of the target docker-machine. In my case its 'dev'.

Example:

```bash
./build.sh dev
```

## Startup

### Backend

```bash
cd kala-game-service
mvn spring-boot:run
```

Note that I attempted to create this project with reactive programming and it is possible and it would be more efficient, however I had to rollback because of the amount of time it would take to complete it.

### Frontend

```bash
cd kala-game-web
npm start
```

### Available users

Users are currently hardcoded and there are only two:

|Username|Password|
|---|---|
|player1|admin|
|player2|admin|

### cUrl

```bash
curl -i -H "Content-Type: application/x-www-form-urlencoded" -c cookies.txt  -d "username=player1&password=admin" -X POST http://localhost:8081/login
curl -i -b cookies.txt http://localhost:8081/user
curl -i -b cookies.txt -X POST http://localhost:8081/api
curl -i -b cookies.txt -X POST http://localhost:8081/api/create
curl -i -b cookies.txt -X PUT http://localhost:8081/api/move/16/2
curl -i -b cookies.txt -X PUT http://localhost:8081/api/move/16/3

curl -i -H "Content-Type: application/x-www-form-urlencoded" -c cookies2.txt  -d "username=player2&password=admin" -X POST http://localhost:8081/login
curl -i -b cookies2.txt -X PUT http://localhost:8081/api/join/16
```

### Practical Example

Have a look at [game.sh](./game.sh) for a practical example, where player 1 wins.

This example relies on static Id's so you can only run it once after a restart.

1.  [cookies.txt](./cookies.txt) holds the session for player1
2.  [cookies2.txt](./cookies2.txt) holds the session for player2

### Front - End - Web application

In order to start the FE, you need to have NODE.JS installed.

Preferably try to install everything using Yarn.

```bash
yarn install
```

To start, go to [kala-game-web](./kala-game-web) and run:

```bash
npm start
```

Please check [Ops](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Ops.md) documentation for more info on how to deploy in K8's.

## System utility installation

-   Installing LCov
```bash
brew install lcov
```

```bash
apt-get install lcov
```

-   Merging reports

```bash
find packages -name lcov.info -exec echo -a {} \; | xargs lcov -o coverage/lcov.info
```

## Yarn

```bash
yarn workspaces run coverage
```

## References 📚

### [![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/openshift-50.png "Openshift")](https://www.openshift.com/) Openshift

-   [OpenShift by Tutorials Point](https://www.tutorialspoint.com/openshift/index.htm)
-   [Learn Open Shift](https://learn.openshift.com/)
-   [Minishift](https://docs.okd.io/latest/minishift/index.html)
-   [Minishif Installation](https://docs.okd.io/latest/minishift/getting-started/installing.html)
-   [Fabric 8 maven plugin](https://github.com/fabric8io/fabric8-maven-plugin)
-   [Fabric 8](Fabric 8 maven plugin)

### [![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/minikube-50.png "Minikube")](https://github.com/kubernetes/minikube) Minikube

-   [What is minikube, kubectl and kubelet by Andreth Salazar](https://www.quora.com/What-is-minikube-kubectl-and-kubelet)
-   [Install Minikube](https://kubernetes.io/docs/tasks/tools/install-minikube/)
-   [Installing Kubernetes with Minikube](https://kubernetes.io/docs/setup/learning-environment/minikube/)
-   [Trigger a Spring Batch job with a JMS message](https://blog.mimacom.com/trigger-spring-batch-job-with-jms-message/)
-   [Postman](https://www.getpostman.com/)
-   [Spring Boot OAuth2 Social Login with Google, Facebook, and Github - Part 1](https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-1/)
-   [Shields IO](https://shields.io)
-   [How To Install Oracle Java 8 and OpenJDK 11 on Ubuntu 18.04, 19.04](https://www.linuxbabe.com/ubuntu/install-oracle-java-8-openjdk-11-ubuntu-18-04-18-10)
-   [Build and Deploy a Spring Boot App on Kubernetes (Minikube)](https://gorillalogic.com/blog/build-and-deploy-a-spring-boot-app-on-kubernetes-minikube/)

### Implementation related

-   [Apache MQ BrokerService](https://activemq.apache.org/maven/apidocs/org/apache/activemq/broker/BrokerService.html)
-   [Apache MQ BrokerFactory](https://activemq.apache.org/maven/apidocs/org/apache/activemq/broker/BrokerFactory.html)
-   [Apache MQ ActiveMQConnectionFactory](https://activemq.apache.org/maven/apidocs/org/apache/activemq/ActiveMQConnectionFactory.html)

### Environment related

-   [Using Meslo LG with the Windows Console](https://github.com/andreberg/Meslo-Font/wiki/Using-Meslo-LG-with-the-Windows-Console)
-   [Install iTerm2 with Solarized in 2017](https://gist.github.com/ZenLulz/c812f70fc86ebdbb189d9fb82f98197e)
-   [Oh My Zsh will not make you a 10x developer...but you may feel like one](https://github.com/ohmyzsh/ohmyzsh/)
-   [Oh My ZSH!](https://ohmyz.sh/)
-   [How to add Custom Fonts to Command Prompt in Windows 10](https://www.thewindowsclub.com/add-custom-fonts-to-command-prompt)
-   [How can I add additional fonts to the Windows console?](https://superuser.com/questions/1347724/how-can-i-add-additional-fonts-to-the-windows-console)
-   [Powerline fonts](https://github.com/powerline/fonts.git)
-   [Microsoft Terminal](https://github.com/Microsoft/Terminal)
-   [How To Install Minikube on Ubuntu 18.04 / Debian 10 Linux](https://computingforgeeks.com/how-to-install-minikube-on-ubuntu-18-04/)
-   [Install KVM on CentOS / RHEL / Ubuntu / Debian / SLES / Arch Linux](https://computingforgeeks.com/install-kvm-centos-rhel-ubuntu-debian-sles-arch/)
-   [choco package? #9](https://github.com/machine-drivers/docker-machine-driver-vmware/issues/9)
-   [Workstation Player](https://www.vmware.com/products/workstation-player.html)
-   [Try VMware Workstation Pro](https://www.vmware.com/products/workstation-pro/workstation-pro-evaluation.html)
-   [Download VMware VIX 1.15.7](https://my.vmware.com/web/vmware/details?productId=640&downloadGroup=PLAYER-1253-VIX1157)
-   [Automation Tools and SDK(s)](https://my.vmware.com/web/vmware/free#desktop_end_user_computing/vmware_player/6_0|PLAYER-602|drivers_tools)

NOTE: remember to use the powershell script if trying to install the Meslo fonts in windows ([.ps1 file](https://github.com/powerline/fonts/blob/master/install.ps1)). It's located in the [fonts repo](https://github.com/powerline/fonts.git).

## About me 👨🏽‍💻🚀🏳️‍🌈

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/JEOrgLogo-20.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/@jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/credly-20.png "Credly")](https://www.credly.com/users/joao-esperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=joaofilipesabinoesperancinha.nl&color=6495ED "João Esperancinha Homepage")](https://joaofilipesabinoesperancinha.nl/)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=jesperancinha&style=social "GitHub")](https://github.com/jesperancinha)
[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social "Twitter")](https://twitter.com/joaofse)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=yellow "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)   
[![Generic badge](https://img.shields.io/static/v1.svg?label=Articles&message=Across%20The%20Web&color=purple)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Articles.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Webapp&message=Image%20Train%20Filters&color=6495ED)](http://itf.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Info.md)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/coursera-20.png "Coursera")](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/google-apps-20.png "Google Apps")](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)   
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/sonatype-20.png "Sonatype Search Repos")](https://search.maven.org/search?q=org.jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/docker-20.png "Docker Images")](https://hub.docker.com/u/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/stack-overflow-20.png)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/reddit-20.png "Reddit")](https://www.reddit.com/user/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/devto-20.png "Dev To")](https://dev.to/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackernoon-20.jpeg "Hackernoon")](https://hackernoon.com/@jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeproject-20.png "Code Project")](https://www.codeproject.com/Members/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/github-20.png "GitHub")](https://github.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bitbucket-20.png "BitBucket")](https://bitbucket.org/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/gitlab-20.png "GitLab")](https://gitlab.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bintray-20.png "BinTray")](https://bintray.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/free-code-camp-20.jpg "FreeCodeCamp")](https://www.freecodecamp.org/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackerrank-20.png "HackerRank")](https://www.hackerrank.com/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeforces-20.png "Code Forces")](https://codeforces.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codebyte-20.png "Codebyte")](https://coderbyte.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codewars-20.png "CodeWars")](https://www.codewars.com/users/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codepen-20.png "Code Pen")](https://codepen.io/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hacker-news-20.png "Hacker News")](https://news.ycombinator.com/user?id=jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/infoq-20.png "InfoQ")](https://www.infoq.com/profile/Joao-Esperancinha.2/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/linkedin-20.png "LinkedIn")](https://www.linkedin.com/in/joaoesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/xing-20.png "Xing")](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/tumblr-20.png "Tumblr")](https://jofisaes.tumblr.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/pinterest-20.png "Pinterest")](https://nl.pinterest.com/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/quora-20.png "Quora")](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)

## Achievements

[![Oracle Certified Professional, JEE 7 Developer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-ee-7-application-developer-100.png "Oracle Certified Professional, JEE7 Developer")](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
[![Oracle Certified Professional, Java SE 11 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-se-11-developer-100.png "Oracle Certified Professional, Java SE 11 Programmer")](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
[![Oracle Certified Professional, Java SE 8 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-se-8-programmer-100.png "Oracle Certified Professional, Java SE 8 Programmer")](https://www.credly.com/badges/92e036f5-4e11-4cff-9935-3e62266d2074)
[![Oracle Certified Associate, Java SE 8 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-associate-java-se-8-programmer-100.png "Oracle Certified Associate, Java SE 8 Programmer")](https://www.credly.com/badges/a206436d-6fd8-4ca1-8feb-38a838446ee7)
[![Oracle Certified Associate, Java SE 7 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-associate-java-se-7-programmer-100.png "Oracle Certified Associate, Java SE 7 Programmer")](https://www.credly.com/badges/f4c6cc1e-cb52-432b-904d-36d266112225)
[![Oracle Certified Junior Associate](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-foundations-associate-java-100.png "Oracle Certified Foundations Associate")](https://www.credly.com/badges/6db92c1e-7bca-4856-9543-0d5ed0182794)

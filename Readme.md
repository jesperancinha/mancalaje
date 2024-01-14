# Coffee Cups Kalah

---

[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Coffee%20Cups%20Kalah%20☕️%20&color=informational)](https://github.com/jesperancinha/mancalaje)
[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/mancalaje.svg)](#)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

[![CircleCI](https://circleci.com/gh/jesperancinha/mancalaje.svg?style=svg)](https://circleci.com/gh/jesperancinha/mancalaje)
[![Build status](https://ci.appveyor.com/api/projects/status/rd3x0g0muu0se6ed?svg=true)](https://ci.appveyor.com/project/jesperancinha/mancalaje)
[![mancalaje](https://github.com/jesperancinha/mancalaje/actions/workflows/mancalaje.yml/badge.svg)](https://github.com/jesperancinha/mancalaje/actions/workflows/mancalaje.yml)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f30b682f67e44391a922f62ada6b2f85)](https://www.codacy.com/app/jofisaes/mancalaje?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/mancalaje&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/eaf7a2e0-ed2c-47fb-9323-2051db72c171)](https://codebeat.co/projects/github-com-jesperancinha-mancalaje-master)
[![Known Vulnerabilities](https://snyk.io/test/github/jesperancinha/mancalaje/badge.svg)](https://snyk.io/test/github/jesperancinha/mancalaje)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/f30b682f67e44391a922f62ada6b2f85)](https://www.codacy.com/gh/jesperancinha/mancalaje/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/mancalaje&utm_campaign=Badge_Coverage)
[![codecov](https://codecov.io/gh/jesperancinha/mancalaje/branch/master/graph/badge.svg?token=FytnZVKZcG)](https://codecov.io/gh/jesperancinha/mancalaje)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/mancalaje/badge.svg?branch=master)](https://coveralls.io/github/jesperancinha/mancalaje?branch=master)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/mancalaje.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/mancalaje.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/mancalaje.svg)](#)

---

## Technologies used

Please check the [TechStack.md](TechStack.md) file for details.

## Project status

🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧 Under construction</i> 🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧

## Introduction for the introduction

This game is a slight change from the typical Kalah game, where in this case I'll be using coffe mugs instead of stones.
This way, more original will rules will be added. Stay tuned...

☕️

To keep up with current changes, please have a look at the [Rules](./Rules.md) documentation.

Note that the above will happen along with multiple other changes. Check out my [planning document](./Planning.md) to
follow up with the running changes...

## Introduction

According to [Ultraboard Games](https://www.ultraboardgames.com/mancala/history.php) the mancala game has existed for
about 7000 years. This means that since the middle neolithic phase of History, mankind has been playing this game.

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

All that needs top be done is to call the build.sh script with the name of the target docker-machine. In my case its '
dev'.

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

Note that I attempted to create this project with reactive programming and it is possible and it would be more
efficient, however I had to rollback because of the amount of time it would take to complete it.

### Frontend

```bash
cd packages/kala-game-web
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

Please check [Ops](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Ops.md)
documentation for more info on how to deploy in K8's.

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

## Roadmap to version 2.0.0

The intention of version 2.0.0, is layed out for you in file [Planning](./Planning.md). Essentially the goal is to
provide an efficient and running modified version of the original Kalah game.

## References

-   [OpenShift by Tutorials Point](https://www.tutorialspoint.com/openshift/index.htm)
-   [Learn Open Shift](https://learn.openshift.com/)
-   [Minishift](https://docs.okd.io/latest/minishift/index.html)
-   [Minishif Installation](https://docs.okd.io/latest/minishift/getting-started/installing.html)
-   [Fabric 8 maven plugin](https://github.com/fabric8io/fabric8-maven-plugin)
-   [Fabric 8](Fabric 8 maven plugin)
-   [What is minikube, kubectl and kubelet by Andreth Salazar](https://www.quora.com/What-is-minikube-kubectl-and-kubelet)
-   [Install Minikube](https://kubernetes.io/docs/tasks/tools/install-minikube/)
-   [Installing Kubernetes with Minikube](https://kubernetes.io/docs/setup/learning-environment/minikube/)
-   [Trigger a Spring Batch job with a JMS message](https://blog.mimacom.com/trigger-spring-batch-job-with-jms-message/)
-   [Postman](https://www.getpostman.com/)
-   [Spring Boot OAuth2 Social Login with Google, Facebook, and Github - Part 1](https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-1/)
-   [Shields IO](https://shields.io)
-   [How To Install Oracle Java 8 and OpenJDK 11 on Ubuntu 18.04, 19.04](https://www.linuxbabe.com/ubuntu/install-oracle-java-8-openjdk-11-ubuntu-18-04-18-10)
-   [Build and Deploy a Spring Boot App on Kubernetes (Minikube)](https://gorillalogic.com/blog/build-and-deploy-a-spring-boot-app-on-kubernetes-minikube/)
-   [Apache MQ BrokerService](https://activemq.apache.org/maven/apidocs/org/apache/activemq/broker/BrokerService.html)
-   [Apache MQ BrokerFactory](https://activemq.apache.org/maven/apidocs/org/apache/activemq/broker/BrokerFactory.html)
-   [Apache MQ ActiveMQConnectionFactory](https://activemq.apache.org/maven/apidocs/org/apache/activemq/ActiveMQConnectionFactory.html)
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

NOTE: remember to use the powershell script if trying to install the Meslo fonts in
windows ([.ps1 file](https://github.com/powerline/fonts/blob/master/install.ps1)). It's located in
the [fonts repo](https://github.com/powerline/fonts.git).

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)

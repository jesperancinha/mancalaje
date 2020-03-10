# MancalaJe

[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=MancalaJE&color=informational)](http://mancalaje.joaofilipesabinoesperancinha.nl/) 
[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/mancalaje.svg)](#)
  
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f30b682f67e44391a922f62ada6b2f85)](https://www.codacy.com/app/jofisaes/mancalaje?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/mancalaje&amp;utm_campaign=Badge_Grade)
[![CircleCI](https://circleci.com/gh/jesperancinha/mancalaje.svg?style=svg)](https://circleci.com/gh/jesperancinha/mancalaje)
[![Build Status](https://travis-ci.org/jesperancinha/mancalaje.svg?branch=master)](https://travis-ci.org/jesperancinha/mancalaje)
[![codebeat badge](https://codebeat.co/badges/eaf7a2e0-ed2c-47fb-9323-2051db72c171)](https://codebeat.co/projects/github-com-jesperancinha-mancalaje-master)
[![BCH compliance](https://bettercodehub.com/edge/badge/jesperancinha/mancalaje?branch=master)](https://bettercodehub.com/)
[![Known Vulnerabilities](https://snyk.io/test/github/jesperancinha/mancalaje/badge.svg)](https://snyk.io/test/github/jesperancinha/mancalaje)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/mancalaje.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/mancalaje.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/mancalaje.svg)](#)
  
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=MancalaJE&color=informational)](http://mancalaje.joaofilipesabinoesperancinha.nl/)

```text                                                                                         
@@@@@@@@@@    @@@@@@   @@@  @@@   @@@@@@@   @@@@@@   @@@        @@@@@@                   @@@  @@@@@@@@  
@@@@@@@@@@@  @@@@@@@@  @@@@ @@@  @@@@@@@@  @@@@@@@@  @@@       @@@@@@@@                  @@@  @@@@@@@@  
@@! @@! @@!  @@!  @@@  @@!@!@@@  !@@       @@!  @@@  @@!       @@!  @@@                  @@!  @@!       
!@! !@! !@!  !@!  @!@  !@!!@!@!  !@!       !@!  @!@  !@!       !@!  @!@                  !@!  !@!       
@!! !!@ @!@  @!@!@!@!  @!@ !!@!  !@!       @!@!@!@!  @!!       @!@!@!@!  @!@!@!@!@       !!@  @!!!:!    
!@!   ! !@!  !!!@!!!!  !@!  !!!  !!!       !!!@!!!!  !!!       !!!@!!!!  !!!@!@!!!       !!!  !!!!!:    
!!:     !!:  !!:  !!!  !!:  !!!  :!!       !!:  !!!  !!:       !!:  !!!                  !!:  !!:       
:!:     :!:  :!:  !:!  :!:  !:!  :!:       :!:  !:!   :!:      :!:  !:!             !!:  :!:  :!:       
:::     ::   ::   :::   ::   ::   ::: :::  ::   :::   :: ::::  ::   :::             ::: : ::   :: ::::  
 :      :     :   : :  ::    :    :: :: :   :   : :  : :: : :   :   : :              : :::    : :: ::   
```                                                                                                           

## Introduction

According to [Ultraboard Games](https://www.ultraboardgames.com/mancala/history.php) the mancala game has existed for about 7000 years. This means that since the middle neolithic phase of History, mankind has been playing this game.

This project is also the official support project of my article on medium:

[The Mancala Saga — Episode I — A minikube box](https://medium.com/@jofisaes/the-mancala-saga-episode-i-64118716b40a)

## Docker Compose

I have provided a docker-compose environment that will run this application in your docker-machine addess.

Make sure that the docker-machine is correctly installed and that you set your environment to your machine.

All that needs top be done is to call the build.sh script with the name of the target docker-machine. In my case its 'dev'.

Example:

```bash
./build.sh dev
```

Be sure to wait at least a couple of minutes. The Spring boot process needs to start queues and all the connectivity with PostgreSQL.

## Milestones 🖲

2019-08-01:
-   5 hour long inactivity registration via email
-   Mail notification
-   Responsive web app
-   Refactoring and re-using Redux

2019-07-22:
-   Password Encryption

2019-07-21:
-   JMS Queueing system implemented for maintenance tasks

2019-07-15:
-   First fully playable version

2019-07-02:
-   OAuth2 Login

2019-07-01:
-   OAuth2 Implementation
-   Basic front end

## Docker images

This project makes use of the following docker images:

[![dockeri.co](https://dockeri.co/image/jesperancinha/je-all-build)](https://hub.docker.com/r/jesperancinha/je-all-build)

[![dockeri.co](https://dockeri.co/image/library/postgres)](https://hub.docker.com/r/library/postgres)

All source code for the [JE](https://bitbucket.org/jesperancinha/docker-images) images reside in repo [Docker images](https://bitbucket.org/jesperancinha/docker-images).

## DevOps References

```text
$ touch /etc/nginx/sites-available/mancalaje
$ sudo ln -s /etc/nginx/sites-available/mancalaje /etc/nginx/sites-enabled/
$ sudo service nginx restart
$ sudo vim /etc/systemd/system/mancalaje.service  
$ sudo vim mancalaje-service  
$ sudo chmod +x mancalaje-service  
$ sudo systemctl daemon-reload  
$ sudo systemctl enable mancalaje  
$ sudo systemctl start mancalaje  
$ sudo systemctl status mancalaje
$ sudo -u postgres psql
\password postgres
create database mancalajedb
\q
```

## Setting up Virtual Machines 💻

### Setting up OpenShift
[![alt text](Documentation/mje-openshift-s.png)](https://manage.openshift.com/)

-   Open an account

    -   [Openshift online](https://manage.openshift.com/)

-   Setup OKD (Original Community Distribution of Kubernetes)

    -   [OKD](https://www.okd.io/index.html)

-   Install Minishift

```bash
brew cask install minishift
brew cask install --force minishift
minishift addons install --defaults
minishift addons enable admin-user
minishift start --vm-driver=virtualbox
brew install openshift-cli
oc adm policy --as system:admin add-cluster-role-to-user cluster-admin developer
minishift console
oc create rolebinding default-view --clusterrole=view --serviceaccount=mancalaje:default --namespace=mancalaje
```

## Setting up minikube 💻

[![alt text](Documentation/mje-docker-s.png "Docker")](https://www.docker.com/)
[![alt text](Documentation/mje-kubernetes-s.png "Kubernetes")](https://kubernetes.io/)
[![alt text](Documentation/mje-minikube-s.png "Minikube")](https://github.com/kubernetes/minikube)
[![alt text](Documentation/mje-vmware-s.png "VMWare")](https://www.vmware.com/)
[![alt text](Documentation/mje-virtualbox-s.png "Virtual Box")](https://www.virtualbox.org/)

NOTE: If you want to run this with vmware please install [VMWare Fusion](https://www.vmware.com/products/fusion/fusion-evaluation.html). You will need this to use the vmrun command. 📝

### Configure minikube ⌨️
```bash
minikube delete # Just in case 😉
minikube config set vm-driver virtualbox
minikube start --vm-driver=virtualbox --extra-config=apiserver.service-node-port-range=1-30000
minikube addons enable ingress # Not mandatory for now 🙂
kubectl config use-context minikube
minikube mount .:/mancalaje
minikube ssh
cd /mancalaje/docker-psql
docker build --file=Dockerfile --tag=mancalaje-postgresql:latest --rm=true .
cd /mancalaje/mancalaje-service
docker build --file=Dockerfile --tag=mancalaje:latest --rm=true .
cd /mancalaje/mancalaje-fe/docker-files
docker build --file=Dockerfile --tag=mancalaje-fe:latest --rm=true .
exit
```
### Configure deployment ⌨️

```bash
kubectl create -f docker-psql/postgres-deployment.yaml
kubectl create -f mancalaje-service/mancalaje-deployment.yaml
kubectl create -f mancalaje-fe/mancalaje-fe-deployment.yaml

kubectl delete service mancalaje-postgresql
kubectl delete deployment mancalaje-postgresql

kubectl delete service mancalaje
kubectl delete deployment mancalaje

kubectl delete service mancalaje-fe
kubectl delete deployment mancalaje-fe

minikube service mancalaje-postgresql
minikube service mancalaje
minikube service mancalaje-fe

kubectl get deployments
kubectl get services
kubectl get pods
```

🚀 and we are redy to go!

### Minikube Hints & Tricks

-   Install minikube for MAC-OS

```bash
brew install minikube
brew link kubernetes-cli
brew link --overwrite kubernetes-cli
brew install docker-machine-driver-virtualbox
brew link --overwrite --dry-run docker-machine
minikube config set driver virtualbox
```

-   Install minikube for linux (not fully tested)

```bash
curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 \
  && chmod +x minikube
mkdir -p /usr/local/bin
install minikube /usr/local/bin/
```

-   Install minikube for Windows (not fully tested)

    -   Please install:
        -   [VMWare Workstation Player](https://www.vmware.com/products/workstation-player.html)
        -   [VMWare Workstation](https://www.vmware.com/products/workstation-pro/workstation-pro-evaluation.html)
        -   [VMware VIX 1.15.7](https://my.vmware.com/web/vmware/details?productId=640&downloadGroup=PLAYER-1253-VIX1157) (This may be optional. Check your VMWare folder for the presence of vmrun.exe)
    -   Add this to your $PATH: C:\Program Files (x86)\VMware\VMware VIX

```bash
Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Hyper-V -All
choco install -y docker-machine-vmware -pre
```
-   VMWare runs
```bash
bcdedit /set hypervisorlaunchtype off
minikube start --alsologtostderr -v=7 --vm-driver vmware
minikube start --vm-driver=vmware
```

-   Use and mount minikube 

```bash
minikube config set vm-driver vmware
minikube start
kubectl config use-context minikube
minikube mount .:/mancalaje
minikube ssh
cd /mancalaje/mancalaje-service
docker build --file=Dockerfile --tag=mancalaje:latest --rm=true .
```

-   Running jar with minikube (not fully tested)
```bash
kubectl config use-context minikube
kubectl cluster-info
kubectl run demo-backend --image=demo-backend:latest --port=8080 --image-pull-policy Never
```

-   Remove minikube

```bash
minikube stop
minikube delete
rm -rf ~/.minikube
rm -rf ~/.kube
brew uninstall minikube
```

## Hints & Tricks

-   Install java versions without [SDKMan](https://sdkman.io/) for [ubuntu prompt for windows](https://www.microsoft.com/en-us/p/ubuntu/9nblggh4msv6?activetab=pivot:overviewtab).

```bash
apt-get -y update
apt-get -y upgrade
apt -y install apt-transport-https ca-certificates wget dirmngr gnupg software-properties-common
wget -qO - https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public | apt-key add -
add-apt-repository --yes https://adoptopenjdk.jfrog.io/adoptopenjdk/deb/
apt -y update
sudo apt -y install openjdk-11-jdk
sudo apt install openjdk-13-jdk
sudo apt -y install adoptopenjdk-8-hotspot
sudo apt -y autoremove
```

- .bashrc file to get Gradle, GitPrompt, [SDKMAN](https://sdkman.io/) and some handy aliases in a Windows environment with [MinGW](http://www.mingw.org/).

```bash
export GRADLE_HOME=/opt/gradle/gradle-6.1.1
export PATH=${GRADLE_HOME}/bin:${PATH}
alias ll='ls -l -a --color=auto'
if [ -f "/root/.bash-git-prompt/gitprompt.sh" ]; then
    GIT_PROMPT_ONLY_IN_REPO=1
    source /root/.bash-git-prompt/gitprompt.sh
fi

alias java13="sdk use java 13.0.2.hs-adpt"
alias java12="sdk use java 12.0.2.hs-adpt"
alias java8="sdk use java 8.0.242.hs-adpt"
alias m2disable="rm ~/.m2/settings.xml"
alias m2enable="cp /your_repo_folder/settings.xml ~/.m2/"

#THIS MUST BE AT THE END OF THE FILE FOR SDKMAN TO WORK!!!
export SDKMAN_DIR="/root/.sdkman"
[[ -s "/root/.sdkman/bin/sdkman-init.sh" ]] && source "/root/.sdkman/bin/sdkman-init.sh"
```

- .bashrc file to get Gradle, GitPrompt and some handy aliases in a Windows environment with [ubuntu prompt for windows](https://www.microsoft.com/en-us/p/ubuntu/9nblggh4msv6?activetab=pivot:overviewtab).

```bash
export GRADLE_HOME=/opt/gradle/gradle-6.1.1
export PATH=${GRADLE_HOME}/bin:${PATH}
alias ll='ls -l -a --color=auto'
if [ -f "/root/.bash-git-prompt/gitprompt.sh" ]; then
    GIT_PROMPT_ONLY_IN_REPO=1
    source /root/.bash-git-prompt/gitprompt.sh
fi

alias java8="export JAVA_HOME=/usr/lib/jvm/adoptopenjdk-8-hotspot-amd64 && update-java-alternatives -s adoptopenjdk-8-hotspot-amd64"
alias java11="export JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64 && update-java-alternatives -s java-1.11.0-openjdk-amd64"
alias java12="echo \"Java 12 is not available. Setting up 13\" && export JAVA_HOME=/usr/lib/jvm/java-13-oracle && update-java-alternatives -s java-13-oracle"
alias java13="export JAVA_HOME=/usr/lib/jvm/java-13-oracle && update-java-alternatives -s java-13-oracle"

#THIS MUST BE AT THE END OF THE FILE FOR SDKMAN TO WORK!!!
export SDKMAN_DIR="/root/.sdkman"
[[ -s "/root/.sdkman/bin/sdkman-init.sh" ]] && source "/root/.sdkman/bin/sdkman-init.sh"
```
-   Update Alternatives

```bash
 update-java-alternatives -l
```

-   Fix Apt

```bash
dpkg --configure -a
```

-   Git tag change
```bash
git tag new-tag old-tag
git tag -d old-tag
git push origin :refs/tags/old-tag
git push --tags
git pull --prune --tags
```

## References 📚

###  [![alt text](Documentation/mje-openshift-s.png "Openshift")](https://www.openshift.com/) Openshift

-   [OpenShift by Tutorials Point](https://www.tutorialspoint.com/openshift/index.htm)
-   [Learn Open Shift](https://learn.openshift.com/)
-   [Deploy a Spring Boot Application to OpenShift](https://www.baeldung.com/spring-boot-deploy-openshift)
-   [Minishift](https://docs.okd.io/latest/minishift/index.html)
-   [Minishif Installation](https://docs.okd.io/latest/minishift/getting-started/installing.html)

### [![alt text](Documentation/mje-minikube-s.png "Minikube")](https://github.com/kubernetes/minikube) Minikube

-   [What is minikube, kubectl and kubelet by Andreth Salazar](https://www.quora.com/What-is-minikube-kubectl-and-kubelet)
-   [Install Minikube](https://kubernetes.io/docs/tasks/tools/install-minikube/)
-   [Installing Kubernetes with Minikube](https://kubernetes.io/docs/setup/learning-environment/minikube/)
-   [Trigger a Spring Batch job with a JMS message](https://blog.mimacom.com/trigger-spring-batch-job-with-jms-message/)
-   [Postman](https://www.getpostman.com/)
-   [Spring Boot OAuth2 Social Login with Google, Facebook, and Github - Part 1](https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-1/)
-   [Shields IO](https://shields.io)
-   [How To Install Oracle Java 8 and OpenJDK 11 on Ubuntu 18.04, 19.04](https://www.linuxbabe.com/ubuntu/install-oracle-java-8-openjdk-11-ubuntu-18-04-18-10)
-   [Running Spring Boot Applications With Minikube by Baeldung](https://www.baeldung.com/spring-boot-minikube)
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

## About me 👨🏽‍💻🚀

[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social)](https://twitter.com/joaofse)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=jesperancinha&style=social)](https://github.com/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=BitBucket&message=jesperancinha&color=navy)](https://bitbucket.org/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitLab&message=jesperancinha&color=navy)](https://gitlab.com/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=joaofilipesabinoesperancinha.nl&color=6495ED)](http://joaofilipesabinoesperancinha.nl)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Time%20Disruption%20Studios&color=6495ED)](http://tds.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Image%20Train%20Filters&color=6495ED)](http://itf.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=MancalaJE&color=6495ED)](http://mancalaje.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Articles&message=On%20The%20Web&color=purple)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/LossArticles.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=DEV&message=Profile&color=green)](https://dev.to/jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Medium&message=@jofisaes&color=green)](https://medium.com/@jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Hackernoon&message=@jesperancinha&color=green)](https://hackernoon.com/@jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Free%20Code%20Camp&message=jofisaes&color=008000)](https://www.freecodecamp.org/jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Hackerrank&message=jofisaes&color=008000)](https://www.hackerrank.com/jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Code%20Forces&message=jesperancinha&color=008000)](https://codeforces.com/profile/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Coder%20Byte&message=jesperancinha&color=008000)](https://coderbyte.com/profile/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Code%20Wars&message=jesperancinha&color=008000)](https://www.codewars.com/users/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Acclaim%20Badges&message=joao-esperancinha&color=red)](https://www.youracclaim.com/users/joao-esperancinha/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Badges.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Status.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Google%20Apps&message=Joao+Filipe+Sabino+Esperancinha&color=orange)](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Code%20Pen&message=jesperancinha&color=orange)](https://codepen.io/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Android&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate-android)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Java&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate-modules/tree/master/itf-chartizate-java)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20API&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate/tree/master/itf-chartizate-api)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Core&color=yellow)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-core)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Filter&color=yellow)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-filter)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Docker%20Images&message=jesperanciha&color=099CEC)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/DockerImages.md)

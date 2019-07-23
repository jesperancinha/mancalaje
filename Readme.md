# MancalaJe

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f30b682f67e44391a922f62ada6b2f85)](https://www.codacy.com/app/jofisaes/mancalaje?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/mancalaje&amp;utm_campaign=Badge_Grade)
[![CircleCI](https://circleci.com/gh/jesperancinha/mancalaje.svg?style=svg)](https://circleci.com/gh/jesperancinha/mancalaje)
[![Build Status](https://travis-ci.org/jesperancinha/mancalaje.svg?branch=master)](https://travis-ci.org/jesperancinha/mancalaje)
[![codebeat badge](https://codebeat.co/badges/eaf7a2e0-ed2c-47fb-9323-2051db72c171)](https://codebeat.co/projects/github-com-jesperancinha-mancalaje-master)
    
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

[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=MancalaJE&color=informational)](http://mancalaje.joaofilipesabinoesperancinha.nl/)

This latest version is currently under contruction.

All Front end is being updated.

stay tuned!

## Status

**Under development**

## Milestones

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

## References

-   [Trigger a Spring Batch job with a JMS message](https://blog.mimacom.com/trigger-spring-batch-job-with-jms-message/)
-   [Postman](https://www.getpostman.com/)
-   [Spring Boot OAuth2 Social Login with Google, Facebook, and Github - Part 1](https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-1/)
-   [Shields IO](https://shields.io)

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
## License

```text
Copyright 2016-2019 João Esperancinha (jesperancinha)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## About me

-   [![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=joaofilipesabinoesperancinha&color=informational)](http://joaofilipesabinoesperancinha.nl)

-   ![Twitter Follow](https://img.shields.io/twitter/follow/jofisaes.svg?label=@jofisaes&style=social)

-   ![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=jesperancinha&style=social)

-   Free Code Camp [jofisaes](https://www.freecodecamp.org/jofisaes)

-   Hackerrank [jofisaes](https://www.hackerrank.com/jofisaes)

-   Acclaim Badges [joao-esperancinha](https://www.youracclaim.com/users/joao-esperancinha/badges)

-   Personal Projects:

    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Time%20Disruption%20Studios&color=informational)](http://tds.joaofilipesabinoesperancinha.nl/)
    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Image%20Train%20Filters&color=informational)](http://itf.joaofilipesabinoesperancinha.nl/)
    -   [![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=MancalaJE&color=informational)](http://mancalaje.joaofilipesabinoesperancinha.nl/)
    -   Google play apps: [Joao+Filipe+Sabino+Esperancinha](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)

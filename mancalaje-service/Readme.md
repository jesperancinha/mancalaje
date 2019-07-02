#MancalaJe Service

## Setting up environment

>$ brew cask install homebrew/cask-versions/java11.

## OAuth2 Requests

>$ brew install httpie  
>$ http -a mancala-client:mancala --form POST http://localhost:8080/oauth/token username='playerOne@mancalaje.com' password='admin123' grant_type='password'  
>$ curl -X POST -u "mancala-client:mancala" -d "grant_type=password&username=playerOne@mancalaje.com&password=admin123" http://localhost:8080/oauth/token

## References

-   [Spring Session with JDBC](https://www.baeldung.com/spring-session-jdbc)
-   [SDK Man!](https://sdkman.io/)
-   [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
-   [Simple Single Sign-On with Spring Security OAuth2](https://www.baeldung.com/sso-spring-security-oauth2)
-   [Spring Boot 2 And OAuth 2 - A Complete Guide](https://pattern-match.com/blog/2018/10/17/springboot2-with-oauth2-integration/)
-   [An OAuth 2.0 introduction for beginners](https://itnext.io/an-oauth-2-0-introduction-for-beginners-6e386b19f7a9)
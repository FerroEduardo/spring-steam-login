# Spring Steam OpenID Login

Proof of concept for implementing Steam OpenID authentication with Spring

## TODO

- [ ] Total refactor

## Endpoints

- `/steam/login`: redirects to Steam login page and then redirects to the API to save the session
- `/steam/profile`: uses Spring current session to show the data/attributes obtained from login

> Before you execute the application, make sure to configure the Steam API Token by utilizing the environment variable `SOFTAWII_STEAM_TOKEN`.

## References

- Custom Authentication
  - [The Registration Process With Spring Security](https://www.baeldung.com/registration-with-spring-mvc-and-spring-security)
  - [Spring security - multiple authentication providers](https://dev.to/nerminkarapandzic/spring-security-multiple-authentication-providers-3628)
  - [Java Spring Security config - multiple authentication providers](https://stackoverflow.com/questions/35363924/java-spring-security-config-multiple-authentication-providers)
  - [Springboot 2.7 injetar authenticationManager sem o WebSecurityConfigurerAdapter](https://cursos.alura.com.br/forum/topico-springboot-2-7-injetar-authenticationmanager-sem-o-websecurityconfigureradapter-228994#1127693)
- Steam OpenID
  - [Steam OpenID Authentication for PHP](https://github.com/xPaw/SteamOpenID.php)

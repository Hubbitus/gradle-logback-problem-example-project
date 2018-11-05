# Project for demonstrate problem to use logback in gradle buildSrc

Please run in that project:

    ./gradlew --no-daemon testLogback

you will see similar output:

```
> Task :testLogback
Hello from buildSrc!
SLF4J: Failed to load class "org.slf4j.impl.StaticMDCBinder".
SLF4J: Defaulting to no-operation MDCAdapter implementation.
SLF4J: See http://www.slf4j.org/codes.html#no_static_mdc_binder for further details.
2018-11-05 03:59:45,345 [Task worker for ':'] [mdc_test:-] INFO  qwerty.sayHello:36 - ExtLog logger initialization
2018-11-05 03:59:45,356 [Task worker for ':'] [mdc_test:-] INFO  qwerty.sayHello:39 - MDC.mdcAdapter=[org.slf4j.helpers.NOPMDCAdapter@16b5ecf4], StaticMDCBinder.SINGLETON.getMDCA()=[ch.qos.logback.classic.util.LogbackMDCAdapter@5e1c85d6]
```

As you see there `LogbackMDCAdapter` found, but not in scope of `MDC.<cinit>`!
package info.hubbitus.gradle;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.util.LogbackMDCAdapter;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import groovy.util.logging.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.slf4j.impl.StaticMDCBinder;

public class ExtLog {
	public static void sayHello() {
		System.out.println("Hello from buildSrc!");

		// print logback's internal status:
		// LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		// StatusPrinter.print(lc);
		// Gradle do not use Logback compatible loggers (https://discuss.gradle.org/t/logback-xml-versus-gradle-build/340)! So we create fresh context:
		LoggerContext context = new LoggerContext();

		try {
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext((Context) context);
			context.reset();
			configurator.doConfigure("src/test/resources/logback-test.xml");
		} catch (JoranException je) {
			// StatusPrinter will handle this
		}
		StatusPrinter.printInCaseOfErrorsOrWarnings(context);

		Logger logger = context.getLogger("qwerty");
		logger.info("ExtLog logger initialization");

		MDC.put("mdc_test", "qaz");
		logger.info("MDC.mdcAdapter=[{}], StaticMDCBinder.SINGLETON.getMDCA()=[{}]", MDC.mdcAdapter, StaticMDCBinder.SINGLETON.getMDCA());
	}
}

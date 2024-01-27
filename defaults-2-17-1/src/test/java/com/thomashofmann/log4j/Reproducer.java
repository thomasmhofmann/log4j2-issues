package com.thomashofmann.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.lookup.StrLookup;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Reproducer {

   @Test
    void testDefaults() {
       //System.setProperty("log4j2.debug", "true");
       //System.setProperty("log.filename.suffix", "suffix set by system property");
       Logger logger = LogManager.getRootLogger();
       logger.atInfo().log("This is an info message.");

       LoggerContext loggerContext = (LoggerContext) LogManager.getContext(true);
       Configuration loggerConfig = loggerContext.getConfiguration();
       StrSubstitutor strSubstitutor = loggerConfig.getStrSubstitutor();
       StrLookup variableResolver = strSubstitutor.getVariableResolver();
       String logFileNameSuffix = variableResolver.lookup("logFileNameSuffix");

       Assertions.assertEquals("2-17-1", logFileNameSuffix);
       logger.atInfo().log("logFileNameSuffix is {}", logFileNameSuffix);
   }
}

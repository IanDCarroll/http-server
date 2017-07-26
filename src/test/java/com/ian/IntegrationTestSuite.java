package com.ian;

import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ServSocketTest.class,
        ParserTest.class,
        ChefTest.class
})

public class IntegrationTestSuite {

    @ClassRule
    public static ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            Runnable serverThread = () -> new ServSocket().serve(5000);
            new Thread(serverThread).start();
        }
    };
}

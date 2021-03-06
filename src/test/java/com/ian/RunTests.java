package com.ian;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.List;


public class RunTests {
    private static Result unit;
    private static Result intg;

    private static int runs;
    private static int ignores;
    private static int fails;
    private static List<Failure> failReport;
    private static boolean success;
    private static long time;

    public static void main(String[] args) {
        runIntegrationTests();
        runUnitTests();

        aggregateResults();

        printResults();
    }

    private static void runUnitTests() {
        unit = JUnitCore.runClasses(ServerTest.class,
                                    RequestValidatorTest.class,
                                    AuthValidatorTest.class,
                                    RangeValidatorTest.class,
                                    MethodizerTest.class,
                                    LogKeeperTest.class,
                                    ParserTest.class,
                                    HeadersParserTest.class,
                                    RangeHeaderParserTest.class,
                                    StartLineParserTest.class,
                                    SplitsParserTest.class,
                                    ParamsParserTest.class,
                                    HexParserTest.class,
                                    AssignmentParserTest.class,
                                    ParamsBodyJoinerTest.class,
                                    MethodTest.class,
                                    ResponseChefTest.class,
                                    DisembodiedSousChefTest.class,
                                    CorpulentSousChefTest.class,
                                    SuccessCookTest.class,
                                    RedirectCookTest.class,
                                    ClientErrorCookTest.class,
                                    HeadersCookTest.class,
                                    ParamsCookTest.class,
                                    ContentCookTest.class,
                                    DirectoryCookTest.class,
                                    HTCPCPListerTest.class,
                                    RedirectListerTest.class,
                                    ByteArrayCookTest.class,
                                    FileStockerTest.class);
    }

    private static void runIntegrationTests() {
        Runnable intgRunnable = () -> intg = JUnitCore.runClasses(IntegrationTest.class);
        Thread intgThread = new Thread(intgRunnable);
        intgThread.start();
        try { intgThread.join();
        } catch (InterruptedException e) { e.getMessage(); }
    }

    private static void aggregateResults() {
        runs = unit.getRunCount() + intg.getRunCount();
        ignores = unit.getIgnoreCount() + intg.getIgnoreCount();
        fails = unit.getFailureCount() + intg.getFailureCount();

        long unitTime = unit.getRunTime();
        long intgTime = intg.getRunTime();
        time = unitTime > intgTime ? unitTime : intgTime;

        success = unit.wasSuccessful() && intg.wasSuccessful();

        List<Failure> unitFailures = unit.getFailures();
        List<Failure> intgFailures = intg.getFailures();
        unitFailures.addAll(intgFailures);
        failReport = unitFailures;
    }

    private static void printResults() {
        System.out.format("testing complete:\n");
        System.out.format("%d tests; %d ignored; with %d failures in %d miliseconds\n",
                          runs, ignores, fails, time);
        if (success) {
            System.out.println("All tests are passing.");
        } else {
            printFailures();
        }
    }

    private static void printFailures() {
        System.out.format("There were failures...\n");
        for (Failure failure : failReport) {
            System.out.format("\nThis was a thing that failed:\n");
            System.out.println(failure);
        }
    }
}

package org.ammonium.linkit.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;


@Command(
    name = "linkit",
    description = "This is the linkit command line application",
    version = "0.1.0",
    subcommands = {
        Add.class,
        Delete.class,
        List.class,
        Update.class
    })
public class Linkit implements Callable<Integer> {

    @Option(
        names = { "-v", "--version"},
        versionHelp = true,
        description = "Version number")
    private boolean versionRequested;

    @Override
    public Integer call() {
        System.out.print("Linkit Command Line App");
        return 0;
    }
}

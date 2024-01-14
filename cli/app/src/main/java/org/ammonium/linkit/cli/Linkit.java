package org.ammonium.linkit.cli;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;


@Command(
    name = "linkit",
    description = "This is the linkit command line application",
    subcommands = {
        Add.class,
        Delete.class,
        List.class,
        Update.class
    })
public class Linkit implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.print("Linkit Command Line App");
        return 0;
    }
}



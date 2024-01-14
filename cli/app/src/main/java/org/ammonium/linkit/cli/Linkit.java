package org.ammonium.linkit.cli;

import org.ammonium.linkit.cli.add.Add;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(
        name = "linkit",
        description = "This is the linkit command line application",
        subcommands = {
                Add.class
        })
public class Linkit implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.printf("Linkit Command Line App");
        return 0;
    }
}



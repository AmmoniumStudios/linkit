package org.ammonium.linkit.cli.add;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "add",
        description = "Add command"
)
public class Add implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println("Add Command");
        return 0;
    }
}

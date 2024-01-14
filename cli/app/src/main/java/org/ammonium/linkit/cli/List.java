package org.ammonium.linkit.cli;

import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
    name = "list",
    description = "Lists keys stored in the database"
)
public class List implements Callable<Integer> {

    @Option(
        names = { "--id" },
        description = "The id of the database row to list"
    )
    String id;

    @Override
    public Integer call() {
        System.out.println("List command coming soon");
        return 0;
    }
}

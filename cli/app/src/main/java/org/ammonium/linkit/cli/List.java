package org.ammonium.linkit.cli;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
    name = "list",
    description = "Lists keys stored in the database"
)
public class List implements Callable<Integer> {

    @ArgGroup(exclusive = true, multiplicity = "1")
    private Exclusive exclusive;

    private static class Exclusive {
        @Option(
            names = {"--id"},
            description = "The id of the database row"
        )
        private String id;

        @Option(
            names = {"-s", "--short"},
            description = "The short link"
        )
        private String shortLink;
    }


    @Override
    public Integer call() {
        System.out.println("List command coming soon");
        return 0;
    }
}

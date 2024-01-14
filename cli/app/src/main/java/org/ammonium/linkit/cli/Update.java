package org.ammonium.linkit.cli;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
    name = "update",
    description = "Updates the short and / or url in the database"
)
public class Update implements Callable<Integer> {

    @Option(
        names = { "-s", "--short" },
        description = "The short link",
        required = true
    )
    String shortLink;

    @ArgGroup(exclusive = true, multiplicity = "1")
    Exclusive exclusive;

    public static class Exclusive {
        @Option(
            names = { "--id" },
            description = "The id of the database row"
        )
        String id;

        @Option(
            names = { "--url" },
            description = "The full url of the link"
        )
        String url;
    }

    @Override
    public Integer call() {
        System.out.println("Update command coming soon");
        return 0;
    }
}

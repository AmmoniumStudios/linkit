package org.ammonium.linkit.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(
    name = "delete",
    description = "The delete command will remove a url from the database"
)
public class Delete implements Callable<Integer> {

    @Option(
        names = { "--id" },
        description = "The id for the row in the database"
    )
    String id;

    @Option(
        names = {"-s", "--short"},
        description = "The short key stored in the database"
    )
    String shortLink;

    @Override
    public Integer call() {
        System.out.println("Delete command coming soon");
        return 0;
    }
}

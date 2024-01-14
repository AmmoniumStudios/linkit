package org.ammonium.linkit.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(
        name = "add",
        description = "Add command"
)
public class Add implements Callable<Integer> {

    @CommandLine.Option(
            names = { "-s", "--short" },
            description = "The short link for the database",
            defaultValue = "TODO" // TODO: Code Generator
    )
    private String shortLink;

    @Parameters(
            paramLabel = "url",
            description = "The full URL of the website",
            arity = "1"
    )
    private String url;

    @Override
    public Integer call() {
        System.out.println("Add command coming soon");
        return 0;
    }
}

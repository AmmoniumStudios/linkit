package org.ammonium.linkit.cli;

import org.ammonium.linkit.util.CodeGenerator;
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
        names = {"-s", "--short"},
        description = "The short link for the database"
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

        if (shortLink == null) {
            shortLink = CodeGenerator.generateCode();
        }

        // TODO: Create the json request

        // TODO: Send request

        // TODO: Handle response

        return 0;
    }
}

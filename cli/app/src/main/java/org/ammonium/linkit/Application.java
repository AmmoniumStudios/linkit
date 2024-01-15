package org.ammonium.linkit;

import org.ammonium.linkit.cli.Linkit;
import picocli.CommandLine;

public final class Application {

    public static void main(String[] args) {

        new CommandLine(new Linkit()).execute(args);
    }
}

package org.ammonium.linkit.cli;

import org.ammonium.linkit.model.http.Error;
import org.ammonium.linkit.model.http.ResponseWrapper;
import org.ammonium.linkit.util.CodeGenerator;
import org.ammonium.linkit.util.HttpUtil;
import org.ammonium.linkit.util.json.Body;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Command(
    name = "add",
    description = "Add command"
)
public class AddCommand implements Callable<Integer> {

    private static final String INSERT = "INSERT INTO 'links' ('short', 'url') VALUES (?, ?);";

    private final AtomicInteger statusCode = new AtomicInteger(-1);
    private Logger LOGGER = Logger.getLogger("LOGGGGGGGGGGER");

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

        Body requestBody = new Body(INSERT, new String[]{shortLink, url});
        HttpUtil.createShort(requestBody).thenAccept(responseBody -> {
            ResponseWrapper wrapper = HttpUtil.GSON.fromJson(responseBody.body(), ResponseWrapper.class);

            boolean successful = wrapper.isSuccess();

            if (successful) {
                statusCode.set(200);
                System.out.printf("Successfully added %s to the database", shortLink);
            } else {

                Error error = wrapper.getErrors().getFirst();

                statusCode.set(error.getCode());
                System.out.printf("Error %d: %s", error.getCode(), error.getMessage());
            }
        });

        return statusCode.get();
    }
}
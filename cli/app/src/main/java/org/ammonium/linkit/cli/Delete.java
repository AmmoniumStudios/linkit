package org.ammonium.linkit.cli;

import org.ammonium.linkit.model.http.Error;
import org.ammonium.linkit.model.http.ResponseWrapper;
import org.ammonium.linkit.util.HttpUtil;
import org.ammonium.linkit.util.json.Body;
import org.checkerframework.checker.units.qual.A;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

@Command(
    name = "delete",
    description = "The delete command will remove a url from the database"
)
public class Delete implements Callable<Integer> {

    private static final String DELETE_BY_ID = "DELETE FROM links WHERE id = ?;";
    private static final String DELETE_BY_SHORT = "DELETE FROM links WHERE short = ?;";

    private final AtomicInteger statusCode = new AtomicInteger(0);

    @Option(
        names = { "--id" },
        description = "The id for the row in the database"
    )
    private String id;

    @Option(
        names = {"-s", "--short"},
        description = "The short key stored in the database"
    )
    private String shortLink;

    @Override
    public Integer call() {
        System.out.println("Deleting link");

        if (id == null && shortLink == null) {
            System.out.println("You must provide either an id or a short link");
            return 1;
        }

        String query = id == null ? DELETE_BY_SHORT : DELETE_BY_ID;
        String parameter = id == null ? shortLink : id;

        HttpUtil.createShort(new Body(query, new String[]{parameter}))
            .thenAccept(response -> {
                ResponseWrapper wrapper = HttpUtil.GSON.fromJson(response.body(), ResponseWrapper.class);
                if (wrapper.isSuccess()) {
                    System.out.println("Successfully deleted link");
                } else {
                    final Error error = wrapper.getErrors().getFirst();
                    System.out.printf("Failed to delete link. Reason: %s%n", error.getMessage());
                    this.statusCode.set(error.getCode());
                }
            });


        return this.statusCode.get();
    }
}

package org.ammonium.linkit.cli;

import org.ammonium.linkit.util.HttpUtil;
import org.ammonium.linkit.util.json.Body;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

@Command(
    name = "update",
    description = "Updates the short and / or url in the database"
)
public class UpdateCommand implements Callable<Integer> {

    private static final String UPDATE_SHORT_ID = "UPDATE links SET short = ? WHERE id = ?";
    private static final String UPDATE_URL_BY_SHORT = "UPDATE links SET url = ? WHERE short = ?";

    private final AtomicInteger statusCode = new AtomicInteger(0);

    @Option(
        names = {"-s", "--short"},
        description = "The short link",
        required = true
    )
    private String shortLink;

    @ArgGroup(multiplicity = "1")
    private Exclusive exclusive;

    @Override
    public Integer call() {
        String query;

        List<String> params = new ArrayList<>();


        if (this.exclusive.id != null) {
            query = UPDATE_SHORT_ID;
            params.add(this.shortLink);
            params.add(this.exclusive.id);
        } else {
            query = UPDATE_URL_BY_SHORT;
            params.add(this.exclusive.url);
            params.add(this.shortLink);
        }

        HttpUtil.createShort(new Body(query, params.toArray(new String[0])))
            .thenAccept(response -> {
                if (response.statusCode() == 200) {
                    System.out.println("Successfully updated the link");
                    this.statusCode.set(response.statusCode());
                } else {
                    System.out.println("Failed to update the link");
                    this.statusCode.set(response.statusCode());
                }
            });

        return this.statusCode.get();
    }

    private static class Exclusive {
        @Option(
            names = {"--id"},
            description = "The id of the database row"
        )
        private String id;

        @Option(
            names = {"--url"},
            description = "The full url of the link"
        )
        private String url;
    }

}

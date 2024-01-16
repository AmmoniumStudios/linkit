package org.ammonium.linkit.cli;

import org.ammonium.linkit.model.http.Error;
import org.ammonium.linkit.model.http.ResponseWrapper;
import org.ammonium.linkit.model.http.ShortUrlData;
import org.ammonium.linkit.util.HttpUtil;
import org.ammonium.linkit.util.ascii.TableGenerator;
import org.ammonium.linkit.util.json.Body;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

@Command(
    name = "list",
    description = "Lists keys stored in the database"
)
public class ListCommand implements Callable<Integer> {

    private static final String LIST_BY_ID = "SELECT * FROM links WHERE id = ?;";
    private static final String LIST_BY_SHORT = "SELECT * FROM links WHERE short = ?;";
    private static final String LIST_ALL = "SELECT * FROM links;";

    private final AtomicInteger statusCode = new AtomicInteger(0);

    @ArgGroup()
    private Exclusive exclusive;

    private static class Exclusive {
        @Option(
            names = {"--id"},
            description = "The id of the database row"
        )
        private int id;

        @Option(
            names = {"-s", "--short"},
            description = "The short link"
        )
        private String shortLink;
    }

    @Override
    public Integer call() {
        System.out.println("Listing links");

        String query, parameter;

        if (exclusive == null) {
            query = LIST_ALL;
            parameter = "";
        } else if (exclusive.id != 0) {
            query = LIST_BY_ID;
            parameter = String.valueOf(exclusive.id);
        } else {
            query = LIST_BY_SHORT;
            parameter = exclusive.shortLink;
        }

        String[] parameters = Objects.equals(parameter, "") ? new String[]{} : new String[]{parameter};

        HttpUtil.createShort(new Body(query, parameters))
            .thenAccept(response -> {
                ResponseWrapper wrapper = HttpUtil.GSON.fromJson(response.body(), ResponseWrapper.class);
                if (wrapper.isSuccess()) {

                    final List<ShortUrlData> data = wrapper.getResult().getFirst().getResults();

                    List<String> headers = List.of("ID", "Short", "URL");
                    List<List<String>> formattedData = new ArrayList<>();

                    for (int i = 0; i < data.size(); i++) {

                        String[] arr = data.get(i).toString().split(",");
                        List<String> innerData = new ArrayList<>(Arrays.asList(arr));
                        formattedData.add(i, innerData);
                    }

                    System.out.println(TableGenerator.generateTable(headers, formattedData));
                } else {
                    final Error error = wrapper.getErrors().getFirst();
                    System.out.printf("Failed to retrieve data. Reason: %s%n", error.getMessage());
                    this.statusCode.set(error.getCode());
                }
            });

        return this.statusCode.get();
    }
}

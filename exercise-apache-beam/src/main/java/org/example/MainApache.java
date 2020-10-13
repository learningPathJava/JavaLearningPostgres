package org.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.values.PCollection;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.apache.beam.sdk.values.TypeDescriptors.strings;

/**
 * next - save into DB
 */
public class MainApache {

    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).create();
        Pipeline pipeline       = Pipeline.create(options);

        String file = "./resources/cars.csv";

        PCollection<String> readCollection = pipeline
                .apply(FileIO.match().filepattern(file))
                .apply(FileIO.readMatches())
                .apply(FlatMapElements
                        .into(strings())
                        .via((FileIO.ReadableFile f) -> {
                            List<String> result = new ArrayList<>();
                            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));) {
                                String line = br.readLine();
                                while (line != null) {
                                    result.add(line);
                                    line = br.readLine();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            result.forEach(System.out::println);
                            return result;
                        }));
        pipeline.run();
    }
}

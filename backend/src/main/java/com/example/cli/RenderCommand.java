package com.example.cli;

import com.example.HelloWorldConfiguration;
import com.example.core.Template;
import com.google.common.base.Optional;
import com.yammer.dropwizard.cli.ConfiguredCommand;
import com.yammer.dropwizard.config.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RenderCommand extends ConfiguredCommand<HelloWorldConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RenderCommand.class);

    public RenderCommand() {
        super("render", "Render the template data to console.");
    }

    @Override
    protected void run(Bootstrap<HelloWorldConfiguration> bootstrap,
                       Namespace namespace,
                       HelloWorldConfiguration configuration) throws Exception {
        final Template template = configuration.buildTemplate();

        if (namespace.getBoolean("include-default")) {
            LOGGER.info("DEFAULT => {}", template.render(Optional.<String>absent()));
        }

        for (String name : namespace.<String>getList("names")) {
            for (int i = 0; i < 1000; i++) {
                LOGGER.info("{} => {}", name, template.render(Optional.of(name)));
                Thread.sleep(1000);
            }
        }

    }
}

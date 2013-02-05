package com.example.health;

import com.example.core.Template;
import com.google.common.base.Optional;
import com.yammer.metrics.core.HealthCheck;

public class TemplateHealthCheck extends HealthCheck {

    private final Template template;

    public TemplateHealthCheck(Template template) {
        super("template");
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        template.render(Optional.of("yay"));
        template.render(Optional.<String>absent());
        return Result.healthy();
    }
}

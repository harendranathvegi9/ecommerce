package com.aripd;

import javax.servlet.ServletContext;
import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

@RewriteConfiguration
public class RewriteConfigurationProvider extends HttpConfigurationProvider {

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public Configuration getConfiguration(final ServletContext context) {
        return ConfigurationBuilder.begin()
                .addRule(Join.path("/attributegroup/{attributegroupId}/{attributegroupSlug}/").to("/attributegroup.jsf"))
                .addRule(Join.path("/category/{id}/{slug}/attributegroup/{attributegroupId}/{attributegroupSlug}/attributevalue/{attributevalueId}/{attributevalueSlug}/").to("/category.jsf"))
                .addRule(Join.path("/category/{id}/{slug}/").to("/category.jsf"))
                .addRule(Join.path("/search/{searchTerm}/").to("/search.jsf"))
                .addRule(Join.path("/product/{id}/{slug}/").to("/product.jsf"))
                .addRule(Join.path("/").to("/index.jsf"));
    }
}

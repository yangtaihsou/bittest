package com.bittest.platform.pg.tag;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

public class FooterTagRuleBundle implements TagRuleBundle {

    @Override
    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        //自定义footer标签
        defaultState.addRule("footer", new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("footer"), false));
    }

    @Override
    public void cleanUp(State defaultState, ContentProperty contentProperty,
                        SiteMeshContext siteMeshContext) {
    }

}

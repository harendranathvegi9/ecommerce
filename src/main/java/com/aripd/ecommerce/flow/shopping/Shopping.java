package com.aripd.ecommerce.flow.shopping;

import java.io.Serializable;
import javax.enterprise.inject.Produces;
import javax.faces.flow.Flow;
import javax.faces.flow.builder.FlowBuilder;
import javax.faces.flow.builder.FlowBuilderParameter;
import javax.faces.flow.builder.FlowDefinition;

public class Shopping implements Serializable {

    @Produces
    @FlowDefinition
    public Flow defineFlow(@FlowBuilderParameter FlowBuilder flowBuilder) {
        String flowId = "shopping";
        flowBuilder.id("", flowId);

        flowBuilder.viewNode(flowId, "/member/" + flowId + "/" + flowId + ".xhtml").markAsStartNode();
        flowBuilder.viewNode("shopping_0062", "/member/" + flowId + "/shopping_0062.xhtml");
        flowBuilder.viewNode("shopping_iyzipay", "/member/" + flowId + "/shopping_iyzipay.xhtml");
        flowBuilder.viewNode("shopping_paypal", "/member/" + flowId + "/shopping_paypal.xhtml");
        flowBuilder.viewNode("shopping_payu", "/member/" + flowId + "/shopping_payu.xhtml");
        flowBuilder.viewNode("shopping_wire", "/member/" + flowId + "/shopping_wire.xhtml");

        flowBuilder.methodCallNode("go-to-payment").expression("#{shoppingBean.doProcessPayment()}");

        flowBuilder.returnNode("go-to-home")
                .fromOutcome("#{shoppingBean.goToHome()}");

        return flowBuilder.getFlow();
    }
}

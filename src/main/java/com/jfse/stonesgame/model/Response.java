package com.jfse.stonesgame.model;

/**
 * Created by joaofilipesabinoesperancinha on 07-04-16.
 */
public class Response {
    private ResponseStatus responseStatus;

    public Response(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }
}

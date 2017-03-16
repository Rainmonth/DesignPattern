package com.rainmonth.pattern.behavioral.chain;

public class OrcKing {

    private RequestHandler chain;

    public OrcKing() {
        buildChain();
    }

    public void makeRequest(Request request) {
        chain.handleRequest(request);
    }

    private void buildChain() {
        chain = new OrcCommander(new OrcOfficer(new OrcSoldier(null)));
    }
}

package com.qxd.learn.entity.entity;

/**
 * Created by Administrator on 2019/3/16/0016.
 */
public class ReceiveTulingMessage {
    private Intent intent;

    private Results results;

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public ReceiveTulingMessage(Intent intent, Results results) {
        this.intent = intent;
        this.results = results;
    }

    public ReceiveTulingMessage(Intent intent) {
        this.intent = intent;
    }

    public ReceiveTulingMessage() {
    }

    @Override
    public String toString() {
        return "ReceiveTulingMessage{" +
                "intent=" + intent +
                ", results=" + results +
                '}';
    }
}

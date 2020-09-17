package com.qxd.learn.entity.entity;

/**
 * Created by Administrator on 2019/3/15/0015.
 */
public class Perception {

    private InputText inputText;

    private InputImage inputImage;

    private SelfInfo selfInfo;

    public InputText getInputText() {
        return inputText;
    }

    public void setInputText(InputText inputText) {
        this.inputText = inputText;
    }

    public InputImage getInputImage() {
        return inputImage;
    }

    public void setInputImage(InputImage inputImage) {
        this.inputImage = inputImage;
    }

    public SelfInfo getSelfInfo() {
        return selfInfo;
    }

    public void setSelfInfo(SelfInfo selfInfo) {
        this.selfInfo = selfInfo;
    }

    public Perception(InputText inputText, InputImage inputImage, SelfInfo selfInfo) {
        this.inputText = inputText;
        this.inputImage = inputImage;
        this.selfInfo = selfInfo;
    }

    @Override
    public String toString() {
        return "Perception{" +
                "inputText=" + inputText +
                ", inputImage=" + inputImage +
                ", selfInfo=" + selfInfo +
                '}';
    }
}

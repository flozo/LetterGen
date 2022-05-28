package de.flozo.data;

public class Sender extends Address {

    private final String signatureFileName;

    public Sender(String signatureFileName) {
        this.signatureFileName = signatureFileName;
    }

    public String getSignatureFileName() {
        return signatureFileName;
    }
}

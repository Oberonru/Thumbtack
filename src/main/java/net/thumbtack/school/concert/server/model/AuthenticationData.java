package net.thumbtack.school.concert.server.model;

public abstract class AuthenticationData {
    String tokenId;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}

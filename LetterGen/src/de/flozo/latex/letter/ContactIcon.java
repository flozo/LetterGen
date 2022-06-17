package de.flozo.latex.letter;

import de.flozo.latex.core.Command2;

public enum ContactIcon {

    // general

    ATLAS("atlas"),
    BOOK("book"),
    BOOKMARK("bookmark"),
    CLOUD("cloud"),
    CLOUD_DOWNLOAD_ALT("cloud-download-alt"),
    CLOUD_UPLOAD_ALT("cloud-upload-alt"),
    COMMENT("comment"),
    COMMENT_ALT("comment-alt"),
    COMMENTS("comments"),
    CREDIT_CARD("credit-card"),
    DOWNLOAD("download"),
    EXTERNAL_LINK_ALT("external-link-alt"),
    EXTERNAL_LINK_SQUARE_ALT("external-link-square-alt"),
    FILE("file"),
    FILE_ALT("file-alt"),
    FILE_DOWNLOAD("file-download"),
    FILE_PDF("file-pdf"),
    GLOBE("globe"),
    GLOBE_AFRICA("globe-africa"),
    GLOBE_AMERICAS("globe-americas"),
    GLOBE_ASIA("globe-asia"),
    GLOBE_EUROPE("globe-europe"),
    PAPERCLIP("paperclip"),


    // identity

    ID_BADGE("id-badge"),
    ID_CARD("id-card"),
    ID_CARD_ALT("id-card-alt"),
    USER("user"),
    USER_ALT("user-alt"),
    USER_CIRCLE("user-circle"),


    // address

    ADDRESS_BOOK("address-book"),
    ADDRESS_CARD("address-card"),
    ENVELOPE("envelope"),
    ENVELOPE_OPEN("envelope-open"),
    ENVELOPE_OPEN_TEXT("envelope-open-text"),
    ENVELOPE_SQUARE("envelope-square"),
    HOME("home"),
    MAP_MARKED("map-marked"),
    MAP_MARKED_ALT("map-marked-alt"),
    MAP_MARKER("map-marker"),
    MAP_MARKER_ALT("map-marker-alt"),
    MAP_PIN("map-pin"),


    // communication

    AT("at"),
    FAX("fax"),
    MOBILE("mobile"),
    MOBILE_ALT("mobile-alt"),
    PHONE("phone"),
    PHONE_ALT("phone-alt"),
    PHONE_SQUARE("phone-square"),
    PHONE_SQUARE_ALT("phone-square-alt"),
    PHONE_VOLUME("phone-volume"),
    PRINT("print"),


    // crypto currencies

    BITCOIN("bitcoin"),
    BTC("btc"),
    ETHEREUM("ethereum"),


    // online platforms and services

    DIASPORA("diaspora"),
    DISCORD("discord"),
    DISCOURSE("discourse"),
    DROPBOX("dropbox"),
    FACEBOOK("facebook"),
    FACEBOOK_F("facebook-f"),
    FACEBOOK_MESSENGER("facebook-messenger"),
    FACEBOOK_SQUARE("facebook-square"),
    GITHUB("github"),
    GITHUB_ALT("github-alt"),
    GITHUB_SQUARE("github-square"),
    GITLAB("gitlab"),
    GOOGLE_DRIVE("google-drive"),
    LINKEDIN("linkedin"),
    LINKEDIN_IN("linkedin-in"),
    MASTODON("mastodon"),
    MENDELEY("mendeley"),
    ORCID("orcid"),
    PAYPAL("paypal"),
    REDDIT("reddit"),
    REDDIT_ALIEN("reddit-alien"),
    REDDIT_SQUARE("reddit-square"),
    SKYPE("skype"),
    SLACK("slack"),
    SOUNDCLOUD("soundcloud"),
    TWITTER("twitter"),
    TWITTER_SQUARE("twitter-square"),
    XING("xing"),
    XING_SQUARE("xing-square");


    private final String iconName;

    ContactIcon(String iconName) {
        this.iconName = iconName;
    }

    public String getIconDefault() {
        return new Command2.Builder("faIcon").body(iconName).build().getInline();
    }

    public String getIconSolid() {
        return new Command2.Builder("faIcon").optionList("solid").body(iconName).build().getInline();
    }

    public String getIconRegular() {
        return new Command2.Builder("faIcon").optionList("regular").body(iconName).build().getInline();
    }

}

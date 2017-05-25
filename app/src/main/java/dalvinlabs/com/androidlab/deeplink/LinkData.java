package dalvinlabs.com.androidlab.deeplink;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LinkData {
    @SerializedName("links")
    private List<String> mLinks;

    public List<String> getLinks() {
        return mLinks;
    }
}

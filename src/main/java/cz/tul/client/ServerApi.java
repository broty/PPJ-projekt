package cz.tul.client;

import retrofit.client.Response;
import retrofit.http.*;
import retrofit.mime.TypedFile;

/**
 * Created by Martin on 25.05.2017.
 */
public interface ServerApi {
    public static final String USERS_PATH = "/users";
    public static final String USER_PATH = USERS_PATH + "/{id}";
    public static final String IMAGES_PATH = "/images";
    public static final String IMAGE_PATH = IMAGES_PATH + "/{id}";
    public static final String TAGS_PATH = "/tags";
    public static final String TAG_PATH = TAGS_PATH + "/{id}";
    public static final String COMMENTS_PATH = "/comments";
    public static final String COMMENT_PATH = COMMENTS_PATH + "/{id}";

    public static final String DOWNLOAD_PATH = "/download/{name}";
    public static final String UPLOAD_PATH = "/upload/{name}";

    @Multipart
    @POST(UPLOAD_PATH)
    public ImageStatus uploadImage(@Path("name") String name, @Part("data") TypedFile imageData);

    @Streaming
    @GET(DOWNLOAD_PATH)
    Response downloadImage(@Path("name") String name);
}

package ca.umontreal.dir.ift2255.team21.distancecalculator;
import ca.umontreal.dir.ift2255.team21.entraves.Entraves;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import io.javalin.Javalin;
public class CalculateDistance {

    private String key = "AIzaSyAgCK4KJXdSOjAUL5jKwyRxY3cIaBSsWlE";

    //public Entraves[] listerEntraves() {}

    private Entraves[] appelAPI() {
        var app = Javalin.create().start(7000);
        app.get("/output", ctx -> {
            // some code
            //ctx.json(object);
        });
        return null;
    }
}

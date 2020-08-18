import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class Main extends AbstractVerticle {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(Main.class.getName());
  }

  @Override
  public void start() {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    router.get("/").produces("application/json").produces("text/html").handler(this::onGet);

    server.requestHandler(router).listen(8080);

    System.out.println("Listening on port 8080 ...");
  }

  private void onGet(RoutingContext ctx) {
    ctx.response().end(ctx.getAcceptableContentType());
  }
}

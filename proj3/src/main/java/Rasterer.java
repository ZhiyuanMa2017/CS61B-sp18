import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    public Rasterer() {

    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     * <p>
     * The grid of images must obey the following properties, where image in the
     * is referred to as a "tile".
     * <ul>
     *     <li>The tiles collected must cover the most longitudinal distance per pixel
     *     (LonDPP) possible, while still covering less than or equal to the amount of
     *     longitudinal distance per pixel in the query box for the user viewport size. </li>
     *     <li>Contains all tiles that intersect the query bounding box that fulfill the
     *     above condition.</li>
     *     <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     * </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     * forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        System.out.println(params);
        Map<String, Object> results = new HashMap<>();

        double lrlon = params.get("lrlon");
        double ullon = params.get("ullon");
        double width = params.get("w");
        double height = params.get("h");
        double ullat = params.get("ullat");
        double lrlat = params.get("lrlat");

        if (ullon > lrlon || ullat < lrlat || width <= 0 || height <= 0) {
            results.put("query_success", false);
            return results;
        }

        if (lrlon > MapServer.ROOT_LRLON || ullon < MapServer.ROOT_ULLON
                || ullat > MapServer.ROOT_ULLAT || lrlat < MapServer.ROOT_LRLAT) {
            results.put("query_success", false);
            return results;
        }
        double d0LonDPP = (MapServer.ROOT_LRLON - MapServer.ROOT_ULLON) / MapServer.TILE_SIZE;
        double d0LatDPP = (MapServer.ROOT_ULLAT - MapServer.ROOT_LRLAT) / MapServer.TILE_SIZE;
        int depth;

        double queryLonDPP = (lrlon - ullon) / width;

        for (depth = 0; depth < 7 && d0LonDPP > queryLonDPP; depth++) {
            d0LonDPP /= 2.0;
            d0LatDPP /= 2.0;
        }

        int x1 = (int) ((ullon - MapServer.ROOT_ULLON) / (d0LonDPP * MapServer.TILE_SIZE));
        int x2 = (int) ((lrlon - MapServer.ROOT_ULLON) / (d0LonDPP * MapServer.TILE_SIZE));
        int y1 = (int) ((MapServer.ROOT_ULLAT - ullat) / (d0LatDPP * MapServer.TILE_SIZE));
        int y2 = (int) ((MapServer.ROOT_ULLAT - lrlat) / (d0LatDPP * MapServer.TILE_SIZE));

        String[][] rendergrid = new String[y2 - y1 + 1][x2 - x1 + 1];
        for (int i = 0; i < y2 - y1 + 1; i++) {
            for (int j = 0; j < x2 - x1 + 1; j++) {
                rendergrid[i][j] = "d" + depth + "_x" + (j + x1) + "_y" + (i + y1) + ".png";
            }
        }

        results.put("render_grid", rendergrid);
        results.put("raster_ul_lon", MapServer.ROOT_ULLON + x1 * (d0LonDPP * MapServer.TILE_SIZE));
        results.put("raster_ul_lat", MapServer.ROOT_ULLAT - y1 * (d0LatDPP * MapServer.TILE_SIZE));
        results.put("raster_lr_lon",
                MapServer.ROOT_ULLON + (x2 + 1) * (d0LonDPP * MapServer.TILE_SIZE));
        results.put("raster_lr_lat",
                MapServer.ROOT_ULLAT - (y2 + 1) * (d0LatDPP * MapServer.TILE_SIZE));
        results.put("depth", depth);
        results.put("query_success", true);

        return results;
    }

    public static void main(String[] args) {
        Map<String, Double> params = new HashMap<>();
        params.put("ullon", -122.292887961);
        params.put("ullat", 37.883626573);
        params.put("lrlon", -122.275684767);
        params.put("lrlat", 37.856014984);
        params.put("w", 557.0);
        params.put("h", 894.0);
        Rasterer rasterer = new Rasterer();
        Map<String, Object> res = rasterer.getMapRaster(params);
        for (Map.Entry<String, Object> entry : res.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

}

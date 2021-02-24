import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {
    private static final double EPS = 0.01;

    /** Depth 0's image's LonDPP */
    private double tile0LonDPP;
    private double tile0LatDPP;

    /** The depth of image we should use. */
    private int depth;

    public Rasterer() {
        tile0LonDPP = (MapServer.ROOT_LRLON - MapServer.ROOT_ULLON) / MapServer.TILE_SIZE;
        tile0LatDPP = (MapServer.ROOT_ULLAT - MapServer.ROOT_LRLAT) / MapServer.TILE_SIZE;
        depth = 0;
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {

        Map<String, Object> results = new HashMap<>();

        if (params.get("ullon") + EPS < MapServer.ROOT_ULLON || params.get("ullat") > MapServer.ROOT_ULLAT + EPS
                || params.get("lrlon") > MapServer.ROOT_LRLON + EPS
                || params.get("lrlat") + EPS < MapServer.ROOT_LRLAT) {
            results.put("query_success", false);
            return results;
        }

        double queryLonDPP = (params.get("lrlon") - params.get("ullon")) / params.get("w");
        double tileiLonDPP = tile0LonDPP;
        double tileiLatDPP = tile0LatDPP;
        for (depth = 0; depth < 7 && tileiLonDPP > queryLonDPP; ++depth) {
            tileiLonDPP /= 2.0;
            tileiLatDPP /= 2.0;
        }

        int startX = (int) ((params.get("ullon") - MapServer.ROOT_ULLON)
                / (tileiLonDPP * MapServer.TILE_SIZE));
        int endX = (int) ((params.get("lrlon") - MapServer.ROOT_ULLON)
                / (tileiLonDPP * MapServer.TILE_SIZE));
        int startY = (int) ((MapServer.ROOT_ULLAT - params.get("ullat"))
                / (tileiLatDPP * MapServer.TILE_SIZE));
        int endY = (int) ((MapServer.ROOT_ULLAT - params.get("lrlat"))
                / (tileiLatDPP * MapServer.TILE_SIZE));

        int maxXY = (int) Math.pow(2, depth);
        endX = endX >= maxXY ? maxXY - 1 : endX;
        endY = endY >= maxXY ? maxXY - 1 : endY;

        String[][] renderGrid = new String[endY - startY + 1][endX - startX + 1];

        for (int i = 0; i <= endY - startY && i + startY < Math.pow(2, depth); ++i) {
            for (int j = 0; j <= endX - startX && j + startX < Math.pow(2, depth); ++j) {
                renderGrid[i][j] = "d" + depth + "_x" + (j + startX) + "_y" + (i + startY) + ".png";
            }
        }

        results.put("render_grid", renderGrid);
        results.put("raster_ul_lon", MapServer.ROOT_ULLON
                + startX * (tileiLonDPP * MapServer.TILE_SIZE));
        results.put("raster_ul_lat", MapServer.ROOT_ULLAT
                - startY * (tileiLatDPP * MapServer.TILE_SIZE));
        results.put("raster_lr_lon", MapServer.ROOT_ULLON
                + (endX + 1) * (tileiLonDPP * MapServer.TILE_SIZE));
        results.put("raster_lr_lat", MapServer.ROOT_ULLAT
                - (endY + 1) * (tileiLatDPP * MapServer.TILE_SIZE));
        results.put("depth", depth);
        results.put("query_success", true);

        return results;
    }
}

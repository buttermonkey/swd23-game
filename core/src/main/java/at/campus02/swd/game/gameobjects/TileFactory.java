package at.campus02.swd.game.gameobjects;

import java.util.Objects;

public class TileFactory implements GameObjectFactory<TileType> {

    public static final int TOP_LEFT_ID = 61;
    public static final int TOP_ID = 62;
    public static final int TOP_RIGHT_ID = 63;
    public static final int LEFT_ID = 76;
    public static final int CENTER_ID = 77;
    public static final int RIGHT_ID = 78;
    public static final int BACKGROUND_ID = 187;
    public static final int FINISH_ID = 95;

    @Override
    public Tile create(TileType type) {
        int textureId = switch (Objects.requireNonNull(type)) {
            case TOP_LEFT -> TOP_LEFT_ID;
            case TOP -> TOP_ID;
            case TOP_RIGHT -> TOP_RIGHT_ID;
            case LEFT -> LEFT_ID;
            case CENTER -> CENTER_ID;
            case RIGHT -> RIGHT_ID;
            case BOTTOM_LEFT -> rotate(TOP_RIGHT_ID);
            case BOTTOM -> rotate(TOP_ID);
            case BOTTOM_RIGHT -> rotate(TOP_LEFT_ID);
            case WATER -> BACKGROUND_ID;
            case FINISH -> FINISH_ID;
        };

        String textureFile = String.format("tiles/mapTile_%03d.png", actualTextureId(textureId));
        return new Tile(textureFile, isRotated(textureId));
    }

    private static int actualTextureId(int textureId) {
        return isRotated(textureId) ? textureId >> 10 : textureId;
    }

    private static boolean isRotated(int textureId) {
        return textureId >= (1 << 10);
    }

    private static int rotate(int textureId) {
        return textureId << 10;
    }
}

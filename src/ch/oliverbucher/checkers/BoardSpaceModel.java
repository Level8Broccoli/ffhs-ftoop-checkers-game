package ch.oliverbucher.checkers;

public class BoardSpaceModel {
    private boolean isEmpty;
    private int coordinateX;
    private int coordinateY;
    private BoardColors boardColors;

    public BoardSpaceModel(boolean isEmpty, int coordinateX, int coordinateY, BoardColors boardColors) {
        this.isEmpty = isEmpty;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.boardColors = boardColors;
    }
}

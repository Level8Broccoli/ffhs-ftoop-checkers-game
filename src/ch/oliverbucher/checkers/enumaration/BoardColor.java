package ch.oliverbucher.checkers.enumaration;

public enum BoardColor {

    LIGHT, DARK;

    private BoardColor next;

    static {
        LIGHT.next = DARK;
        DARK.next = LIGHT;
    }

    public BoardColor switchColor() {
        return next;
    }
}

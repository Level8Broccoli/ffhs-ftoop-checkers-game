package ch.oliverbucher.checkers.enumaration;

public enum MarkType {

    CURRENT_CLICK, POSSIBLE_MOVE, TOKEN_COULD_MOVE, NO_MOVE_POSSIBLE;

    public String getName() {

        return this.name();
    }
}

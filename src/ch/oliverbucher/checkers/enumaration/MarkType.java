package ch.oliverbucher.checkers.enumaration;

public enum MarkType {

    CURRENT_CLICK, POSSIBLE_MOVE, TOKEN_COULD_MOVE_OR_JUMP, TOKEN_MOVED;

    public String getName() {

        return this.name();
    }
}

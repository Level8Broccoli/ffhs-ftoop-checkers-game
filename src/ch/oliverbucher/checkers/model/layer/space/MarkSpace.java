package ch.oliverbucher.checkers.model.layer.space;

import ch.oliverbucher.checkers.enumaration.MarkType;

public class MarkSpace {

    private MarkType markType;

    public MarkSpace(MarkType markType) {

        this.markType = markType;
    }

    public String getName() {

        return markType.name();
    }
}

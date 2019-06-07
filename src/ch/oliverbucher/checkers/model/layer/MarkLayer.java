package ch.oliverbucher.checkers.model.layer;

import ch.oliverbucher.checkers.enumaration.MarkType;
import ch.oliverbucher.checkers.model.layer.space.MarkSpace;
import ch.oliverbucher.checkers.model.position.PositionXY;

import java.util.HashMap;

public class MarkLayer {

    private HashMap<PositionXY, MarkSpace> marks;
    private PositionXY lastClicked;
    
    public MarkLayer() {

        marks = new HashMap<>();
        updateMarkLayer();
        
    }

    private void updateMarkLayer() {

        System.out.println(marks);
    }

    public void markCurrentClick(PositionXY currentClick) {

        marks.remove(lastClicked);
        updateMarkLayer();
        marks.put(currentClick, new MarkSpace(MarkType.CURRENT_CLICK));
        lastClicked = currentClick;
    }

    public MarkSpace get(PositionXY position) {

            return marks.get(position);
    }
}

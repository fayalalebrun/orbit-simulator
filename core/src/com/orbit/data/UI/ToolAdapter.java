package com.orbit.data.UI;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kotcrab.vis.ui.util.adapter.ArrayListAdapter;
import com.kotcrab.vis.ui.widget.VisTable;

import java.util.ArrayList;

/**
 * Created by Fran on 5/24/2017.
 */
public class ToolAdapter extends ArrayListAdapter<Tool, VisTable> {
    AssetManager manager;

    Tool currentTool;

    public ToolAdapter(ArrayList<Tool> array, AssetManager manager, Tool currentTool) {
        super(array);
        this.manager = manager;
        this.currentTool = currentTool;
    }

    @Override
    protected VisTable createView(Tool item) {

        Texture texture = null;

        switch (item){
            case POINTER:
                texture = manager.get("pointer.png", Texture.class);
                break;
            case MOVE:
                texture = manager.get("move.png", Texture.class);
                break;
            case ZOOM_IN:
                texture = manager.get("zoom-in", Texture.class);
                break;
            case ZOOM_OUT:
                texture = manager.get("zoom-out", Texture.class);
                break;
        }

        VisTable table = new VisTable();

        Image image = new Image(texture);
        image.setSize(32f,32f);

        table.add(image);

        return table;
    }
}

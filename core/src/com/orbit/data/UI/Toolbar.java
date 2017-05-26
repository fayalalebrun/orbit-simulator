package com.orbit.data.UI;

import com.badlogic.gdx.assets.AssetManager;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.orbit.data.GameScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fran on 5/24/2017.
 */
public class Toolbar extends VisWindow{
    AssetManager manager;


    public Toolbar(AssetManager manager){
        super("Tools");
        this.manager = manager;

        TableUtils.setSpacingDefaults(this);
        addButtons();
        pack();
    }

    private void addButtons(){
        ToolAdapter adapter = new ToolAdapter(new ArrayList<Tool>(), manager);
        ListView<Tool> view = new ListView<Tool>(adapter);
        this.add(view.getMainTable()).grow();
        adapter.add(Tool.MOVE);
        adapter.add(Tool.POINTER);
        adapter.add(Tool.ZOOM_IN);
        adapter.add(Tool.ZOOM_OUT);

        adapter.setItemClickListener(new ListView.ItemClickListener<Tool>() {
            @Override
            public void clicked(Tool item) {
                GameScreen.currentTool = item;
            }
        });
    }

}

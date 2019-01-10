package org.lwjglb.game;

import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

import java.util.HashMap;
import java.util.List;

import org.lwjglb.engine.GameItem;
import org.lwjglb.engine.IGameLogic;
import org.lwjglb.engine.Window;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.graph.Texture;

import model.Edge;
import model.State;

public class DummyGame implements IGameLogic {

    private int displxInc = 0;

    private int displyInc = 0;

    private int displzInc = 0;

    private int scaleInc = 0;

    private final Renderer renderer;

    private GameItem[] gameItems;
    
    // Create the Mesh
    private float[] positions = new float[] {
        // V0
        -0.5f, 0.5f, 0.5f,
        // V1
        -0.5f, -0.5f, 0.5f,
        // V2
        0.5f, -0.5f, 0.5f,
        // V3
        0.5f, 0.5f, 0.5f,
        // V4
        -0.5f, 0.5f, -0.5f,
        // V5
        0.5f, 0.5f, -0.5f,
        // V6
        -0.5f, -0.5f, -0.5f,
        // V7
        0.5f, -0.5f, -0.5f,
        
        // For text coords in top face
        // V8: V4 repeated
        -0.5f, 0.5f, -0.5f,
        // V9: V5 repeated
        0.5f, 0.5f, -0.5f,
        // V10: V0 repeated
        -0.5f, 0.5f, 0.5f,
        // V11: V3 repeated
        0.5f, 0.5f, 0.5f,

        // For text coords in right face
        // V12: V3 repeated
        0.5f, 0.5f, 0.5f,
        // V13: V2 repeated
        0.5f, -0.5f, 0.5f,

        // For text coords in left face
        // V14: V0 repeated
        -0.5f, 0.5f, 0.5f,
        // V15: V1 repeated
        -0.5f, -0.5f, 0.5f,

        // For text coords in bottom face
        // V16: V6 repeated
        -0.5f, -0.5f, -0.5f,
        // V17: V7 repeated
        0.5f, -0.5f, -0.5f,
        // V18: V1 repeated
        -0.5f, -0.5f, 0.5f,
        // V19: V2 repeated
        0.5f, -0.5f, 0.5f,
    };
    
    private float[] textCoords = new float[]{
            0.0f, 0.0f,
            0.0f, 0.5f,
            0.5f, 0.5f,
            0.5f, 0.0f,
            
            0.0f, 0.0f,
            0.5f, 0.0f,
            0.0f, 0.5f,
            0.5f, 0.5f,
            
            // For text coords in top face
            0.0f, 0.5f,
            0.5f, 0.5f,
            0.0f, 1.0f,
            0.5f, 1.0f,

            // For text coords in right face
            0.0f, 0.0f,
            0.0f, 0.5f,

            // For text coords in left face
            0.5f, 0.0f,
            0.5f, 0.5f,

            // For text coords in bottom face
            0.5f, 0.0f,
            1.0f, 0.0f,
            0.5f, 0.5f,
            1.0f, 0.5f,
        };

    private int[] indices = new int[]{
            // Front face
            0, 1, 3, 3, 1, 2,
            // Top Face
            8, 10, 11, 9, 8, 11,
            // Right face
            12, 13, 7, 5, 12, 7,
            // Left face
            14, 15, 6, 4, 14, 6,
            // Bottom face
            16, 18, 19, 17, 16, 19,
            // Back face
            4, 6, 7, 5, 4, 7,};
    
	private HashMap<Integer, State> states;
	private List<Edge> edges;
	private List<String> transitions;
    
    public DummyGame(HashMap<Integer, State> states, List<Edge> edges, List<String> transitions) {
        this.states = states;
        this.edges = edges;
        this.transitions = transitions;
    	renderer = new Renderer();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        Texture redTexture = new Texture("/textures/redblockwithborder.png");
        Texture greenTexture = new Texture("/textures/greenblockwithborder.png");
        Mesh redMesh = new Mesh(positions, textCoords, indices, redTexture);
        Mesh greenMesh = new Mesh(positions, textCoords, indices, greenTexture);
        
        //TODO Unsure what the algorithm could be for diagramming states and edges properly
        gameItems = new GameItem[states.size() + edges.size()];
        for(int i = 0; i < states.size(); i++) {
        	GameItem gameItem = new GameItem(redMesh);
            gameItem.setPosition(0, 0 + i*4, -11);
            gameItems[i] = gameItem;
        }
        for(int i = states.size(); i < states.size() + edges.size(); i++) {
        	GameItem gameItem = new GameItem(greenMesh);
            gameItem.setPosition(-5 + i*2, 2, -11);
            gameItems[i] = gameItem;
        }
    }

    @Override
    public void input(Window window) {
        displyInc = 0;
        displxInc = 0;
        displzInc = 0;
        scaleInc = 0;
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            displyInc = 1;
        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            displyInc = -1;
        } else if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            displxInc = -1;
        } else if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            displxInc = 1;
        } else if (window.isKeyPressed(GLFW_KEY_A)) {
            displzInc = -1;
        } else if (window.isKeyPressed(GLFW_KEY_Q)) {
            displzInc = 1;
        } else if (window.isKeyPressed(GLFW_KEY_Z)) {
            scaleInc = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            scaleInc = 1;
        }
    }

    @Override
    public void update(float interval) {
        for (GameItem gameItem : gameItems) {
            // Update position
            Vector3f itemPos = gameItem.getPosition();
            float posx = itemPos.x + displxInc * 0.01f;
            float posy = itemPos.y + displyInc * 0.01f;
            float posz = itemPos.z + displzInc * 0.01f;
            gameItem.setPosition(posx, posy, posz);

            // Update scale
            float scale = gameItem.getScale();
            scale += scaleInc * 0.05f;
            if (scale < 0) {
                scale = 0;
            }
            gameItem.setScale(scale);

            // Disable rotation
            // float rotation = gameItem.getRotation().x + 1.5f;
            // if (rotation > 360) {
            //     rotation = 0;
            // }
            // gameItem.setRotation(rotation, rotation, rotation);
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, gameItems);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (GameItem gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
    }

}

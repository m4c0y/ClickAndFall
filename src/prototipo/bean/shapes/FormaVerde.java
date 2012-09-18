package prototipo.bean.shapes;

import prototipo.bean.shapes.interfaces.NotClickable;
import prototipo.bean.shapes.interfaces.Verde;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class FormaVerde extends Forma implements Verde, NotClickable{
	
	public FormaVerde(float positionX, float positionY, float width, float height) {
		super(positionX, positionY, width, height, BodyType.DynamicBody);
		this.setColor(0f, 1.0f, 0f);
	}
}

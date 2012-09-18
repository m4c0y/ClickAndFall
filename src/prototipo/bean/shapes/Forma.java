package prototipo.bean.shapes;

import org.anddev.andengine.entity.primitive.Rectangle;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public abstract class Forma extends Rectangle{

	private BodyType type;
	
	public Forma(float positionX, float positionY, float width, float height, BodyType type) {
		super(positionX, positionY, width, height);
		this.setType(type);
	}

	public BodyType getType() {
		return type;
	}

	private void setType(BodyType type) {
		this.type = type;
	}
}

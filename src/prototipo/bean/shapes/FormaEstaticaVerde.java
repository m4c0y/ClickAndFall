package prototipo.bean.shapes;

import prototipo.bean.shapes.interfaces.NotClickable;
import prototipo.bean.shapes.interfaces.Verde;


public class FormaEstaticaVerde extends FormaEstatica implements Verde, NotClickable{

	public FormaEstaticaVerde(float positionX, float positionY, float width, float height) {
		super(positionX, positionY, width, height);
		this.setColor(0f, 1.0f, 0f);
	}
}
